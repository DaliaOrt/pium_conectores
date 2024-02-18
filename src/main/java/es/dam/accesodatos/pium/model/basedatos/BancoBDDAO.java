package es.dam.accesodatos.pium.model.basedatos;


import java.sql.SQLException;
import java.util.List;

import es.dam.accesodatos.pium.model.CuentaBancaria;
import es.dam.accesodatos.pium.model.Usuario;

public interface BancoBDDAO {

    public boolean iniciarSesion(String nombre, String contrasena) throws SQLException;
    public void registrarUsuario(Usuario usuario) throws SQLException;
    public List<CuentaBancaria> mostrarCuentas(String nombre) throws SQLException;
    public void crearCuenta();
    public Usuario obtenerUsuario(String nombreUsuario) throws SQLException;
    public double obtenerSaldo() throws SQLException;
    public void ActualizarSaldo(double nuevaCantidad) throws SQLException;
}
