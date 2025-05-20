/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.espe.edu.wargame.model;

/**
 *
 * @author berna
 */
import java.time.LocalDateTime;

public class Event {
    private String title;
    private String description;
    private LocalDateTime eventDate;

    public Event(String title, String description, LocalDateTime eventDate) {
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    @Override
    public String toString() {
        return "Event: " + title + "\nDescription: " + description + "\nDate: " + eventDate.toString();
    }
}
