package es.dam.accesodatos.pium;

import java.io.IOException;
import java.sql.SQLException;

import es.dam.accesodatos.pium.model.CuentaBancaria;
import es.dam.accesodatos.pium.model.Usuario;
import es.dam.accesodatos.pium.model.basedatos.BancoBDDAO;
import es.dam.accesodatos.pium.model.basedatos.BancoBDFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class CrearCuenta {

    @FXML
    private Button btnAtras;

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

    private BancoBDDAO daoBanco;

    @FXML
    public void initialize() {
        // Hacer que solamente se pueda seleccionar una opción
        ToggleGroup toggleGroup = new ToggleGroup();
        rbSi.setToggleGroup(toggleGroup);
        rbNo.setToggleGroup(toggleGroup);

        rbSi.setSelected(true);
    }

    @FXML
    void crearCuenta(ActionEvent event) {
        // String nombre = tfNombre.getText().trim();
        // String telefono = tfTelefono.getText().trim();
        // boolean tieneBizum = rbSi.isSelected();
        // double saldo = 0;

        // iniciarConexion();

        // if (!nombre.isEmpty() && !telefono.isEmpty()) {
        //     try {
        //         // Comprobar el usuario
        //         Usuario usuario = daoBanco.obtenerUsuario(nombre);
        //         if (usuario != null) {
        //             CuentaBancaria cuenta = new CuentaBancaria(usuario.getidUsuario, telefono, saldo, tieneBizum);
        //             daoBanco.crearCuenta(cuenta);
        //             mostrarAviso("Éxito", "Usuario registrado correctamente", AlertType.INFORMATION);
        //             App.setRoot("inicio");
        //         } else {
        //             mostrarAviso("Error", "El nombre no existe", AlertType.ERROR);
        //         }
        //     } catch (SQLException e) {
        //         mostrarAviso("Error",
        //                 "Ocurrió un error al intentar registrar al usuario. Por favor, inténtalo nuevamente más tarde",
        //                 AlertType.ERROR);
        //     }
        // } else {
        //     mostrarAviso("Error", "Llena todos los campos", AlertType.ERROR);
        // }
    }

    @FXML
    void volverAtras(ActionEvent event) throws IOException {
        App.setRoot("inicio");
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
