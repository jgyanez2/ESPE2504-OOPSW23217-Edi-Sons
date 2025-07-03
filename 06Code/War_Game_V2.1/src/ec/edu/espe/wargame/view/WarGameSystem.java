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
import ec.espe.edu.wargame.model.Director;
import ec.espe.edu.wargame.model.EventManager;
import ec.espe.edu.wargame.model.GameTimer;
import ec.espe.edu.wargame.model.ReportManager;
import ec.espe.edu.wargame.model.SuperiorOfficer;
import java.util.Scanner;
import java.time.LocalDateTime;

public class WarGameSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthSystem authSystem = new AuthSystem();
        EventManager eventManager = new EventManager();
        ReportManager reportManager = new ReportManager();
        GameTimer gameTimer = null;
        String loginUsername = "";

        
        Director director = new Director("director", "admin123");
        authSystem.registerDirector(director);
        SuperiorOfficer superior = new SuperiorOfficer("superior1", "pass1");
        authSystem.registerSuperior(superior);

        while (true) {
            System.out.println("=== War Game System ===");
            System.out.println("1. Log In");
            System.out.println("2. Register (Director only)");
            System.out.println("3. View Events");
            System.out.println("4. Create Event (Director only)");
            System.out.println("5. View Reports");
            System.out.println("6. Create Report (Superior or Director)");
            System.out.println("7. Create Timer (Director only)");
            System.out.println("8. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Enter username: ");
                    loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();

                    if (authSystem.logIn(loginUsername, loginPassword)) {
                        System.out.println("Log in successful.");
                    } else {
                        System.out.println("Incorrect username or password.");
                    }
                    break;

                case 2:
                    System.out.println("Only the Director can register Superiors and Subordinates.");
                    break;

                case 3:
                    eventManager.viewEvents();
                    break;

                case 4:
                    if (loginUsername.equals("director")) {
                        System.out.print("Enter event title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter event description: ");
                        String description = scanner.nextLine();
                        System.out.print("Enter event date (YYYY-MM-DD HH:MM): ");
                        String date = scanner.nextLine();
                        LocalDateTime eventDate = LocalDateTime.parse(date);
                        eventManager.createEvent(director, title, description, eventDate);
                    } else {
                        System.out.println("Only the Director can create events.");
                    }
                    break;

                case 5:
                    
                    reportManager.listReports();
                    break;

                case 6:
                    if (loginUsername.equals("superior1") || loginUsername.equals("director")) {
                        System.out.print("Enter report title: ");
                        String titleReport = scanner.nextLine();
                        System.out.print("Enter report reason: ");
                        String reason = scanner.nextLine();
                        System.out.print("Enter report content: ");
                        String content = scanner.nextLine();
                        LocalDateTime creationDate = LocalDateTime.now();
                        reportManager.createReport(authSystem.logIn("superior1", "pass1") ? superior : director, titleReport, reason, content, creationDate);
                    } else {
                        System.out.println("Only Superiors or the Director can create reports.");
                    }
                    break;

                case 7:
                    if (loginUsername.equals("director")) {
                        System.out.print("Enter timer duration (in seconds): ");
                        long duration = scanner.nextLong() * 1000;
                        gameTimer = new GameTimer(duration);
                        gameTimer.startTimer();
                    } else {
                        System.out.println("Only the Director can create timers.");
                    }
                    break;

                case 8:
                    System.out.print("Enter report name to read (or 'exit' to quit): ");
                    String reportName = scanner.nextLine();
                    if (reportName.equals("exit")) {
                        System.out.println("Exiting the system.");
                        return;
                    } else {
                        reportManager.readReport(reportName);
                    }
                    break;
            }
        }
    }
}

