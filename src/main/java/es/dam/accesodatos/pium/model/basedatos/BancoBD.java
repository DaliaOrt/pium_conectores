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
        final String INSERTAR = "INSERT INTO usuario (nombre, apellido, telefono, contrasena) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(INSERTAR);
        ps.setString(1, usuario.getNombre());
        ps.setString(2, usuario.getApellido());
        ps.setString(3, usuario.getTelefono());
        ps.setString(4, usuario.getContrasena());
        ps.executeUpdate();

    }

    @Override
    public boolean iniciarSesion(String nombre, String contrasena) throws SQLException {
        boolean inicioSesion = false;
        final String CONSULTA = "SELECT * FROM usuario WHERE nombre = ? AND contrasena = ?";
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
    public List<CuentaBancaria> obtenerCuentasUsuario(String nombreUsuario) throws SQLException {
        List<CuentaBancaria> cuentas = new ArrayList<>();
        final String CONSULTA = "SELECT cb.*, u.id AS id_usuario FROM cuenta_bancaria cb " +
                "INNER JOIN usuario u ON cb.id_usuario = u.id " +
                "WHERE u.nombre = ?";

        PreparedStatement ps = conn.prepareStatement(CONSULTA);
        ps.setString(1, nombreUsuario);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int numCuenta = rs.getInt("num_cuenta");
            int idUsuario = rs.getInt("id_usuario");
            double saldo = rs.getDouble("saldo");
            boolean tieneBizum = rs.getBoolean("tiene_bizum");

            CuentaBancaria cuenta = new CuentaBancaria(numCuenta, idUsuario, saldo, tieneBizum);
            cuentas.add(cuenta);
        }
        return cuentas;
    }

    @Override
    public Usuario obtenerUsuario(String nombreUsuario) throws SQLException {
        Usuario usuario = null;
        final String CONSULTA = "SELECT * FROM usuario WHERE nombre = ?";

        try (PreparedStatement ps = conn.prepareStatement(CONSULTA)) {
            ps.setString(1, nombreUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    String telefono = rs.getString("telefono");
                    String contrasena = rs.getString("contrasena");

                    usuario = new Usuario(nombre, apellido, telefono, contrasena);
                }
            }
        }

        return usuario;
    }

    @Override
    public int obtenerIdUsuario(String nombreUsuario) throws SQLException {
        final String CONSULTA = "SELECT id from usuario WHERE nombre = ?";
        int id = 0;
        try(PreparedStatement ps = conn.prepareStatement(CONSULTA)) {
            ps.setString(1, nombreUsuario );
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt("id");
                }
            }
        }

        return id;
    }

    @Override
    public void crearCuenta(CuentaBancaria cuenta) throws SQLException {
        final String INSERTAR = "INSERT INTO cuenta_bancaria (num_cuenta, id_usuario, tiene_bizum) VALUES (?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(INSERTAR);
        ps.setInt(1, cuenta.getNumCuenta());
        ps.setInt(2, cuenta.getIdUsuario());
        ps.setBoolean(3, cuenta.isTieneBizum());
        ps.executeUpdate();
    }

    public void hacerPium() {

    }

    // SIN IMPLEMENTAR
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

    // SIN IMPLEMENTAR
    @Override
    public void actualizarSaldo(int numCuenta, double cantidad) throws SQLException {
        final String CONSULTA = "UPDATE cuenta_bancaria SET saldo = ? WHERE num_cuenta = ?";
        try (PreparedStatement st = conn.prepareStatement(CONSULTA)) {
            st.setDouble(1, cantidad);
            st.setInt(2, numCuenta);
            st.executeUpdate();
        }
    }

}
