package es.dam.accesodatos.pium.model.basedatos;


import java.sql.SQLException;

import es.dam.accesodatos.pium.model.Usuario;

public interface BancoBDDAO {

    public boolean iniciarSesion(String nombre, String contrasena) throws SQLException;
    public void registrarUsuario(Usuario usuario) throws SQLException;
    
}
