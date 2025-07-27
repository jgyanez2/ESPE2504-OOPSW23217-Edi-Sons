/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.espe.edu.wargame.model;

/**
 *
 * @author Jose Yanez
 */
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReportManager {
    private List<Report> reports;

    public ReportManager() {
        reports = new ArrayList<>();
    }

    public void createReport(User officer, String title, String reason, String content, LocalDateTime creationDate) {
        if (officer instanceof SuperiorOfficer || officer instanceof Director) {
            Report newReport = new Report(title, reason, content, creationDate);
            reports.add(newReport);
            newReport.saveToFile(); // Save the report to a .txt file
            System.out.println("Report created successfully.");
        } else {
            System.out.println("Only Superior Officers and Directors can create reports.");
        }
    }

    public void viewReports() {
        if (reports.isEmpty()) {
            System.out.println("No reports available.");
        } else {
            for (Report report : reports) {
                System.out.println(report);
                System.out.println("----------------------");
            }
        }
    }
}

