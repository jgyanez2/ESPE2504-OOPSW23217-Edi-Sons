/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Jose Yanez
 */
package ec.espe.edu.wargame.model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

public class AuthSystem {

    private final List<Director> directorList;
    private final List<SuperiorOfficer> superiorList;
    private final List<Subordinate> subordinateList;

    public AuthSystem() {
        directorList = new ArrayList<>();
        superiorList = new ArrayList<>();
        subordinateList = new ArrayList<>();
    }

    public void loadUsersFromCSV(String archivoCSV, String roleOption) {
        if (roleOption.equals("Director") && !directorList.isEmpty()) return;
        if (roleOption.equals("SuperiorOfficer") && !superiorList.isEmpty()) return;
        if (roleOption.equals("Subordinate") && !subordinateList.isEmpty()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length < 2) {
                    System.out.println("Linea mal formateada en el CSV, ignorando: " + linea);
                    continue;
                }

                String username = partes[0].trim();
                String password = partes[1].trim();

                switch (roleOption) {
                    case "Director":
                        directorList.add(new Director(username, password));
                        break;
                    case "SuperiorOfficer":
                        superiorList.add(new SuperiorOfficer(username, password));
                        break;
                    case "Subordinate":
                        subordinateList.add(new Subordinate(username, password));
                        break;
                    default:
                        System.out.println("Rol desconocido: " + roleOption);
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo archivo CSV: " + e.getMessage());
        }
    }

    public Object getUser(String username, String password) {
        for (Director d : directorList) {
            if (d.getUsername().equals(username) && d.getPassword().equals(password)) {
                return d;
            }
        }
        for (SuperiorOfficer s : superiorList) {
            if (s.getUsername().equals(username) && s.getPassword().equals(password)) {
                return s;
            }
        }
        for (Subordinate sub : subordinateList) {
            if (sub.getUsername().equals(username) && sub.getPassword().equals(password)) {
                return sub;
            }
        }
        return null;
    }
}