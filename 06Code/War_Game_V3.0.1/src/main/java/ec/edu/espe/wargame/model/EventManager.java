/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.wargame.model;

/**
 *
 * @author Mateo Unda
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EventManager {
    private final List<Event> events;
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
                if (eventData.length == 3) {
                    String title = eventData[0];
                    String description = eventData[1];
                    String creatorUsername = eventData[2];
                    Event event = new Event(title, description, new Director(creatorUsername, ""));
                    events.add(event);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer los eventos desde el archivo.");
        }
    }

    private void saveEventToFile(Event event) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(event.getTitle() + "," + event.getDescription() + "," + event.getCreator().getUsername());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar el evento en el archivo.");
        }
    }

    private void overwriteFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Event event : events) {
                writer.write(event.getTitle() + "," + event.getDescription() + "," + event.getCreator().getUsername());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al sobrescribir el archivo de eventos.");
        }
    }

    public void createEvent(Director director, String title, String description) {
        Event newEvent;
        newEvent = new Event(title, description, director);
        events.add(newEvent);
        saveEventToFile(newEvent);
        System.out.println("Evento creado: " + title);
    }

    public void viewEvents() {
        if (events.isEmpty()) {
            System.out.println("No hay eventos disponibles.");
        } else {
            System.out.println("=== Lista de eventos ===");
            for (Event event : events) {
                System.out.println("Titulo: " + event.getTitle());
                System.out.println("Descripcion: " + event.getDescription());
                System.out.println("Creado por: " + event.getCreator().getUsername());
                System.out.println();
            }
        }
    }

    public void searchEventByTitle(String title) {
        for (Event event : events) {
            if (event.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Titulo: " + event.getTitle());
                System.out.println("Descripcion: " + event.getDescription());
                System.out.println("Creado por: " + event.getCreator().getUsername());
                return;
            }
        }
        System.out.println("Evento no encontrado.");
    }

    public void deleteEventByTitle(String title) {
        Event eventToRemove = null;
        for (Event event : events) {
            if (event.getTitle().equalsIgnoreCase(title)) {
                eventToRemove = event;
                break;
            }
        }
        if (eventToRemove != null) {
            events.remove(eventToRemove);
            overwriteFile();
            System.out.println("Evento eliminado exitosamente.");
        } else {
            System.out.println("Evento no encontrado.");
        }
    }

    public void mostrarMenuEventos(Scanner scanner, Director director) {
        while (true) {
            System.out.println("\n=== Menu de Eventos ===");
            System.out.println("1. Ver eventos");
            System.out.println("2. Crear evento");
            System.out.println("3. Buscar evento por titulo");
            System.out.println("4. Eliminar evento por titulo");
            System.out.println("5. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    viewEvents();
                    break;
                case 2:
                    System.out.print("Ingrese el titulo del evento: ");
                    String title = scanner.nextLine();
                    System.out.print("Ingrese la descripcion del evento: ");
                    String description = scanner.nextLine();
                    createEvent(director, title, description);
                    break;
                case 3:
                    System.out.print("Ingrese el titulo del evento a buscar: ");
                    String searchTitle = scanner.nextLine();
                    searchEventByTitle(searchTitle);
                    break;
                case 4:
                    System.out.print("Ingrese el titulo del evento a eliminar: ");
                    String deleteTitle = scanner.nextLine();
                    deleteEventByTitle(deleteTitle);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opcion invalida. Intente nuevamente.");
            }
        }
    }

    public List<Event> getEvents() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}