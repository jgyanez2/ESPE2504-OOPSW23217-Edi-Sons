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
public class SuperiorOfficer extends User {

    public SuperiorOfficer(String username, String password) {
        super(username, password, "SuperiorOfficer");
    }

    /**
     *
     * @param scanner
     * @param eventManager
     * @param gameTimer
     */
    public void mostrarMenu(Scanner scanner, EventManager eventManager, GameTimer gameTimer) {
        while (true) {
            System.out.println("\n=== Menu del Oficial Superior ===");
            System.out.println("1. Conversor de unidades");
            System.out.println("2. Ver eventos");
            System.out.println("3. Cerrar sesion");
            System.out.print("Seleccione una opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Enter the surface value: ");
                    double value = scanner.nextDouble();

                    System.out.println("Select the FROM unit:");
                    displayUnits();
                    int fromIndex = scanner.nextInt();
                    Area.areaUnit fromUnit = Area.areaUnit.values()[fromIndex];

                    System.out.println("Select the TO unit:");
                    displayUnits();
                    int toIndex = scanner.nextInt();
                    Area.areaUnit toUnit = Area.areaUnit.values()[toIndex];

                    double result = Area.convert(value, fromUnit, toUnit);

                    System.out.printf("Result: %.4f %s%n", result, toUnit.name());
                    break;
                case 2:
                    eventManager.viewEvents();
                    return;
                case 3:
                    System.out.println("Cerrando sesion...");
                    return; // Cerrar sesión y volver al menú principal
                default:
                    System.out.println("Opcion invalida. Intente nuevamente.");
            }
        }
    }

    private static void displayUnits() {
        Area.areaUnit[] units = Area.areaUnit.values();
        for (int i = 0; i < units.length; i++) {
            System.out.println(i + " - " + units[i].name());
        }
    }
}