/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author berna
 */
package ec.espe.edu.wargame.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthSystem {

    private List<Director> directorList;
    private final List<SuperiorOfficer> superiorList;
    private final List<Subordinate> subordinateList;

    public AuthSystem() {
        directorList = new ArrayList<>();
        superiorList = new ArrayList<>();
        subordinateList = new ArrayList<>();
    }

    // Registrar un Director
    public void registerDirector(Director director) {
        directorList.add(director);
    }

    // Registrar un Oficial Superior
    public void registerSuperior(SuperiorOfficer superior) {
        superiorList.add(superior);
    }

    // Registrar un Subordinado
    public void registerSubordinate(Subordinate subordinate) {
        subordinateList.add(subordinate);
    }

    // Cargar usuarios desde un archivo CSV según el rol
    public void loadUsersFromCSV(String archivoCSV, String roleOption) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length < 2) {
                    System.out.println("Línea mal formateada en el archivo CSV, ignorando: " + linea);
                    continue; // Saltar líneas mal formateadas
                }

                String username = partes[0].trim();
                String password = partes[1].trim();

                // Validar que el rol no sea nulo o vacío
                if (roleOption == null || roleOption.isEmpty()) {
                    System.out.println("El rol no puede ser nulo o vacío.");
                    continue;
                }

                switch (roleOption) {
                    case "Director":
                        registerDirector(new Director(username, password));
                        break;
                    case "SuperiorOfficer":
                        registerSuperior(new SuperiorOfficer(username, password));
                        break;
                    case "Subordinate":
                        registerSubordinate(new Subordinate(username, password));
                        break;
                    default:
                        System.out.println("Rol no reconocido: " + roleOption);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    // Obtener usuario según el nombre de usuario y contraseña
    public Object getUser(String username, String password) {
        for (Director director : directorList) {
        if (director.getUsername().equals(username) && director.getPassword().equals(password)) {
            return director;
        }
        }

        for (SuperiorOfficer superior : superiorList) {
            if (superior.getUsername().equals(username) && superior.getPassword().equals(password)) {
                return superior;
            }
        }

        for (Subordinate subordinate : subordinateList) {
            if (subordinate.getUsername().equals(username) && subordinate.getPassword().equals(password)) {
                return subordinate;
            }
        }

        return null; // Si no se encuentra el usuario
    }

    // Obtener el Director
    public List<Director> getDirector() {
        return directorList;
    }

    // Obtener la lista de Oficiales Superiores
    public List<SuperiorOfficer> getSuperiorList() {
        return superiorList;
    }

    // Obtener la lista de Subordinados
    public List<Subordinate> getSubordinateList() {
        return subordinateList;
    }
}
