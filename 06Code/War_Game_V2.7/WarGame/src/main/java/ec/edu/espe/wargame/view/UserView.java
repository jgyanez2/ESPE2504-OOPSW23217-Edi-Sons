package ec.edu.espe.wargame.view;

import ec.edu.espe.wargame.controller.UserController;
import ec.edu.espe.wargame.model.User;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private final UserController controller;
    private final Scanner scanner;

    public UserView() {
        this.controller = new UserController();
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n===== War Game User Management =====");
            System.out.println("1. Create User");
            System.out.println("2. List All Users");
            System.out.println("3. Find User by Username");
            System.out.println("4. List Users by Sector");
            System.out.println("5. Update User");
            System.out.println("6. Delete User");
            System.out.println("7. Exit");
            System.out.print("Select option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1 -> createUser();
                case 2 -> displayAllUsers();
                case 3 -> findUserByUsername();
                case 4 -> displayUsersBySector();
                case 5 -> updateUser();
                case 6 -> deleteUser();
                case 7 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    private void createUser() {
        System.out.println("\n--- Create New User ---");
        System.out.print("ID: ");
        String id = scanner.nextLine();
        
        System.out.print("Type: ");
        String type = scanner.nextLine();
        
        System.out.print("Username: ");
        String username = scanner.nextLine();
        
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        System.out.print("Country: ");
        String country = scanner.nextLine();
        
        System.out.print("Sector: ");
        String sector = scanner.nextLine();
        
        User newUser = new User(id, type, username, password, country, sector);
        controller.createUser(newUser);
        System.out.println("User created successfully!");
    }

    private void displayAllUsers() {
        System.out.println("\n--- All Users ---");
        List<User> users = controller.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users found");
            return;
        }
        
        users.forEach(user -> {
            System.out.printf("ID: %s | Username: %s | Type: %s | Sector: %s%n",
                user.getId(), user.getUser(), user.getType(), user.getSector());
        });
    }

    private void findUserByUsername() {
        System.out.print("\nEnter username to search: ");
        String username = scanner.nextLine();
        
        User user = controller.getUserByUsername(username);
        if (user != null) {
            System.out.println("\nUser found:");
            System.out.printf("ID: %s%nUsername: %s%nType: %s%nSector: %s%nCountry: %s%n",
                user.getId(), user.getUser(), user.getType(), user.getSector(), user.getCountry());
        } else {
            System.out.println("User not found");
        }
    }

    private void displayUsersBySector() {
        System.out.print("\nEnter sector to filter: ");
        String sector = scanner.nextLine();
        
        List<User> users = controller.getUsersBySector(sector);
        if (users.isEmpty()) {
            System.out.println("No users found in sector: " + sector);
            return;
        }
        
        System.out.println("\nUsers in sector " + sector + ":");
        users.forEach(user -> {
            System.out.printf("ID: %s | Username: %s | Type: %s%n",
                user.getId(), user.getUser(), user.getType());
        });
    }

    private void updateUser() {
        System.out.print("\nEnter username to update: ");
        String username = scanner.nextLine();
        
        User existing = controller.getUserByUsername(username);
        if (existing == null) {
            System.out.println("User not found");
            return;
        }
        
        System.out.println("\nCurrent user data:");
        System.out.printf("ID: %s%nType: %s%nUsername: %s%nPassword: %s%nCountry: %s%nSector: %s%n",
            existing.getId(), existing.getType(), existing.getUser(),
            existing.getPassword(), existing.getCountry(), existing.getSector());
        
        System.out.println("\nEnter new data (leave blank to keep current):");
        
        System.out.print("New ID [" + existing.getId() + "]: ");
        String id = scanner.nextLine();
        
        System.out.print("New Type [" + existing.getType() + "]: ");
        String type = scanner.nextLine();
        
        System.out.print("New Password [" + existing.getPassword() + "]: ");
        String password = scanner.nextLine();
        
        System.out.print("New Country [" + existing.getCountry() + "]: ");
        String country = scanner.nextLine();
        
        System.out.print("New Sector [" + existing.getSector() + "]: ");
        String sector = scanner.nextLine();
        
        // Use existing values if no new input provided
        User updatedUser = new User(
            id.isEmpty() ? existing.getId() : id,
            type.isEmpty() ? existing.getType() : type,
            username, // username can't be changed (used as identifier)
            password.isEmpty() ? existing.getPassword() : password,
            country.isEmpty() ? existing.getCountry() : country,
            sector.isEmpty() ? existing.getSector() : sector
        );
        
        long updated = controller.updateUser(username, updatedUser);
        System.out.println(updated + " user(s) updated");
    }

    private void deleteUser() {
        System.out.print("\nEnter username to delete: ");
        String username = scanner.nextLine();
        
        long deleted = controller.deleteUser(username);
        if (deleted > 0) {
            System.out.println("User deleted successfully");
        } else {
            System.out.println("User not found or could not be deleted");
        }
    }
}