package ec.edu.espe.wargame.model;

import java.util.Scanner;

/**
 *
 * @author Jose Yanez
 */
public abstract class User {
    protected String username;
    protected String password;
    protected String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public abstract void mostrarMenu(Scanner scanner, EventManager eventManager, GameTimer gameTimer);
}