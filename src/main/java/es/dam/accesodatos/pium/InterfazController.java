package es.dam.accesodatos.pium;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import es.dam.accesodatos.pium.model.CuentaBancaria;
import es.dam.accesodatos.pium.model.Usuario;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

public class InterfazController {

    @FXML
    private Pane panelCrear;

    @FXML
    private Pane panelEntrar;

    @FXML
    private Pane panelIngresar;

    @FXML
    private Pane panelInicio;

    @FXML
    private Pane panelPium;

    @FXML
    private Pane panelRegistrar;

    @FXML
    private Pane panelSacar;

    @FXML
    void volverAtras(ActionEvent event) {
        panelInicio.toFront();
        tblCuenta_Inicio.refresh();
    }

    private static BancoBDDAO daoBanco;
    private ObservableList<CuentaBancaria> cuentas;

    // ---------- INICIAR SESIÓN ---------- //

    @FXML
    private Button btnEntrar_Entrar;

    @FXML
    private Button btnRegistrar_Entrar;

    @FXML
    private TextField tfNombre_Entrar;

    @FXML
    private PasswordField tfContrasena_Entrar;

    @FXML
    void iniciarSesion(ActionEvent event) throws SQLException {
        iniciarConexion();
        String nombre = tfNombre_Entrar.getText().trim();
        String contrasena = tfContrasena_Entrar.getText().trim();
        boolean inicioSesion = daoBanco.iniciarSesion(nombre, contrasena);

        if (inicioSesion) {
            lblNombre_Inicio.setText(nombre);
            panelInicio.toFront();

            cuentas = FXCollections.observableArrayList(daoBanco.obtenerCuentasUsuario(nombre));

            // Asignar las cuentas a la tabla en la pantalla de inicio
            tblCuenta_Inicio.setItems(cuentas);
            colID_Inicio.setCellValueFactory(new PropertyValueFactory<>("numCuenta"));
            colSaldo_Inicio.setCellValueFactory(new PropertyValueFactory<>("saldo"));

            // Asignar las cuentas a la tabla en la pantalla de ingresar
            tblCuenta_Ingresar.setItems(cuentas);
            colID_Ingresar.setCellValueFactory(new PropertyValueFactory<>("numCuenta"));
            colSaldo_Ingresar.setCellValueFactory(new PropertyValueFactory<>("saldo"));

            // Asignar las cuentas a la tabla en la pantalla de sacar dinero
            tblCuenta_Sacar.setItems(cuentas);
            colID_Sacar.setCellValueFactory(new PropertyValueFactory<>("numCuenta"));
            colSaldo_Sacar.setCellValueFactory(new PropertyValueFactory<>("saldo"));

        } else {
            mostrarAviso("Error",
                    "Inicio de sesión fallido. Por favor, verifica tus credenciales e inténtalo nuevamente",
                    AlertType.ERROR);
            tfNombre_Entrar.clear();
            tfContrasena_Entrar.clear();
        }
    }

    @FXML
    void cambiarRegistrar(ActionEvent event) {
        panelRegistrar.toFront();
    }

    // ---------- REGISTRARSE ---------- //

    @FXML
    private TextField tfNombre_Regist;

    @FXML
    private TextField tfTelefono_Regist;

    @FXML
    private TextField tfApellido_Regist;

    @FXML
    private TextField tfContrasena_Regist;

    @FXML
    private Button btnEntrar_Regist;

    @FXML
    private Button btnRegistrar_Regist;

    @FXML
    void registrarUsuario(ActionEvent event) throws SQLException {
        String nombre = tfNombre_Regist.getText().trim();
        String apellido = tfApellido_Regist.getText().trim();
        String telefono = tfTelefono_Regist.getText().trim();
        String contrasena = tfContrasena_Regist.getText().trim();
        iniciarConexion();

        if (!nombre.isEmpty() && !apellido.isEmpty() && !telefono.isEmpty() && !contrasena.isEmpty()) {
            try {
                Usuario usuario = new Usuario(nombre, apellido, telefono, contrasena);
                daoBanco.registrarUsuario(usuario);
                mostrarAviso("Éxito", "Usuario registrado correctamente", AlertType.INFORMATION);
                panelInicio.toFront();
            } catch (SQLException e) {
                mostrarAviso("Error",
                        "Ocurrió un error al intentar registrar al usuario. Por favor, inténtalo nuevamente más tarde",
                        AlertType.ERROR);
            }
        } else {
            mostrarAviso("Error", "Llena todos los campos", AlertType.ERROR);
        }
    }

    @FXML
    void cambiarEntrar(ActionEvent event) {
        panelEntrar.toFront();
    }

    // ---------- PANTALLA INICIO ---------- //

    @FXML
    private Label lblNombre_Inicio;

    @FXML
    private Button btnCuenta_Inicio;

    @FXML
    private Button btnIngresar_Inicio;

    @FXML
    private Button btnPium_Inicio;

    @FXML
    private Button btnSacar_Inicio;

    @FXML
    private TableView<CuentaBancaria> tblCuenta_Inicio;

    @FXML
    private TableColumn<CuentaBancaria, Integer> colID_Inicio;

    @FXML
    private TableColumn<CuentaBancaria, Double> colSaldo_Inicio;

    @FXML
    void crearCuenta(ActionEvent event) {
        panelCrear.toFront();
    }

    @FXML
    void sacarDinero(ActionEvent event) {
        panelSacar.toFront();
    }

    @FXML
    void hacerPium(ActionEvent event) {
        panelPium.toFront();
    }

    @FXML
    void ingresarDinero(ActionEvent event) {
        panelIngresar.toFront();
    }

    // ---------- CREAR CUENTA ---------- //

    @FXML
    public void initialize() {
        // Hacer que solamente se pueda seleccionar una opción
        ToggleGroup toggleGroup = new ToggleGroup();
        rbSi.setToggleGroup(toggleGroup);
        rbNo.setToggleGroup(toggleGroup);

        rbSi.setSelected(true);
    }

    @FXML
    private TextField tfNombre_Crear;

    @FXML
    private PasswordField tfContrasena_Crear;

    @FXML
    private RadioButton rbNo;

    @FXML
    private RadioButton rbSi;

    @FXML
    private Button btnCrear_Crear;

    @FXML
    private Button btnAtras_Crear;

    @FXML
    void crear(ActionEvent event) throws SQLException {
        String nombre = tfNombre_Crear.getText().trim();
        String contrasena = tfContrasena_Crear.getText().trim();
        boolean tieneBizum = rbSi.isSelected();
        boolean inicioSesion = daoBanco.iniciarSesion(nombre, contrasena);
        double saldo = 0;
        int idUsuario = daoBanco.obtenerIdUsuario(nombre);

        // Asignar un número de 4 cifras aleatorio a la nueva cuenta
        Random random = new Random();
        int numCuenta = random.nextInt(9000) + 1000;

        iniciarConexion();

        if (!nombre.isEmpty() && !contrasena.isEmpty()) {
            try {
                // Comprobar el usuario
                daoBanco.iniciarSesion(nombre, contrasena);
                if (inicioSesion) {
                    // Obtener cuentas del usuario porque solamente se quiere una cuenta con Pium
                    // activado por usuario
                    List<CuentaBancaria> cuentasUsuario = daoBanco.obtenerCuentasUsuario(nombre);
                    // Comprobar si alguna cuenta tiene Pium activado
                    boolean tieneCuentaConPium = false;
                    for (CuentaBancaria cuenta : cuentasUsuario) {
                        if (cuenta.isTieneBizum()) {
                            tieneCuentaConPium = true;
                            break;
                        }
                    }
                    if (tieneCuentaConPium) {
                        mostrarAviso("Error", "Ya tienes una cuenta con Pium activado", AlertType.ERROR);
                        return;
                    }
                    CuentaBancaria cuenta = new CuentaBancaria(numCuenta, idUsuario, saldo, tieneBizum);
                    daoBanco.crearCuenta(cuenta);
                    cuentas.add(cuenta);
                    mostrarAviso("Éxito", "Cuenta creada correctamente", AlertType.INFORMATION);
                    tfNombre_Crear.clear();
                    tfContrasena_Crear.clear();
                    panelInicio.toFront();
                } else {
                    mostrarAviso("Error", "Usuario o contraseña incorrectos", AlertType.ERROR);
                    tfNombre_Crear.clear();
                    tfContrasena_Crear.clear();
                }
            } catch (SQLException e) {
                mostrarAviso("Error",
                        "Ocurrió un error al intentar registrar al usuario. Por favor, inténtalo nuevamente más tarde",
                        AlertType.ERROR);
            }
        } else {
            mostrarAviso("Error", "Llena los campos de nombre y contraseña", AlertType.ERROR);
        }
    }

    // ---------- INGRESAR DINERO ---------- //

    @FXML
    private TextField tfCantidad_Ingresar;

    @FXML
    private Button btnConfirmar_Ingresar;

    @FXML
    private Button btnAtras_Ingresar;

    @FXML
    private TableView<CuentaBancaria> tblCuenta_Ingresar;

    @FXML
    private TableColumn<CuentaBancaria, Integer> colID_Ingresar;

    @FXML
    private TableColumn<CuentaBancaria, Double> colSaldo_Ingresar;

    @FXML
    void ingresar(ActionEvent event) throws SQLException {
        try {
            double cantidad = Double.parseDouble(tfCantidad_Ingresar.getText().trim());
            CuentaBancaria cuentaSeleccionada = tblCuenta_Ingresar.getSelectionModel().getSelectedItem();
            if (cuentaSeleccionada != null) {
                // Actualizar en la base de datos
                daoBanco.actualizarSaldo(cuentaSeleccionada.getNumCuenta(), cuentaSeleccionada.getSaldo() + cantidad);
                cuentaSeleccionada.setSaldo(cuentaSeleccionada.getSaldo() + cantidad);
                mostrarAviso("Éxito", "Ingreso realizado correctamente", AlertType.INFORMATION);
                tblCuenta_Ingresar.refresh();
            } else {
                mostrarAviso("Error", "Selecciona una cuenta de la tabla", AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            mostrarAviso("Error", "Introduce un número válido en la cantidad", AlertType.ERROR);
            tfCantidad_Ingresar.clear();
        }
    }

    // ---------- SACAR DINERO ---------- //

    @FXML
    private TextField tfCantidad_Sacar;

    @FXML
    private Button btnSacar_Sacar;

    @FXML
    private Button btnAtras_Sacar;

    @FXML
    private TableView<CuentaBancaria> tblCuenta_Sacar;

    @FXML
    private TableColumn<CuentaBancaria, Integer> colID_Sacar;

    @FXML
    private TableColumn<CuentaBancaria, Double> colSaldo_Sacar;

    @FXML
    void sacar(ActionEvent event) throws SQLException {
        double cantidad = Double.parseDouble(tfCantidad_Sacar.getText().trim());
        CuentaBancaria cuentaSeleccionada = tblCuenta_Sacar.getSelectionModel().getSelectedItem();
        if (cuentaSeleccionada != null) {
            //Comprobar que no se pueda sacar más dinero del que hay en la cuenta
            if (cantidad <= cuentaSeleccionada.getSaldo()) {
                // Actualizar en la base de datos
                daoBanco.actualizarSaldo(cuentaSeleccionada.getNumCuenta(), cuentaSeleccionada.getSaldo() - cantidad);
                cuentaSeleccionada.setSaldo(cuentaSeleccionada.getSaldo() - cantidad);
                mostrarAviso("Éxito", "Retirada de dinero realizada correctamente", AlertType.INFORMATION);
                tblCuenta_Sacar.refresh();
            } else {
                mostrarAviso("Error", "No puedes sacar más dinero del que tienes en la cuenta", AlertType.ERROR);
                tfCantidad_Sacar.clear();
            }
        } else {
            mostrarAviso("Error", "Selecciona una cuenta de la tabla", AlertType.ERROR);
        }
    }

    // ---------- HACER PIUM (TRANSFERIR) ---------- //

    @FXML
    private TextField tfCantidad_Pium;

    @FXML
    private TextField tfNombre_Pium;

    @FXML
    private Button btnComprobar_Pium;

    @FXML
    private Button btnAtras_Pium;

    @FXML
    private Button btnPium_Pium;

    @FXML
    private Label lblComprobar;

    @FXML
    void comprobar(ActionEvent event) {

    }

    @FXML
    void transferir(ActionEvent event) {

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
