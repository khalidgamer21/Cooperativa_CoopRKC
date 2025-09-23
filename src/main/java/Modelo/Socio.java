package Modelo;

public class Socio {
    private String nombre;
    private String cedula;


    public Socio(String nombre, String cedula) {
        this.nombre = nombre;
        this.cedula = cedula;
    }
    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }


}
