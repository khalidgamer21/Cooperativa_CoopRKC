package Transacciones;

import Modelo.Cuenta;

public class Retiro implements Transaccion {
    private Cuenta cuenta;
    private double monto;

    public Retiro(Cuenta cuenta, double monto) {
        this.cuenta = cuenta;
        this.monto = monto;
    }

    @Override
    public void ejecutar() {
        cuenta.retirar(monto);
    }

    @Override
    public double getMonto() {
        return monto;
    }
}
