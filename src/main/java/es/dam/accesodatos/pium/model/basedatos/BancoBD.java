package es.dam.accesodatos.pium.model.basedatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.dam.accesodatos.pium.model.CuentaBancaria;
import es.dam.accesodatos.pium.model.Usuario;

public class BancoBD implements BancoBDDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/sistema_bancario";
    private static final String USUARIO = "patata";
    private static final String CONTRASENA = "patata";
    private Connection conn = null;

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }

    public BancoBD() throws SQLException {
        this.conn = connect();
    }

    @Override
    public void registrarUsuario(Usuario usuario) throws SQLException {
        final String INSERTAR = "INSERT INTO usuarios (nombre, apellido, contrasena) VALUES (?,?,?)";

        PreparedStatement ps = null;

        ps = conn.prepareStatement(INSERTAR);
        ps.setString(1, usuario.getNombre());
        ps.setString(2, usuario.getApellidos());
        ps.setString(3, usuario.getContrasena());
        ps.executeUpdate();

    }

    @Override
    public boolean iniciarSesion(String nombre, String contrasena) throws SQLException {
        boolean inicioSesion = false;
        final String CONSULTA = "SELECT * FROM usuarios WHERE nombre = ? AND contrasena = ?";
        PreparedStatement ps = conn.prepareStatement(CONSULTA);
        ps.setString(1, nombre);
        ps.setString(2, contrasena);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            inicioSesion = true;
        }

        ps.close();
        rs.close();

        return inicioSesion;
    }

    @Override
    public Usuario obtenerUsuario(String nombreUsuario) throws SQLException {
        Usuario usuario = null;
        final String CONSULTA = "SELECT * FROM usuarios WHERE nombre = ?";

        try (PreparedStatement ps = conn.prepareStatement(CONSULTA)) {
            ps.setString(1, nombreUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    String contrasena = rs.getString("contrasena");

                    usuario = new Usuario(nombre, apellido, contrasena);
                }
            }
        }

        return usuario;
    }

    @Override
    public void crearCuenta() {
        final String INSERTAR = "INSERT INTO cuentas_bancarias (id_usuario, saldo) VALUES (?,?)";
    }

    public void hacerPium() {

    }

    //SIN IMPLEMENTAR
    @Override
    public double obtenerSaldo() throws SQLException {
        PreparedStatement st = conn.prepareStatement("SELECT saldo FROM cuentas_bancarias WHERE id = ?");
        st.setInt(1, 1); // ID de la cuenta
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return rs.getDouble("saldo");
        }
        throw new SQLException("No se pudo obtener la cantidad existente de la base de datos.");
    }

    //SIN IMPLEMENTAR
    @Override
    public void ActualizarSaldo(double nuevaCantidad) throws SQLException {
        PreparedStatement st = conn.prepareStatement("UPDATE cuentas_bancarias SET cantidad = ? WHERE id = ?");
        st.setDouble(1, nuevaCantidad);
        st.setInt(2, 1); // Aquí va el ID de la cuenta
        st.executeUpdate();
    }

    // REPLANTEAR
    @Override
    public List<CuentaBancaria> mostrarCuentas(String nombre) throws SQLException {
        List<CuentaBancaria> cuentas = new ArrayList<>();
        final String CONSULTA = "SELECT * FROM cuentas_bancarias WHERE id_usuario = (SELECT id, nombre FROM usuarios WHERE nombre = ? )";
        PreparedStatement ps = conn.prepareStatement(CONSULTA);
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int idCuenta = rs.getInt("id");
            int idUsuario = rs.getInt("id_usuario");
            String telefono = rs.getString("telefono");
            double saldo = rs.getDouble("saldo");
            boolean tieneBizum = rs.getBoolean("tiene_bizum");
            cuentas.add(new CuentaBancaria(idCuenta, idUsuario, telefono, saldo, tieneBizum));
        }
        return cuentas;
    }

}
