package es.dam.accesodatos.pium.model.basedatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InicioBD implements InicioBDDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NOMBRE = "sistema_bancario";
    private static final String USER = "patata";
    private static final String PASSWORD = "patata";

    @Override
    public void inicializarBaseDatos() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Crear la base de datos si no existe
            crearBaseDatos(conn);
            // Seleccionar la base de datos
            conn.setCatalog(DB_NOMBRE);
            // Crear las tablas si no existen
            crearTablaUsuarios(conn);
            crearTablaCuentasBancarias(conn);
            // Insertar datos si las tablas están vacías
            if (tablaUsuariosVacia(conn)) {
                insertarDatosUsuarios(conn);
            }
            if (tablaCuentasBancariasVacia(conn)) {
                insertarDatosCuentasBancarias(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void crearBaseDatos(Connection conn) throws SQLException {
        try (PreparedStatement st = conn.prepareStatement("CREATE DATABASE IF NOT EXISTS " + DB_NOMBRE)) {
            st.executeUpdate();
        }
    }

    private void crearTablaUsuarios(Connection conn) throws SQLException {
        try (PreparedStatement st = conn.prepareStatement(
                "CREATE TABLE IF NOT EXISTS usuarios (id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(100) NOT NULL, apellido VARCHAR(100) NOT NULL, contrasena VARCHAR(100) NOT NULL)")) {
            st.executeUpdate();
        }
    }

    private void crearTablaCuentasBancarias(Connection conn) throws SQLException {
        try (PreparedStatement st = conn.prepareStatement(
                "CREATE TABLE IF NOT EXISTS cuentas_bancarias (id INT AUTO_INCREMENT PRIMARY KEY, id_usuario INT NOT NULL, telefono VARCHAR(15) UNIQUE NOT NULL, saldo DECIMAL(10,2) NOT NULL DEFAULT 0, tiene_bizum BOOLEAN NOT NULL, FOREIGN KEY (id_usuario) REFERENCES usuarios(id))")) {
            st.executeUpdate();
        }
    }

    private boolean tablaUsuariosVacia(Connection conn) throws SQLException {
        try (PreparedStatement st = conn.prepareStatement("SELECT COUNT(*) FROM usuarios");
             ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
        }
        return false;
    }

    private boolean tablaCuentasBancariasVacia(Connection conn) throws SQLException {
        try (PreparedStatement st = conn.prepareStatement("SELECT COUNT(*) FROM cuentas_bancarias");
             ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
        }
        return false;
    }

    private void insertarDatosUsuarios(Connection conn) throws SQLException {
        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO usuarios (nombre, apellido, contrasena) VALUES (?, ?, ?)")) {
            st.setString(1, "Juan");
            st.setString(2, "Pérez");
            st.setString(3, "juan");
            st.addBatch();

            st.setString(1, "María");
            st.setString(2, "López");
            st.setString(3, "maria");
            st.addBatch();

            st.setString(1, "Carlos");
            st.setString(2, "García");
            st.setString(3, "carlos");
            st.addBatch();

            st.executeBatch();
        }
    }

    private void insertarDatosCuentasBancarias(Connection conn) throws SQLException {
        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO cuentas_bancarias (id_usuario, telefono, saldo, tiene_bizum) VALUES (?, ?, ?, ?)")) {
            st.setInt(1, 1);
            st.setString(2, "123456789");
            st.setDouble(3, 1500.00);
            st.setBoolean(4, true);
            st.addBatch();

            st.setInt(1, 1);
            st.setString(2, "987654321");
            st.setDouble(3, 2500.00);
            st.setBoolean(4, false);
            st.addBatch();

            st.setInt(1, 2);
            st.setString(2, "555666777");
            st.setDouble(3, 3000.00);
            st.setBoolean(4, true);
            st.addBatch();

            st.setInt(1, 3);
            st.setString(2, "999888777");
            st.setDouble(3, 500.00);
            st.setBoolean(4, false);
            st.addBatch();

            st.setInt(1, 3);
            st.setString(2, "111222333");
            st.setDouble(3, 750.00);
            st.setBoolean(4, true);
            st.addBatch();

            st.executeBatch();
        }
    }
}
