/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.wargame.model;

import java.util.Scanner;

/**
 *
 * @author Jose Yanez
 */
public class Director extends User {

    public Director(String username, String password) {
        super(username, password, "Director");
    }

    /**
     *
     * @param scanner
     * @param eventManager
     * @param gameTimer
     */
    @Override
    public void mostrarMenu(Scanner scanner, EventManager eventManager, GameTimer gameTimer) {
        while (true) {
            System.out.println("\n=== Menu del Director ===");
            System.out.println("1. Ver eventos");
            System.out.println("2. Conversor de unidades");
            System.out.println("3. Temporizador");
            System.out.println("4. Cerrar sesion");
            System.out.print("Seleccione una opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    eventManager.mostrarMenuEventos(scanner, this);
                    break;
                case 2:
                    System.out.print("Enter the surface value: ");
                    double value = scanner.nextDouble();

                    System.out.println("Select the FROM unit:");
                    displayUnits();
                    int fromIndex = scanner.nextInt();
                    AreaConverter.Unit fromUnit = AreaConverter.Unit.values()[fromIndex];

                    System.out.println("Select the TO unit:");
                    displayUnits();
                    int toIndex = scanner.nextInt();
                    AreaConverter.Unit toUnit = AreaConverter.Unit.values()[toIndex];

                    double result = AreaConverter.convert(value, fromUnit, toUnit);

                    System.out.printf("Result: %.4f %s%n", result, toUnit.name());
                    break;
                case 3:
                    if (gameTimer == null) {
                        System.out.println("\nGuia rapida de tiempos:");
                        System.out.println("1 hora   = 3600 segundos");
                        System.out.println("2 horas  = 7200 segundos");
                        System.out.println("3 horas  = 10800 segundos");
                        System.out.println("4 horas  = 14400 segundos");
                        System.out.println("5 horas  = 18000 segundos");
                        System.out.print("Duracion del temporizador (segundos): ");
                        long duracion = scanner.nextLong() * 1000;
                        scanner.nextLine();
                        gameTimer = new GameTimer(duracion);
                    }
                    gameTimer.mostrarMenuTemporizador(scanner);
                    break;
                case 4:
                    System.out.println("Cerrando sesion...");
                    return;
                default:
                    System.out.println("Opcion invalida. Intente nuevamente.");
            }
        }
    }

    private static void displayUnits() {
        AreaConverter.Unit[] units = AreaConverter.Unit.values();
        for (int i = 0; i < units.length; i++) {
            System.out.println(i + " - " + units[i].name());
        }
    }
}