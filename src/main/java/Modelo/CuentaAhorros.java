package Modelo;

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
    public String toString() {
        return "Cuenta de Ahorros " + getNumeroCuenta() + " | Saldo: " + saldo + " | Interés: " + interes;
    }



}
