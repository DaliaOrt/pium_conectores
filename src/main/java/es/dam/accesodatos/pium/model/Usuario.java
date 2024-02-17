package es.dam.accesodatos.pium.model;

public class Usuario {

    private String nombre;
    private String apellidos;
    private String telefono;
    private String contrasena;
    private boolean tieneBizum;
    
    public Usuario(String nombre, String apellidos, String telefono, String contrasena, boolean tieneBizum) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.tieneBizum = tieneBizum;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getContrasena() {
        return contrasena;
    }

    public boolean getTieneBizum() {
        return tieneBizum;
    }

    @Override
    public String toString() {
        return "Usuario [nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono
                + ", contrasena=" + contrasena + ", tieneBizum=" + tieneBizum + "]";
    }
    
}
