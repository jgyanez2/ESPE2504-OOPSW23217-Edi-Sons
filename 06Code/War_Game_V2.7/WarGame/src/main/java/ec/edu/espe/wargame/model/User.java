package ec.edu.espe.wargame.model;

import java.time.LocalDate;

/**
 *
 * @author Kevin Vaca Edison's OOP ESPE
 */
public class User {

    private String id;
    private String type;
    private String user;
    private String password;
    private String country;
    private String sector;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
  
    public User(String id, String type, String user, String password, String country, String sector) {
        this.id = id;
        this.type = type;
        this.user = user;
        this.password = password;
        this.country = country;
        this.sector = sector;
    }
}
