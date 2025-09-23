package Gestion;

import Modelo.Socio;
import Modelo.Cuenta;
import Modelo.CuentaAhorros;


import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class Cooperativa {


    private List<Socio> socios;

    public Cooperativa() {
        socios = new ArrayList<>();
    }


    public void registrarSocio(Socio socio) {
        socios.add(socio);
    }

    public void abrirCuenta(Socio socio, Cuenta cuenta) {
        boolean existe = socio.getCuentas().stream()
                .anyMatch(c -> c.getNumeroCuenta().equals(cuenta.getNumeroCuenta()));

        if (existe) {
            JOptionPane.showMessageDialog(null,
                    "Error: el socio ya tiene una cuenta con el número " + cuenta.getNumeroCuenta(),
                    "Error de Registro",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            socio.agregarCuenta(cuenta);
        }
    }

    public List<Socio> getSocios() {
        return socios;
    }
}

