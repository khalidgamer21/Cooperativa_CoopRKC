package App;

import Gestion.Cooperativa;
import Modelo.Socio;
import Modelo.CuentaAhorros;
import Transacciones.Deposito;
import Transacciones.Retiro;
import Modelo.Cuenta;

import java.util.Scanner;
import java.util.stream.Collectors;


public class App {

    public static void main(String[] args) {
            Cooperativa coop = new Cooperativa();
            Scanner sc = new Scanner(System.in);
            int opcion;

            do {
                    System.out.println("\n--- MENÚ COOPERATIVA ---");
                    System.out.println("1. Registrar socio");
                    System.out.println("2. Abrir cuenta de ahorros");
                    System.out.println("3. Realizar depósito");
                    System.out.println("4. Realizar retiro");
                    System.out.println("5. Listar socios (Stream)");
                    System.out.println("6. Filtrar cuentas con saldo > 500000 (Stream)");
                    System.out.println("7. Total de saldos en la cooperativa (Stream)");
                    System.out.println("8. Salir");
                    System.out.print("Seleccione una opción: ");
                    opcion = sc.nextInt();
                    sc.nextLine(); // limpiar buffer

                    switch (opcion) {
                            case 1 -> {
                                    System.out.print("Nombre del socio: ");
                                    String nombre = sc.nextLine();
                                    System.out.print("Cédula: ");
                                    String cedula = sc.nextLine();
                                    coop.registrarSocio(new Socio(nombre, cedula));
                                    System.out.println("Socio registrado con éxito.");
                            }

                            case 2 -> {
                                    System.out.print("Cédula del socio: ");
                                    String cedula = sc.nextLine();
                                    Socio socio = coop.getSocios().stream()
                                            .filter(s -> s.getCedula().equals(cedula))
                                            .findFirst()
                                            .orElse(null);

                                    if (socio != null) {
                                            System.out.print("Número de cuenta: ");
                                            String numCuenta = sc.nextLine();
                                            System.out.print("Saldo inicial: ");
                                            double saldo = sc.nextDouble();
                                            System.out.print("Interés (ej: 0.02): ");
                                            double interes = sc.nextDouble();

                                            coop.abrirCuenta(socio, new CuentaAhorros(numCuenta, saldo, interes));
                                            System.out.println("Cuenta abierta con éxito.");
                                    } else {
                                            System.out.println("Socio no encontrado.");
                                    }
                            }

                            case 3 -> {
                                    System.out.print("Cédula del socio: ");
                                    String cedula = sc.nextLine();
                                    Socio socio = coop.getSocios().stream()
                                            .filter(s -> s.getCedula().equals(cedula))
                                            .findFirst()
                                            .orElse(null);

                                    if (socio != null && !socio.getCuentas().isEmpty()) {
                                            System.out.print("Número de cuenta: ");
                                            String numCuenta = sc.nextLine();
                                            Cuenta cuenta = socio.getCuentas().stream()
                                                    .filter(c -> c.getNumeroCuenta().equals(numCuenta))
                                                    .findFirst()
                                                    .orElse(null);

                                            if (cuenta != null) {
                                                    System.out.print("Monto a depositar: ");
                                                    double monto = sc.nextDouble();
                                                    new Deposito(cuenta, monto).ejecutar();
                                                    System.out.println("Depósito realizado.");
                                            } else {
                                                    System.out.println("Cuenta no encontrada.");
                                            }
                                    } else {
                                            System.out.println("Socio no encontrado o sin cuentas.");
                                    }
                            }

                            case 4 -> {
                                    System.out.print("Cédula del socio: ");
                                    String cedula = sc.nextLine();
                                    Socio socio = coop.getSocios().stream()
                                            .filter(s -> s.getCedula().equals(cedula))
                                            .findFirst()
                                            .orElse(null);

                                    if (socio != null && !socio.getCuentas().isEmpty()) {
                                            System.out.print("Número de cuenta: ");
                                            String numCuenta = sc.nextLine();
                                            Cuenta cuenta = socio.getCuentas().stream()
                                                    .filter(c -> c.getNumeroCuenta().equals(numCuenta))
                                                    .findFirst()
                                                    .orElse(null);

                                            if (cuenta != null) {
                                                    System.out.print("Monto a retirar: ");
                                                    double monto = sc.nextDouble();
                                                    sc.nextLine(); // ⚠️ importante limpiar buffer
                                                    new Retiro(cuenta, monto).ejecutar();
                                            } else {
                                                    System.out.println("Cuenta no encontrada.");
                                            }
                                    } else {
                                            System.out.println("Socio no encontrado o sin cuentas.");
                                    }
                            }


                            case 5 -> {
                                    System.out.println("\n=== Lista de socios ===");
                                    coop.getSocios().stream()
                                            .map(Socio::getNombre)
                                            .forEach(System.out::println);
                            }

                            case 6 -> {
                                    System.out.println("\n=== Cuentas con saldo > 500000 ===");
                                    coop.getSocios().stream()
                                            .flatMap(s -> s.getCuentas().stream())
                                            .filter(c -> c.getSaldo() > 500000)
                                            .forEach(System.out::println);
                            }

                            case 7 -> {
                                    double total = coop.getSocios().stream()
                                            .flatMap(s -> s.getCuentas().stream())
                                            .mapToDouble(Cuenta::getSaldo)
                                            .sum();
                                    System.out.println("Total en la cooperativa: " + total);
                            }

                            case 8 -> System.out.println("Saliendo del sistema...");

                            default -> System.out.println("Opción inválida.");
                    }
            } while (opcion != 8);

            sc.close();

    }
}
