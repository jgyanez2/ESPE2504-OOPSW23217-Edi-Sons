/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.espe.edu.wargame.model;

import java.util.Scanner;

/**
 *
 * @author Jose Yanez
 */
public class Subordinate extends User {

    public Subordinate(String username, String password) {
        super(username, password, "Subordinate");
    }

    /**
     *
     * @param scanner
     * @param eventManager
     * @param reportManager
     * @param gameTimer
     */
    @Override
    public void mostrarMenu(Scanner scanner, EventManager eventManager, ReportManager reportManager, GameTimer gameTimer) {
        while (true) {
            System.out.println("\n=== Menú del Subordinado ===");
            System.out.println("1. Ver eventos");
            System.out.println("2. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    eventManager.viewEvents();
                    break;
                case 2:
                    System.out.println("Cerrando sesión...");
                    return; // Cerrar sesión y volver al menú principal
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }
}
