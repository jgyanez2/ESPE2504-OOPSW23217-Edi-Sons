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

public class Report {
    private String title;
    private String reason;
    private String content;
    private LocalDateTime creationDate;

    public Report(String title, String reason, String content, LocalDateTime creationDate) {
        this.title = title;
        this.reason = reason;
        this.content = content;
        this.creationDate = creationDate;
    }

    public String getTitle() {
        return title;
    }

    public String getReason() {
        return reason;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return "Report: " + title + "\nReason: " + reason + "\nContent: " + content + "\nCreation Date: " + creationDate.toString();
    }

    // Save report to file
    public void saveToFile() {
        try {
            java.nio.file.Files.write(
                java.nio.file.Paths.get(this.title + "_report.txt"),
                (this.toString()).getBytes()
            );
            System.out.println("Report successfully saved as " + this.title + "_report.txt");
        } catch (Exception e) {
            System.out.println("There was an error saving the report.");
        }
    }
}

