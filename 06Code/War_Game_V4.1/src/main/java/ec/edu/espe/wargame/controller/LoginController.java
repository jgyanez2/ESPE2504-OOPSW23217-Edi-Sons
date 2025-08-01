package ec.edu.espe.wargame.controller;

import ec.edu.espe.wargame.model.User;

/**
 *
 * @author Kevin Vaca Edison's OOP ESPE
 */
public class LoginController {
    
    private final UserController userController = new UserController();

    public boolean authenticateCredentials(String username, String password) {
        User user = userController.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return true; // Successful login
        }
        return false; // Invalid credentials
    }
}
