/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.espe.edu.wargame.model;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 *
 * @author berna
 */
public class Director extends User {

    public Director(String username, String password) {
        super(username, password, "Director");
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
            System.out.println("\n=== Menú del Director ===");
            System.out.println("1. Ver eventos");
            System.out.println("2. Crear evento");
            System.out.println("3. Ver reportes");
            System.out.println("4. Crear reporte");
            System.out.println("5. Crear temporizador");
            System.out.println("6. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    eventManager.viewEvents();
                    break;
                case 2:
                    System.out.println("Creando evento...");
                    break;
                case 3:
                    reportManager.listReports();
                    break;
                case 4:
                    System.out.print("Título del reporte: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Motivo del reporte: ");
                    String motivo = scanner.nextLine();
                    System.out.print("Contenido del reporte: ");
                    String contenido = scanner.nextLine();
                    reportManager.createReport(this, titulo, motivo, contenido, LocalDateTime.now());
                    break;
                case 5:
                    System.out.print("Duración del temporizador (segundos): ");
                    long duracion = scanner.nextLong() * 1000;
                    gameTimer = new GameTimer(duracion);
                    gameTimer.startTimer();
                    break;
                case 6:
                    System.out.println("Cerrando sesión...");
                    return; // Cerrar sesión y volver al menú principal
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }
}
