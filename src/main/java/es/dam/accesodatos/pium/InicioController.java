package es.dam.accesodatos.pium;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class InicioController {

    @FXML
    private Button btnCuenta;

    @FXML
    private Button btnIngresar;

    @FXML
    private Button btnPium;

    @FXML
    private Button btnSacar;

    @FXML
    void crearCuenta(ActionEvent event) {

    }

    @FXML
    void hacerPium(ActionEvent event) {

    }

    @FXML
    void ingresarDinero(ActionEvent event) {

    }

    @FXML
    void sacarDinero(ActionEvent event) {

    }

    private void mostrarAviso(String titulo, String mensaje, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}
