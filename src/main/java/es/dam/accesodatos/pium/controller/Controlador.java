package es.dam.accesodatos.pium.controller;

import java.sql.SQLException;

import es.dam.accesodatos.pium.model.Usuario;
import es.dam.accesodatos.pium.model.basedatos.BancoBDDAO;
import es.dam.accesodatos.pium.model.basedatos.BancoBDFactory;

public class Controlador {

    private BancoBDDAO daoBanco;

    public Controlador() throws SQLException {
        daoBanco = BancoBDFactory.getDAO(BancoBDFactory.MODO_SQL);
    }

    public void registrarUsuario(Usuario usuario) throws SQLException {
        daoBanco.registrarUsuario(usuario);        
    }

    public boolean iniciarSesion(String nombre, String contrasena) throws SQLException {
       return daoBanco.iniciarSesion(nombre, contrasena);
    }

}
