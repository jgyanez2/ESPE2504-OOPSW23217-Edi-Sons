/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author berna
 */
package ec.espe.edu.wargame.model;

import java.util.ArrayList;
import java.util.List;

public class AuthSystem {
    private Director director;
    private final List<SuperiorOfficer> superiorList;

    public AuthSystem() {
        superiorList = new ArrayList<>();
    }

    public void registerDirector(Director director) {
        this.director = director;
    }

    public void registerSuperior(SuperiorOfficer superior) {
        superiorList.add(superior);
    }

    public boolean logIn(String username, String password) {
        return getUser(username, password) != null;
    }

    public Object getUser(String username, String password) {
        if (director != null &&
            director.getUsername().equals(username) &&
            director.getPassword().equals(password)) {
            return director;
        }

        for (SuperiorOfficer superior : superiorList) {
            if (superior.getUsername().equals(username) &&
                superior.getPassword().equals(password)) {
                return superior;
            }
        }

        return null;
    }

    public Director getDirector() {
        return director;
    }

    public List<SuperiorOfficer> getSuperiorList() {
        return superiorList;
    }
}
