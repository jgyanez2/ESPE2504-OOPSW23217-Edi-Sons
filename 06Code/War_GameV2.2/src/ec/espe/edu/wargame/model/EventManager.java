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
import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private List<Event> events;  
    private static final String FILE_NAME = "events.csv";  

    public EventManager() {
        events = new ArrayList<>();
        loadEventsFromFile();  
    }

    
    private void loadEventsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                
                String[] eventData = line.split(",");
                String title = eventData[0];
                String description = eventData[1];
                LocalDateTime eventDate = LocalDateTime.parse(eventData[2]);
                String creatorUsername = eventData[3];

                
                Event event = new Event(title, description, eventDate, new Director(creatorUsername, ""));
                events.add(event);
            }
        } catch (IOException e) {
            System.out.println("Error reading events from file.");
        }
    }

    
    private void saveEventToFile(Event event) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            
            writer.write(event.getTitle() + "," + event.getDescription() + "," + event.getEventDate() + "," + event.getCreator().getUsername());
            writer.newLine();  
        } catch (IOException e) {
            System.out.println("Error saving event to file.");
        }
    }

    
    public void createEvent(Director director, String title, String description, LocalDateTime eventDate) {
        Event newEvent = new Event(title, description, eventDate, director);
        events.add(newEvent);  
        saveEventToFile(newEvent);  
        System.out.println("Event created: " + title);
    }

    
    public void viewEvents() {
        if (events.isEmpty()) {
            System.out.println("No events available.");
        } else {
            System.out.println("=== Events ===");
            for (Event event : events) {
                System.out.println("Title: " + event.getTitle());
                System.out.println("Description: " + event.getDescription());
                System.out.println("Date: " + event.getEventDate());
                System.out.println("Created by: " + event.getCreator().getUsername());
                System.out.println();
            }
        }
    }
}
