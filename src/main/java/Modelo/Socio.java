package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Socio {
    private String nombre;
    private String cedula;
    private List <Cuenta> cuentas;

    public Socio(String nombre, String cedula) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.cuentas = new ArrayList<>();

    }
    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;

    }


    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }


    @Override
    public String toString() {
        return "Socio: " + nombre + " | Cedula: " + cedula;
    }

}
