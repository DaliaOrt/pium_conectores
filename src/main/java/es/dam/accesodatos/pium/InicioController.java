package es.dam.accesodatos.pium;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import es.dam.accesodatos.pium.model.CuentaBancaria;
import es.dam.accesodatos.pium.model.basedatos.BancoBD;
import es.dam.accesodatos.pium.model.basedatos.BancoBDDAO;
import es.dam.accesodatos.pium.model.basedatos.BancoBDFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class InicioController extends ControladorVistas {

    @FXML
    private Button btnCuenta;

    @FXML
    private Button btnIngresar;

    @FXML
    private Button btnPium;

    @FXML
    private Button btnSacar;

    @FXML
    private TableColumn<CuentaBancaria, Integer> colID;

    @FXML
    private TableColumn<CuentaBancaria, Double> colSaldo;

    @FXML
    private Label lblNombre;

    @FXML
    private TableView<CuentaBancaria> tblCuenta;

    private BancoBDDAO daoBanco;
    private ObservableList<CuentaBancaria> cuentas;

    @FXML
    public void initialize() throws SQLException {

        daoBanco = new BancoBD();
        cuentas = FXCollections.observableArrayList();
        
        this.colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.colSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));

        iniciarConexion();
        
        // TO DO - NO FUNCIONA
        // cargarTabla(daoBanco.mostrarCuentas(nombre));

        tblCuenta.setItems(cuentas);

        if (EntrarController.nombre != null) {
            lblNombre.setText(EntrarController.nombre);
        } else {
            lblNombre.setText("---");
        }
    }

    // private void cargarTabla(List<CuentaBancaria> lista) {
    //     cuentas.addAll(lista);
    //     this.tblCuenta.setItems(cuentas);
    // }

    @FXML
    void crearCuenta(ActionEvent event) throws IOException {
        App.setRoot("crearCuenta");
    }

    // TO DO - implementar con vista nueva? 
    @FXML
    void hacerPium(ActionEvent event) {

    }

    // TO DO - intentar implementar con cuadro de alerta
    @FXML
    void ingresarDinero(ActionEvent event) {
        // TextField textField = new TextField();
        // textField.setPromptText("Escribe la cantidad");

        // VBox vbox = new VBox(textField);

        // Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        // alert.setTitle("Hacer un ingreso");
        // alert.setHeaderText("Por favor, escribe la cantidad a ingresar:");
        // alert.getDialogPane().setContent(vbox);

        // alert.showAndWait()
        // .filter(response -> response == ButtonType.OK)
        // .ifPresent(response -> {
        // try {
        // double cantidadSumar = Double.parseDouble(textField.getText());
        // double cantidadExistente = daoBanco.obtenerSaldo();
        // double nuevaCantidad = cantidadExistente + cantidadSumar;
        // daoBanco.ActualizarSaldo(nuevaCantidad);
        // System.out.println("Cantidad existente después de sumar: " + nuevaCantidad);
        // } catch (NumberFormatException e) {
        // System.out.println("Por favor, ingresa un número válido");
        // } catch (SQLException e) {
        // System.out.println("Error al conectar a la base de datos: " +
        // e.getMessage());
        // }
        // });
    }

    // TO DO - intentar implementar con cuadro de alerta
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

    private void iniciarConexion() throws SQLException {
        daoBanco = BancoBDFactory.getDAO(BancoBDFactory.MODO_SQL);
    }

}
