package es.dam.accesodatos.pium;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EntrarController {

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

    @FXML
    void cambiarInicio(ActionEvent event) throws IOException {
        App.setRoot("registrar");
    }

    @FXML
    void cambiarPantalla(ActionEvent event) throws IOException {
        App.setRoot("registrar");
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("registrar");
    }
}
