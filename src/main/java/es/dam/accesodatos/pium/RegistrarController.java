package es.dam.accesodatos.pium;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class RegistrarController {

    @FXML
    private Button btnEntrar;

    @FXML
    private Button btnRegistrarme;

    @FXML
    private RadioButton rbNo;

    @FXML
    private RadioButton rbSi;

    @FXML
    private TextField tfApellido;

    @FXML
    private TextField tfContrasena;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfTelefono;

    @FXML
    void cambiarEntrar(ActionEvent event) throws IOException {
        App.setRoot("entrar");
    }

    @FXML
    void cambiarPantalla(ActionEvent event) throws IOException {
        App.setRoot("entrar");
    }

}
