package ec.edu.espe.wargame.model;

import java.util.Scanner;

public class Subordinate extends User {

    public Subordinate(String username, String password) {
        super(username, password, "Subordinate");
    }

    /**
     *
     * @param scanner
     * @param eventManager
     * @param gameTimer
     */
    public void mostrarMenu(Scanner scanner, EventManager eventManager, GameTimer gameTimer) {
        while (true) {
            System.out.println("\n=== Menu del Subordinado ===");
            System.out.println("1. Ver eventos");
            System.out.println("2. Conversor de unidades");
            System.out.println("3. Cerrar sesion");
            System.out.print("Seleccione una opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> {
                    eventManager.viewEvents();
                    return;
                }
                case 2 -> {
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
                }
                case 3 -> {
                    System.out.println("Cerrando sesion...");
                    return;
                }
                default ->
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