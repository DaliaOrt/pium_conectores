package es.dam.accesodatos.pium;

import java.io.IOException;
import java.sql.SQLException;

import es.dam.accesodatos.pium.model.Usuario;
import es.dam.accesodatos.pium.model.basedatos.BancoBDDAO;
import es.dam.accesodatos.pium.model.basedatos.BancoBDFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class RegistrarController extends ControladorVistas {

    @FXML
    private Button btnEntrar;

    @FXML
    private Button btnRegistrarme;

    @FXML
    private TextField tfApellido;

    @FXML
    private TextField tfContrasena;

    @FXML
    private TextField tfNombre;

    private static BancoBDDAO daoBanco;

    @FXML
    void cambiarEntrar(ActionEvent event) throws IOException {
        App.setRoot("entrar");
    }

    @FXML
    void registrarUsuario(ActionEvent event) throws IOException, SQLException {
        String nombre = tfNombre.getText().trim();
        String apellido = tfApellido.getText().trim();
        String contrasena = tfContrasena.getText().trim();
        iniciarConexion();

        if (!nombre.isEmpty() && !apellido.isEmpty() &&!contrasena.isEmpty()) {
            try {
                Usuario usuario = new Usuario(nombre, apellido, contrasena);
                daoBanco.registrarUsuario(usuario);
                mostrarAviso("Éxito", "Usuario registrado correctamente", AlertType.INFORMATION);
                App.setRoot("entrar");
            } catch (SQLException e) {
                mostrarAviso("Error", "Ocurrió un error al intentar registrar al usuario. Por favor, inténtalo nuevamente más tarde", AlertType.ERROR);
            }
        } else {
            mostrarAviso("Error", "Llena todos los campos", AlertType.ERROR);
        }
    }

    private void iniciarConexion () throws SQLException {
        daoBanco = BancoBDFactory.getDAO(BancoBDFactory.MODO_SQL);
    }

    private void mostrarAviso(String titulo, String mensaje, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}
