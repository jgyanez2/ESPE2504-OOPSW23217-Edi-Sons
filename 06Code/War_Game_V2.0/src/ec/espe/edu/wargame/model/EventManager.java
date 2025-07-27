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

public class EventManager {
    private List<Event> events;

    public EventManager() {
        events = new ArrayList<>();
    }

    public void createEvent(Director director, String title, String description, LocalDateTime eventDate) {
        if (director != null) {
            Event newEvent = new Event(title, description, eventDate);
            events.add(newEvent);
            System.out.println("Event created successfully.");
        } else {
            System.out.println("Only the Director can create events.");
        }
    }

    public void viewEvents() {
        if (events.isEmpty()) {
            System.out.println("No events available.");
        } else {
            for (Event event : events) {
                System.out.println(event);
                System.out.println("----------------------");
            }
        }
    }
}
