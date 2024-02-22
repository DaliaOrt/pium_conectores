package es.dam.accesodatos.pium.model;

public class CuentaBancaria {

    private int id;
    private int idUsuario;
    private double saldo;
    private boolean tieneBizum;
    
    public CuentaBancaria(int id, int idUsuario, double saldo, boolean tieneBizum) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.saldo = saldo;
        this.tieneBizum = tieneBizum;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean isTieneBizum() {
        return tieneBizum;
    }

    public int getId() {
        return id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }
    
}
