package ec.edu.espe.wargame.model;

import java.time.LocalDateTime;

public class Event {
    private final String title;
    private final String description;
    private final Director creator;

    /**
     *
     * @param title
     * @param description
     * @param eventDate
     * @param creator
     */
    public Event(String title, String description, Director creator) {
        this.title = title;
        this.description = description;
        this.creator = creator;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Director getCreator() {
        return creator;
    }
}