package es.dam.accesodatos.pium.model;

public class CuentaBancaria {

    private int id;
    private int idUsuario;
    private double saldo;
    
    public CuentaBancaria(int id, int idUsuario, double saldo) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public double getSaldo() {
        return saldo;
    }

    @Override
    public String toString() {
        return "CuentaBancaria [id=" + id + ", idUsuario=" + idUsuario + ", saldo=" + saldo + "]";
    }
    
}
