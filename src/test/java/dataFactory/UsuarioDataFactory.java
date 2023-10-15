package dataFactory;

import pojo.UsuarioPojo;

public class UsuarioDataFactory {

    public static UsuarioPojo usuarioPadrao () {

        UsuarioPojo usuario = new UsuarioPojo();
        usuario.setUsuarioLogin("admin");
        usuario.setUsuarioSenha("admin");

        return usuario;
    }

}
