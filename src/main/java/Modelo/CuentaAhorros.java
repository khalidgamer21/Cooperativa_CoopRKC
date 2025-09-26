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



    public void retirar(double monto) {
        // Mientras el monto sea mayor al saldo, volvemos a pedir un valor válido
        while (monto > saldo) {
            JOptionPane.showMessageDialog(null,
                    "Error: saldo insuficiente en la cuenta " + getNumeroCuenta() +
                            "\nSaldo actual: " + getSaldo(),
                    "Error de Retiro", JOptionPane.ERROR_MESSAGE);

            // Pedimos un nuevo monto
            String nuevoMonto = JOptionPane.showInputDialog(
                    null,
                    "Digite un nuevo monto a retirar.\nSaldo disponible: " + getSaldo(),
                    "Nuevo Retiro",
                    JOptionPane.QUESTION_MESSAGE
            );

            // Si el usuario cancela o deja vacío, salimos del método sin hacer nada
            if (nuevoMonto == null || nuevoMonto.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Operación cancelada.",
                        "Cancelar", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                monto = Double.parseDouble(nuevoMonto); // convertimos a número
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Monto inválido. Intente de nuevo.",
                        "Error de Formato", JOptionPane.ERROR_MESSAGE);
                return; // salimos para no quedar atrapados en un bucle
            }
        }

        // Si llegamos aquí, el monto es válido
        saldo -= monto;
        JOptionPane.showMessageDialog(null,
                "Retiro exitoso.\nNuevo saldo en la cuenta " + getNumeroCuenta() +
                        ": " + getSaldo(),
                "Retiro Exitoso", JOptionPane.INFORMATION_MESSAGE);
    }


    @Override
    public String toString() {
        return "Cuenta de Ahorros " + getNumeroCuenta() + " | Saldo: " + saldo + " | Interés: " + interes;
    }



}
