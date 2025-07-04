/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.espe.edu.wargame.model;

/**
 *
 * @author berna
 */
import java.io.*;
import java.util.*;

public class AuthSystem {
    private List<Subordinate> subordinateList = new ArrayList<>();
    private List<SuperiorOfficer> superiorOfficerList = new ArrayList<>();
    private List<Director> directorList = new ArrayList<>();

    public AuthSystem() {
        loadUsersFromCSV();
    }

    
    public void loadUsersFromCSV() {
        try {
            
            BufferedReader reader = new BufferedReader(new FileReader("subordinates.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    Subordinate subordinate = new Subordinate(parts[0], parts[1]);
                    subordinateList.add(subordinate);
                }
            }
            reader.close();

            
            reader = new BufferedReader(new FileReader("superiors.csv"));
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    SuperiorOfficer superior = new SuperiorOfficer(parts[0], parts[1]);
                    superiorOfficerList.add(superior);
                }
            }
            reader.close();

            
            reader = new BufferedReader(new FileReader("directors.csv"));
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    Director director = new Director(parts[0], parts[1]);
                    directorList.add(director);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error al cargar los usuarios desde los archivos CSV.");
        }
    }

    
    public void saveUserToCSV(Object user) {
        try {
            BufferedWriter writer = null;
            if (user instanceof Subordinate) {
                writer = new BufferedWriter(new FileWriter("subordinates.csv", true));
                Subordinate subordinate = (Subordinate) user;
                writer.write(subordinate.getUsername() + "," + subordinate.getPassword());
            } else if (user instanceof SuperiorOfficer) {
                writer = new BufferedWriter(new FileWriter("superiors.csv", true));
                SuperiorOfficer superior = (SuperiorOfficer) user;
                writer.write(superior.getUsername() + "," + superior.getPassword());
            } else if (user instanceof Director) {
                writer = new BufferedWriter(new FileWriter("directors.csv", true));
                Director director = (Director) user;
                writer.write(director.getUsername() + "," + director.getPassword());
            }
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al guardar el usuario en el archivo CSV.");
        }
    }

    
    public void registerDirector(Director director) {
        directorList.add(director);
        saveUserToCSV(director); 
    }

   
    public void registerSuperior(SuperiorOfficer superior) {
        superiorOfficerList.add(superior);
        saveUserToCSV(superior); 
    }

    
    public void registerSubordinate(Subordinate subordinate) {
        subordinateList.add(subordinate);
        saveUserToCSV(subordinate); 
    }

    
    public boolean logIn(String username, String password) {
        for (Subordinate subordinate : subordinateList) {
            if (subordinate.getUsername().equals(username) && subordinate.getPassword().equals(password)) {
                return true;
            }
        }

        for (SuperiorOfficer superior : superiorOfficerList) {
            if (superior.getUsername().equals(username) && superior.getPassword().equals(password)) {
                return true;
            }
        }

        for (Director director : directorList) {
            if (director.getUsername().equals(username) && director.getPassword().equals(password)) {
                return true;
            }
        }

        return false; 
    }
}

