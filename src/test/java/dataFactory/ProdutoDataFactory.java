package dataFactory;

import pojo.ComponentePojo;
import pojo.ProdutoPojo;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDataFactory {
    public static ProdutoPojo criarProdutoComumComOValorIgualA(double valor) {
        ProdutoPojo produto = new ProdutoPojo();
        produto.setProdutoNome("Diário");
        produto.setProdutoValor(valor);

        List<String> cores = new ArrayList<>();
        cores.add("rosa");
        cores.add("preto");

        produto.setProdutoCores(cores);
        produto.setProdutoUrlMock("");

        List<ComponentePojo> componentes = new ArrayList<>();

        ComponentePojo componente = new ComponentePojo();
        componente.setCompenenteNome("Cadeado");
        componente.setComponenteQuantidade(1);
        componentes.add(componente);

        produto.setComponentes(componentes);

        return produto;
    }



}
