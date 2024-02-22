package es.dam.accesodatos.pium.model;

public class CuentaBancaria {

    private int numCuenta;
    private int idUsuario;
    private double saldo;
    private boolean tieneBizum;
    
    public CuentaBancaria(int numCuenta, int idUsuario, double saldo, boolean tieneBizum) {
        this.numCuenta = numCuenta;
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

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(int numCuenta) {
        this.numCuenta = numCuenta;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setTieneBizum(boolean tieneBizum) {
        this.tieneBizum = tieneBizum;
    }
    
}
