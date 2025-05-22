/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.espe.edu.wargame.model;

import java.time.LocalDateTime;
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
     * @param reportManager
     * @param gameTimer
     */
    @Override
    public void mostrarMenu(Scanner scanner, EventManager eventManager, ReportManager reportManager, GameTimer gameTimer) {
        while (true) {
            System.out.println("\n=== Menú del Oficial Superior ===");
            System.out.println("1. Ver reportes");
            System.out.println("2. Crear reporte");
            System.out.println("3. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    reportManager.listReports();
                    break;
                case 2:
                    System.out.print("Título del reporte: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Motivo del reporte: ");
                    String motivo = scanner.nextLine();
                    System.out.print("Contenido del reporte: ");
                    String contenido = scanner.nextLine();
                    reportManager.createReport(this, titulo, motivo, contenido, LocalDateTime.now());
                    break;
                case 3:
                    System.out.println("Cerrando sesión...");
                    return; // Cerrar sesión y volver al menú principal
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }
}
