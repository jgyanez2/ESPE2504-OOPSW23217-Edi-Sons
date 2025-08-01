package ec.edu.espe.wargame.view;

import ec.edu.espe.wargame.controller.MongoDBConnection;
import ec.edu.espe.wargame.controller.UserController;
import ec.edu.espe.wargame.model.User;
import java.time.LocalDate;

/**
 *
 * @author Kevin Vaca Edison's OOP ESPE
 */
public class WarGameSystem {

    public static void main(String[] args) {
        try {
            UserView view = new UserView();
            view.displayMenu();
        } catch (Exception e) {
            System.err.println("Application error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}