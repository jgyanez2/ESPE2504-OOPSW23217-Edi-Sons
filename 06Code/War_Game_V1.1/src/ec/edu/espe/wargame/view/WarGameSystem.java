/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ec.edu.espe.wargame.view;

/**
 *
 * @author Jose Yanez
 */
import ec.espe.edu.wargame.model.AuthSystem;
import ec.espe.edu.wargame.model.SuperiorOfficer;
import java.util.Scanner;

public class WarGameSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthSystem authSystem = new AuthSystem();

        while (true) {
            System.out.println("=== Sistema de Autenticación de Oficiales Superiores ===");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (option) {
                case 1 -> {
                    System.out.print("Ingrese el nombre del oficial: ");
                    String loginName = scanner.nextLine();
                    System.out.print("Ingrese la contraseña: ");
                    String loginPassword = scanner.nextLine();

                    if (authSystem.logInOfficer(loginName, loginPassword)) {
                        System.out.println("✅ Inicio de sesión exitoso. ¡Bienvenido, Oficial " + loginName + "!");
                    } else {
                        System.out.println("❌ Nombre o contraseña incorrectos.");
                    }
                }

                case 2 -> {
                    System.out.print("Ingrese un nombre de oficial: ");
                    String newName = scanner.nextLine();
                    System.out.print("Ingrese una contraseña: ");
                    String newPassword = scanner.nextLine();

                    SuperiorOfficer newOfficer = new SuperiorOfficer(newName, newPassword);

                    if (authSystem.registerOfficer(newOfficer)) {
                        System.out.println("✅ Registro exitoso. Ahora puede iniciar sesión.");
                    } else {
                        System.out.println("⚠ Ese nombre de oficial ya está en uso.");
                    }
                }

                case 3 -> {
                    System.out.println("Saliendo del sistema. ¡Hasta luego!");
                    return;
                }

                default -> System.out.println("❗ Opción no válida. Intente de nuevo.");
            }

            System.out.println(); // espacio
        }
    }
}