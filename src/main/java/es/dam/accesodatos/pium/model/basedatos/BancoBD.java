package es.dam.accesodatos.pium.model.basedatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        final String INSERTAR = "INSERT INTO usuarios (nombre, apellido, telefono, contrasena, tiene_bizum) VALUES (?,?,?,?,?)";

        PreparedStatement ps = null;

        ps = conn.prepareStatement(INSERTAR);
        ps.setString(1, usuario.getNombre());
        ps.setString(2, usuario.getApellidos());
        ps.setString(3, usuario.getTelefono());
        ps.setString(4, usuario.getContrasena());
        ps.setBoolean(5, usuario.getTieneBizum());
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

}
