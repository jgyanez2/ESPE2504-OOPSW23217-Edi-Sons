/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.espe.edu.wargame.model;

/**
 *
 * @author berna
 */
import java.util.HashMap;
import java.util.Map;

public class AuthSystem {
    private Map<String, User> users;

    public AuthSystem() {
        users = new HashMap<>();
    }

    public void registerDirector(Director director) {
        users.put(director.getUsername(), director);
    }

    public void registerSuperior(SuperiorOfficer superior) {
        users.put(superior.getUsername(), superior);
    }

    public void registerSubordinate(Subordinate subordinate) {
        users.put(subordinate.getUsername(), subordinate);
    }

    public boolean logIn(String username, String password) {
        User user = users.get(username);
        return user != null && user.getPassword().equals(password);
    }
}
