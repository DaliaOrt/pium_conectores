package es.dam.accesodatos.pium;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class CrearCuenta {

    @FXML
    private Button btnCrear;

    @FXML
    private RadioButton rbNo;

    @FXML
    private RadioButton rbSi;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfTelefono;

    @FXML
    public void initialize() {
        //Hacer que solamente se pueda seleccionar una opción
        ToggleGroup toggleGroup = new ToggleGroup();
        rbSi.setToggleGroup(toggleGroup);
        rbNo.setToggleGroup(toggleGroup);

        rbSi.setSelected(true);
    }

    @FXML
    void crearCuenta(ActionEvent event) {
        String nombre = tfNombre.getText().trim();
        String telefono = tfTelefono.getText().trim();
        boolean tieneBizum = rbSi.isSelected();
    }

}

