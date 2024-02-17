package es.dam.accesodatos.pium.model.basedatos;

import java.sql.SQLException;

import es.dam.accesodatos.pium.model.Usuario;

public class BancoBDTest implements BancoBDDAO {

    @Override
    public boolean iniciarSesion(String nombre, String contrasena) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iniciarSesion'");
    }

    @Override
    public void registrarUsuario(Usuario usuario) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registrarUsuario'");
    }

}
