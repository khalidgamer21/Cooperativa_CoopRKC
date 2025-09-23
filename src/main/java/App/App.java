package App;

import Gestion.Cooperativa;
import Modelo.Socio;
import Modelo.CuentaAhorros;
import Transacciones.Deposito;
import Transacciones.Retiro;
import Modelo.Cuenta;

import java.util.Scanner;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
public class App {

    public static void main(String[] args) {
            Cooperativa coop = new Cooperativa();

            int opcion;
            do {
                    String menu = """
                    --- MENÚ COOPERATIVA ---
                    1. Registrar socio
                    2. Abrir cuenta de ahorros
                    3. Realizar depósito
                    4. Realizar retiro
                    5. Listar socios (Stream)
                    6. Filtrar cuentas con saldo > 500000 (Stream)
                    7. Total de saldos en la cooperativa (Stream)
                    8. Salir
                    Seleccione una opción:
                    """;

                    opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));

                    switch (opcion) {
                            case 1 -> {
                                    String nombre = JOptionPane.showInputDialog("Nombre del socio:");
                                    String cedula = JOptionPane.showInputDialog("Cédula del socio:");
                                    Socio socio = new Socio(nombre, cedula);
                                    coop.registrarSocio(socio);
                                    JOptionPane.showMessageDialog(null, "✅ Socio registrado correctamente.");
                            }

                            case 2 -> {
                                    String cedula = JOptionPane.showInputDialog("Cédula del socio:");
                                    Socio socio = coop.getSocios().stream()
                                            .filter(s -> s.getCedula().equals(cedula))
                                            .findFirst()
                                            .orElse(null);

                                    if (socio != null) {
                                            String numCuenta = JOptionPane.showInputDialog("Número de cuenta:");
                                            double saldo = Double.parseDouble(JOptionPane.showInputDialog("Saldo inicial:"));
                                            double interes = Double.parseDouble(JOptionPane.showInputDialog("Interés (ej: 0.02):"));

                                            coop.abrirCuenta(socio, new CuentaAhorros(numCuenta, saldo, interes));
                                            JOptionPane.showMessageDialog(null, "✅ Cuenta creada correctamente.");
                                    } else {
                                            JOptionPane.showMessageDialog(null, "❌ Socio no encontrado.");
                                    }
                            }

                            case 3 -> {
                                    String cedula = JOptionPane.showInputDialog("Cédula del socio:");
                                    String numCuenta = JOptionPane.showInputDialog("Número de cuenta:");
                                    double monto = Double.parseDouble(JOptionPane.showInputDialog("Monto a depositar:"));

                                    Socio socio = coop.getSocios().stream()
                                            .filter(s -> s.getCedula().equals(cedula))
                                            .findFirst()
                                            .orElse(null);

                                    if (socio != null) {
                                            Cuenta cuenta = socio.getCuentas().stream()
                                                    .filter(c -> c.getNumeroCuenta().equals(numCuenta))
                                                    .findFirst()
                                                    .orElse(null);

                                            if (cuenta != null) {
                                                    new Deposito(cuenta, monto).ejecutar();
                                                    JOptionPane.showMessageDialog(null, "💰 Depósito exitoso.\nSaldo actual: " + cuenta.getSaldo());
                                            } else {
                                                    JOptionPane.showMessageDialog(null, "❌ Cuenta no encontrada.");
                                            }
                                    } else {
                                            JOptionPane.showMessageDialog(null, "❌ Socio no encontrado.");
                                    }
                            }

                            case 4 -> {
                                    String cedula = JOptionPane.showInputDialog("Cédula del socio:");
                                    String numCuenta = JOptionPane.showInputDialog("Número de cuenta:");
                                    double monto = Double.parseDouble(JOptionPane.showInputDialog("Monto a retirar:"));

                                    Socio socio = coop.getSocios().stream()
                                            .filter(s -> s.getCedula().equals(cedula))
                                            .findFirst()
                                            .orElse(null);

                                    if (socio != null) {
                                            Cuenta cuenta = socio.getCuentas().stream()
                                                    .filter(c -> c.getNumeroCuenta().equals(numCuenta))
                                                    .findFirst()
                                                    .orElse(null);

                                            if (cuenta != null) {
                                                    new Retiro(cuenta, monto).ejecutar();
                                                    JOptionPane.showMessageDialog(null, "💸 Retiro exitoso.\nSaldo actual: " + cuenta.getSaldo());
                                            } else {
                                                    JOptionPane.showMessageDialog(null, "❌ Cuenta no encontrada.");
                                            }
                                    } else {
                                            JOptionPane.showMessageDialog(null, "❌ Socio no encontrado.");
                                    }
                            }

                            case 5 -> {
                                    String socios = coop.getSocios().stream()
                                            .map(Socio::getNombre)
                                            .reduce("", (a, b) -> a + "\n" + b);

                                    JOptionPane.showMessageDialog(null, "👥 Lista de socios:\n" + socios);
                            }

                            case 6 -> {
                                    String cuentas = coop.getSocios().stream()
                                            .flatMap(s -> s.getCuentas().stream())
                                            .filter(c -> c.getSaldo() > 500000)
                                            .map(c -> "Cuenta " + c.getNumeroCuenta() + " → Saldo: " + c.getSaldo())
                                            .reduce("", (a, b) -> a + "\n" + b);

                                    JOptionPane.showMessageDialog(null, "🏦 Cuentas con saldo > 500000:\n" + cuentas);
                            }

                            case 7 -> {
                                    double total = coop.getSocios().stream()
                                            .flatMap(s -> s.getCuentas().stream())
                                            .mapToDouble(Cuenta::getSaldo)
                                            .sum();

                                    JOptionPane.showMessageDialog(null, "💲 Total de saldos en la cooperativa: " + total);
                            }

                            case 8 -> JOptionPane.showMessageDialog(null, "👋 Saliendo del sistema...");
                            default -> JOptionPane.showMessageDialog(null, "❌ Opción inválida.");
                    }

            } while (opcion != 8);

    }
}
