package es.dam.accesodatos.pium;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import es.dam.accesodatos.pium.controller.Controlador;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        scene = new Scene(loadFXML("entrar"), 760, 550);
        stage.setScene(scene);
        stage.show();

        // Llama al método iniciarBaseDatos() al iniciar la aplicación
        // Para crear base de datos, tablas y registros
        Controlador controlador = new Controlador();
        controlador.iniciarBaseDatos();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    //Método de cambio de escena que acepta un parámetro nuevo
    public static void setRooot(String fxml, String nombre) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}