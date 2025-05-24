package ec.edu.espe.wargame.view;

/**
 *
 * @author Jose Yanez
 */
import ec.espe.edu.wargame.model.*;
import java.util.Scanner;
import ec.espe.edu.wargame.model.AreaConverter;
import ec.espe.edu.wargame.model.AreaUnit;

public class WarGameSystem {

    private static Scanner scanner = new Scanner(System.in);
    private static AuthSystem authSystem = new AuthSystem();
    private static EventManager eventManager = new EventManager();
    private static ReportManager reportManager = new ReportManager();
    private static GameTimer gameTimer = null;

    public static void main(String[] args) {
        boolean running = true;
        
        while (running) {
            System.out.println("\n=== Sistema del Juego de Guerra ===");
            System.out.println("Seleccione su rol para iniciar sesión:");
            System.out.println("1. Director");
            System.out.println("2. Oficial Superior");
            System.out.println("3. Subordinado");
            System.out.println("4. Salir");
            System.out.print("Ingrese una opción: ");
            int roleOption = scanner.nextInt();
            scanner.nextLine();
            String role = "";
            String archivoCSV = "";

            switch (roleOption) {
                case 1:
                    role = "Director";
                    authSystem.loadUsersFromCSV("directors.csv", "Director");
                    break;
                case 2:
                    role = "SuperiorOfficer";
                    authSystem.loadUsersFromCSV("superiors.csv", "SuperiorOfficer");
                    break;
                case 3:
                    role = "Subordinate";
                    authSystem.loadUsersFromCSV("subordinates.csv", "Subordinate");
                    break;
                case 4:
                    System.out.println("Saliendo del sistema...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    continue;
            }

            System.out.print("Nombre de usuario: ");
            String username = scanner.nextLine();
            System.out.print("Contraseña: ");
            String password = scanner.nextLine();

            Object currentUser = authSystem.getUser(username, password);

            if (currentUser != null && currentUser.getClass().getSimpleName().equals(role)) {
                System.out.println("Inicio de sesión exitoso como " + role + ".");
                ((User) currentUser).mostrarMenu(scanner, eventManager, reportManager, gameTimer);
            } else {
                System.out.println("Credenciales incorrectas o usuario no pertenece al rol seleccionado.");
            }
        }
            System.out.println("=== Conversor de Superficies ===");

        System.out.print("Enter the surface value: ");
        double value = scanner.nextDouble();

        System.out.println("Select the FROM unit:");
        displayUnits();
        int fromIndex = scanner.nextInt();
        AreaUnit fromUnit = AreaUnit.values()[fromIndex];

        System.out.println("Select the TO unit:");
        displayUnits();
        int toIndex = scanner.nextInt();
        AreaUnit toUnit = AreaUnit.values()[toIndex];

        double result = AreaConverter.convert(value, fromUnit, toUnit);

        System.out.printf("Result: %.4f %s%n", result, toUnit.name());
    }

    private static void displayUnits() {
        AreaUnit[] units = AreaUnit.values();
        for (int i = 0; i < units.length; i++) {
            System.out.println(i + " - " + units[i].name());
        }
    }
}