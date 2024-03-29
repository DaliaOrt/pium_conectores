package es.dam.accesodatos.pium.model.basedatos;


import java.sql.SQLException;
import java.util.List;

import es.dam.accesodatos.pium.model.CuentaBancaria;
import es.dam.accesodatos.pium.model.Usuario;

public interface BancoBDDAO {

    public boolean iniciarSesion(String nombre, String contrasena) throws SQLException;
    public void registrarUsuario(Usuario usuario) throws SQLException;
    public List<CuentaBancaria> obtenerCuentasUsuario(String nombreUsuario) throws SQLException;
    public void crearCuenta(CuentaBancaria cuenta) throws SQLException;
    public Usuario obtenerUsuario(String nombreUsuario) throws SQLException;
    public int obtenerIdUsuario(String nombreUsuario) throws SQLException;
    public void actualizarSaldo(int idCuenta, double nuevaCantidad) throws SQLException;
    public boolean tieneCuentaBizum(String nombreUsuario) throws SQLException;
    public boolean existeUsuario(String nombreUsuario) throws SQLException;
    public double obtenerSaldoCuenta(String nombreUsuario) throws SQLException;
    public double obtenerSaldoCuentaPium(String nombreUsuario) throws SQLException;
    public void hacerPium(String nombreRemitente, String nombreDestinatario, double cantidad) throws SQLException;
}
