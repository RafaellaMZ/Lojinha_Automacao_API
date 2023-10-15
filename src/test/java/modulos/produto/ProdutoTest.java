package modulos.produto;

import dataFactory.ProdutoDataFactory;
import dataFactory.UsuarioDataFactory;
import groovy.json.JsonOutput;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.ComponentePojo;
import pojo.ProdutoPojo;
import pojo.UsuarioPojo;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API REST do módulo de produto")
public class ProdutoTest {

    private String token;

    @BeforeEach
    public void berforeEach() {
        // Configurando os dados da API REST da Lojinha
        baseURI = "http://165.227.93.41";
        // As vezes, é necessário colocar a porta onde a base URI está localizada. port = 8080;
        basePath = "/lojinha";

        //JUNIT te dá os métodos de teste e possibilidade de executar os testes
        //RestAssured te dá a possibilidade de apontar uma API e executar os as requisições contra ela, de modo que você possa validá-los de maneira automatizada

        // Obter o token do usuário Admin
        this.token = given()
                .contentType(ContentType.JSON)
                .body(UsuarioDataFactory.usuarioPadrao())
            .when() // Onde diz o método que se deve usar
                .post("/v2/login")
            .then()
                .extract()
                    .path("data.token"); //Se coloca no path uma estrutura hierárquica do que tem dentro da response

    }

    @Test
    @DisplayName("Validar que o valor do produto igual a 00.00 não é permitido")
    public void testValidarLimiteZeradoValorProduto() {

        // Tentar inserir um produto com valor 0.00 e validar que a mensagem de erro foi apresentada e o status code retornado foi 422
        given()
            .contentType(ContentType.JSON)
                .header("token", this.token)
            .body(ProdutoDataFactory.criarProdutoComumComOValorIgualA(00.00))
        .when()
            .post("/v2/produtos")
        .then()
            .assertThat()
                .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .statusCode(422);
    }

    @Test
    @DisplayName("Validar que o valor do produto igual a 7000.01 não é permitido")
    public void testValidarLimiteSeteMilValorProduto() {

        // Tentar inserir um produto com valor 7000.01 e validar que a mensagem de erro foi apresentada e o status code retornado foi 422
        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarProdutoComumComOValorIgualA(7000.01))
            .when()
                .post("/v2/produtos")
            .then()
                .assertThat()
                    .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                    .statusCode(422);
    }

}
