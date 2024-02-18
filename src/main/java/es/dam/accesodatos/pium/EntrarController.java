package es.dam.accesodatos.pium;

import java.io.IOException;
import java.sql.SQLException;

import es.dam.accesodatos.pium.model.basedatos.BancoBDDAO;
import es.dam.accesodatos.pium.model.basedatos.BancoBDFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class EntrarController extends ControladorVistas {

    @FXML
    private Button btnEntrar;

    @FXML
    private Button btnRegistrar;

    @FXML
    private Button primaryButton;

    @FXML
    private TextField tfContrasena;

    @FXML
    private TextField tfUsuario;

    private static BancoBDDAO daoBanco;

    @FXML
    void cambiarRegistrar(ActionEvent event) throws IOException {
        App.setRoot("registrar");
    }

    @FXML
    void iniciarSesion(ActionEvent event) throws SQLException, IOException {
        iniciarConexion();
        String nombre = tfUsuario.getText().trim();
        String contrasena = tfContrasena.getText().trim();
        boolean inicioSesion = daoBanco.iniciarSesion(nombre, contrasena);

        if (inicioSesion) {
            //Guardar el nombre del usuario en controlador externo para usarlo en otra vista
            ControladorVistas.getInstancia().setNombreCompartido(nombre);
            //Método de cambio de escena que acepta un parámetro más
            App.setRooot("inicio", nombre);
        } else {
            mostrarAviso("Error",
                    "Inicio de sesión fallido. Por favor, verifica tus credenciales e inténtalo nuevamente",
                    AlertType.ERROR);
            tfUsuario.clear();
            tfContrasena.clear();
        }
    }

    private void iniciarConexion() throws SQLException {
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
