package Transacciones;

import Modelo.Cuenta;
public class Deposito implements  Transaccion {
    private Cuenta cuenta;
    private double monto;

    public Deposito(Cuenta cuenta, double monto) {
        this.cuenta = cuenta;
        this.monto = monto;
    }
    @Override
    public void ejecutar() {
        cuenta.depositar(monto);
    }

    @Override
    public double getMonto() {
        return monto;
    }
}
