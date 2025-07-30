package ec.edu.espe.wargame.controller;


import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import ec.edu.espe.wargame.model.User;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

public class UserController {
    private final MongoDatabase db;
    private final String collectionName = "Users";

    public UserController() {
        this.db = MongoDBConnection.getDatabase();
        if (this.db == null) {
            throw new IllegalStateException("Database connection not established");
        }
    }

    private MongoCollection<Document> getCollection() {
        return db.getCollection(collectionName);
    }

    // CREATE - Insert a new user
    public void createUser(User user) {
        try {
            Document doc = new Document()
                .append("id", user.getId())
                .append("type", user.getType())
                .append("user", user.getUser())
                .append("password", user.getPassword())
                .append("country", user.getCountry())
                .append("sector", user.getSector());
            
            getCollection().insertOne(doc);
            System.out.println("User created successfully: " + user.getUser());
        } catch (Exception e) {
            System.err.println("Error creating user: " + e.getMessage());
            throw e;
        }
    }

    // READ - Get all users
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            for (Document doc : getCollection().find()) {
                User user = documentToUser(doc);
                users.add(user);
            }
        } catch (Exception e) {
            System.err.println("Error fetching users: " + e.getMessage());
            throw e;
        }
        return users;
    }

    // READ - Get user by username
    public User getUserByUsername(String username) {
        try {
            Document doc = getCollection().find(Filters.eq("user", username)).first();
            return (doc != null) ? documentToUser(doc) : null;
        } catch (Exception e) {
            System.err.println("Error finding user: " + e.getMessage());
            throw e;
        }
    }

    // READ - Get users by sector
    public List<User> getUsersBySector(String sector) {
        List<User> users = new ArrayList<>();
        try {
            Bson filter = Filters.eq("sector", sector);
            for (Document doc : getCollection().find(filter)) {
                users.add(documentToUser(doc));
            }
        } catch (Exception e) {
            System.err.println("Error fetching users by sector: " + e.getMessage());
            throw e;
        }
        return users;
    }

    // READ - Get users by type
    public List<User> getUsersByType(String type) {
        List<User> users = new ArrayList<>();
        try {
            Bson filter = Filters.eq("type", type);
            for (Document doc : getCollection().find(filter)) {
                users.add(documentToUser(doc));
            }
        } catch (Exception e) {
            System.err.println("Error fetching users by type: " + e.getMessage());
            throw e;
        }
        return users;
    }

    // UPDATE - Update user's sector
    public long updateUserSector(String username, String newSector) {
        try {
            Bson filter = Filters.eq("user", username);
            Bson update = Updates.set("sector", newSector);
            UpdateResult result = getCollection().updateOne(filter, update);
            System.out.println("Updated sector for user: " + username);
            return result.getModifiedCount();
        } catch (Exception e) {
            System.err.println("Error updating sector: " + e.getMessage());
            throw e;
        }
    }

    // UPDATE - Update user's ID
    public long updateUserId(String currentId, String newId) {
        try {
            Bson filter = Filters.eq("id", currentId);
            Bson update = Updates.set("id", newId);
            UpdateResult result = getCollection().updateMany(filter, update);
            System.out.println("Updated ID from " + currentId + " to " + newId);
            return result.getModifiedCount();
        } catch (Exception e) {
            System.err.println("Error updating ID: " + e.getMessage());
            throw e;
        }
    }

    // UPDATE - Update multiple fields
    public long updateUser(String username, User updatedUser) {
        try {
            Bson filter = Filters.eq("user", username);
            
            Bson updates = Updates.combine(
                Updates.set("id", updatedUser.getId()),
                Updates.set("type", updatedUser.getType()),
                Updates.set("password", updatedUser.getPassword()),
                Updates.set("country", updatedUser.getCountry()),
                Updates.set("sector", updatedUser.getSector())
            );
            
            UpdateResult result = getCollection().updateOne(filter, updates);
            System.out.println("Updated user: " + username);
            return result.getModifiedCount();
        } catch (Exception e) {
            System.err.println("Error updating user: " + e.getMessage());
            throw e;
        }
    }

    // DELETE - Remove user by username
    public long deleteUser(String username) {
        try {
            Bson filter = Filters.eq("user", username);
            DeleteResult result = getCollection().deleteOne(filter);
            System.out.println("Deleted user: " + username);
            return result.getDeletedCount();
        } catch (Exception e) {
            System.err.println("Error deleting user: " + e.getMessage());
            throw e;
        }
    }

    // DELETE - Remove all users in a sector
    public long deleteUsersBySector(String sector) {
        try {
            Bson filter = Filters.eq("sector", sector);
            DeleteResult result = getCollection().deleteMany(filter);
            System.out.println("Deleted users from sector: " + sector);
            return result.getDeletedCount();
        } catch (Exception e) {
            System.err.println("Error deleting users by sector: " + e.getMessage());
            throw e;
        }
    }

    // Helper method to convert Document to User
    private User documentToUser(Document doc) {
        return new User(
            doc.getString("id"),
            doc.getString("type"),
            doc.getString("user"),
            doc.getString("password"),
            doc.getString("country"),
            doc.getString("sector")
        );
    }
}