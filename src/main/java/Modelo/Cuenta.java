package Modelo;
import javax.swing.JOptionPane;

public class Cuenta {

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
    public void retirar(double monto) {
        if (monto > saldo) {
            JOptionPane.showMessageDialog(null,
                    "Error: saldo insuficiente en la cuenta " + numeroCuenta +
                            "\nSaldo actual: " + saldo,
                    "Error de Retiro",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            saldo -= monto;
            JOptionPane.showMessageDialog(null,
                    "Retiro exitoso.\nNuevo saldo en la cuenta " + numeroCuenta + ": " + saldo,
                    "Retiro Exitoso",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public String toString() {
        return "Cuenta " + numeroCuenta + " | Saldo: " + saldo;
    }

}
