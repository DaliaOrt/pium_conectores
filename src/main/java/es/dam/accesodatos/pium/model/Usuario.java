package es.dam.accesodatos.pium.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String nombre;
    private String apellidos;
    private String contrasena;
    private List<CuentaBancaria> cuentas = new ArrayList<CuentaBancaria>();
    
    public Usuario(String nombre, String apellidos, String contrasena) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.contrasena = contrasena;
    }

    public Usuario() {}

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }


    public String getContrasena() {
        return contrasena;
    }

    public List<CuentaBancaria> getCuentas() {
        return cuentas;
    }

    public void agregarCuenta(CuentaBancaria cuenta) {
        cuentas.add(cuenta);
    }
    
}
