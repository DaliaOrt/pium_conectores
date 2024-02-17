package es.dam.accesodatos.pium.model.basedatos;

import java.sql.SQLException;

public class BancoBDFactory {

    public static final int MODO_TEST = 0;
    public static final int MODO_SQL = 1;

    public static BancoBDDAO getDAO(int modo) throws SQLException {
        switch (modo) {
            case MODO_SQL:
                return new BancoBD();
            default:
                return new BancoBDTest();
        }
    }

}
