package es.dam.accesodatos.pium.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String nombre;
    private String apellido;
    private String telefono;
    private String contrasena;
    private List<CuentaBancaria> cuentas = new ArrayList<CuentaBancaria>();
    
    public Usuario(String nombre, String apellido, String telefono, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public List<CuentaBancaria> getCuentas() {
        return cuentas;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }
    
}
