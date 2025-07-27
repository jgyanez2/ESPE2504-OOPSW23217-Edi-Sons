package ec.edu.espe.wargame.model;

/**
 *
 * @author Jose Yanez
 */
import ec.edu.espe.wargame.model.*;
import ec.edu.espe.wargame.model.AuthSystem;
import ec.edu.espe.wargame.model.EventManager;
import ec.edu.espe.wargame.model.GameTimer;
import ec.edu.espe.wargame.model.User;
import java.util.Scanner;

public class WarGameSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthSystem authSystem = new AuthSystem();
        EventManager eventManager = new EventManager();
        GameTimer gameTimer = null;

        while (true) {
            System.out.println("\n=== Sistema del Juego de Guerra ===");
            System.out.println("Seleccione su rol para iniciar sesion:");
            System.out.println("1. Director");
            System.out.println("2. Oficial Superior");
            System.out.println("3. Subordinado");
            System.out.println("4. Salir");
            System.out.print("Ingrese una opcion: ");
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
                    return;
                default:
                    System.out.println("Opcion invalida. Intente de nuevo.");
                    continue;
            }

            System.out.print("Nombre de usuario: ");
            String username = scanner.nextLine();
            System.out.print("Contraseña: ");
            String password = scanner.nextLine();

            Object currentUser = authSystem.getUser(username, password);

            if (currentUser != null && currentUser.getClass().getSimpleName().equals(role)) {
                System.out.println("Inicio de sesion exitoso como " + role + ".");
                ((User) currentUser).mostrarMenu(scanner, eventManager, gameTimer);
            } else {
                System.out.println("Credenciales incorrectas o usuario no pertenece al rol seleccionado.");
            }
        }
    }
}