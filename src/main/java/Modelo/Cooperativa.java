package Modelo;

import java.util.List;

import java.util.ArrayList;

public class Cooperativa {
    private ArrayList<Socio> socios = new ArrayList<>();
    public void agregarSocio(Socio socio) {
        socios.add(socio);
    }

    public List<Socio> getListaSocios() {
        return new ArrayList<>(socios);
    }

    public Socio buscarSocio(String cedula) {
        return socios.stream()
                .filter(s -> s.getCedula().equals(cedula))
                .findFirst()
                .orElse(null);
    }
}

