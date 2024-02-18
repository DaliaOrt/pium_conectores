package es.dam.accesodatos.pium.model;

public class CuentaBancaria {

    private int id;
    private int idUsuario;
    private String telefono;
    private double saldo;
    private boolean tieneBizum;
    
    public CuentaBancaria(int id, int idUsuario, String telefono, double saldo, boolean tieneBizum) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.telefono = telefono;
        this.saldo = saldo;
        this.tieneBizum = tieneBizum;
    }

    public int getId() {
        return id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getTelefono() {
        return telefono;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean isTieneBizum() {
        return tieneBizum;
    }

    @Override
    public String toString() {
        return "CuentaBancaria [id=" + id + ", idUsuario=" + idUsuario + ", telefono=" + telefono + ", saldo=" + saldo
                + ", tieneBizum=" + tieneBizum + "]";
    }
    
}
