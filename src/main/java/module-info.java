module es.dam.accesodatos.pium {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires mysql.connector.j;

    opens es.dam.accesodatos.pium to javafx.fxml;
    opens es.dam.accesodatos.pium.model to javafx.base;
    exports es.dam.accesodatos.pium;
}
