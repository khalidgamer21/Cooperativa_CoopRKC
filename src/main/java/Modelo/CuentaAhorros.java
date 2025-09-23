package Modelo;
import javax.swing.JOptionPane;
public class CuentaAhorros extends Cuenta {
    ;
    private double interes;

    public CuentaAhorros(String numeroCuenta, double saldoInicial, double interes) {
        super(numeroCuenta, saldoInicial);
        this.interes = interes;
    }


    public void aplicarInteres() {
        saldo += saldo * interes;
    }
    @Override
    public void retirar(double monto) {
        if (monto > saldo) {
            JOptionPane.showMessageDialog(null,
                    "Error: saldo insuficiente en la cuenta " + getNumeroCuenta() +
                            "\nSaldo actual: " + saldo,
                    "Error de Retiro",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            saldo -= monto;
            JOptionPane.showMessageDialog(null,
                    "Retiro exitoso.\nNuevo saldo en la cuenta " + getNumeroCuenta() +
                            ": " + saldo,
                    "Retiro Exitoso",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }


    @Override
    public String toString() {
        return "Cuenta de Ahorros " + getNumeroCuenta() + " | Saldo: " + saldo + " | Interés: " + interes;
    }



}
