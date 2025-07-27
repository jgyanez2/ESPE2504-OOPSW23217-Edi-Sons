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

public class AuthSystem {
    private final String officerFile = "oficiales.csv";

    public boolean registerOfficer(SuperiorOfficer officer) {
        if (checkOfficerExists(officer.getName())) {
            return false;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(officerFile, true))) {
            writer.write(officer.toCsvLine());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
            return false;
        }

        return true;
    }

    public boolean logInOfficer(String name, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(officerFile))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 2) {
                    String storedName = parts[0];
                    String storedPassword = parts[1];

                    if (storedName.equals(name) && storedPassword.equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return false;
    }

    public boolean checkOfficerExists(String name) {
        try (BufferedReader reader = new BufferedReader(new FileReader(officerFile))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1 && parts[0].equals(name)) {
                    return true;
                }
            }
        } catch (IOException e) {
            // archivo puede no existir aún
        }

        return false;
    }
}
