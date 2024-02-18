package es.dam.accesodatos.pium;

import es.dam.accesodatos.pium.controller.Controlador;

public class ControladorVistas {

    private static ControladorVistas instancia = new ControladorVistas();
    protected static String nombre;
    protected Controlador controladorBanco;
    

    public void setControlador(Controlador controlador) {
        this.controladorBanco = controlador;
    }

    public static ControladorVistas getInstancia() {
        return instancia;
    }

    public String getNombreCompartido() {
        return nombre;
    }

    public void setNombreCompartido(String nombreCompartido) {
        this.nombre = nombreCompartido;
    }

}