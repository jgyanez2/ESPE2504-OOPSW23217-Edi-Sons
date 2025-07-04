/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.espe.edu.wargame.model;

/**
 *
 * @author Jose Yanez
 */
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class ReportManager {

    
    public void createReport(Object user, String title, String reason, String content, LocalDateTime creationDate) {
        try {
            
            String fileName = "report_" + creationDate.toString().replace(":", "-") + ".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            
            writer.write("Title: " + title);
            writer.newLine();
            writer.write("Reason: " + reason);
            writer.newLine();
            writer.write("Content: " + content);
            writer.newLine();
            writer.write("Created at: " + creationDate.toString());
            writer.newLine();
            writer.close();
            
            System.out.println("Report saved as: " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving the report.");
        }
    }

    
    public void listReports() {
        File folder = new File(".");
        File[] listOfFiles = folder.listFiles((dir, name) -> name.startsWith("report_") && name.endsWith(".txt"));
        
        if (listOfFiles != null && listOfFiles.length > 0) {
            System.out.println("Reports available:");
            for (File file : listOfFiles) {
                System.out.println(file.getName());
            }
        } else {
            System.out.println("No reports found.");
        }
    }

    
    public void readReport(String reportName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(reportName));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading the report.");
        }
    }
}


