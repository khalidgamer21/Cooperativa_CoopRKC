package Modelo;
import javax.swing.JOptionPane;

public abstract class Cuenta {

    private String numeroCuenta;
    protected double saldo;

    public Cuenta(String numeroCuenta, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
    }


    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }
    public void depositar(double monto) {
        saldo += monto;
    }

    public abstract void retirar(double monto);

    @Override
    public String toString() {
        return "Cuenta " + numeroCuenta + " | Saldo: " + saldo;
    }

}
