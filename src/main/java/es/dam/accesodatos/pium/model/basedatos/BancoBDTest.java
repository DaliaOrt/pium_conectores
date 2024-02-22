package es.dam.accesodatos.pium.model.basedatos;

import java.sql.SQLException;
import java.util.List;

import es.dam.accesodatos.pium.model.CuentaBancaria;
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

    @Override
    public Usuario obtenerUsuario(String nombreUsuario) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerUsuario'");
    }

    @Override
    public double obtenerSaldo() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerSaldo'");
    }

    @Override
    public void ActualizarSaldo(double nuevaCantidad) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ActualizarSaldo'");
    }

    @Override
    public void crearCuenta(CuentaBancaria cuenta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearCuenta'");
    }

    @Override
    public List<CuentaBancaria> obtenerCuentasUsuario(String nombreUsuario) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerCuentasUsuario'");
    }

    @Override
    public int obtenerIdUsuario(String nombreUsuario) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerIdUsuario'");
    }

}
