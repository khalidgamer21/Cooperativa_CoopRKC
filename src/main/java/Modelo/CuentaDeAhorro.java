package Modelo;

public abstract class CuentaDeAhorro extends Cuenta {
    private Socio socio;
    private double tasaInteres;

    public CuentaDeAhorro(String numeroCuenta, double saldo, Socio socio, double tasaInteres) {
        super(numeroCuenta, saldo);
        this.socio = socio;
        this.tasaInteres = tasaInteres;
    }

    public Socio getSocio() {
        return socio;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    public void aplicarInteres() {
        double interes = getSaldo() * (tasaInteres / 100);
        depositar(interes);
    }





}
