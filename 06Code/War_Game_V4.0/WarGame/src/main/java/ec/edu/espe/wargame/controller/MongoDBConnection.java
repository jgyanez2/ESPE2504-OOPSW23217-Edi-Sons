package ec.edu.espe.wargame.controller;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDBConnection {
    private static final String CONNECTION_STRING = "mongodb+srv://KevinV593:KevinV593@cluster0.7ufczsp.mongodb.net/?retryWrites=true&w=majority";
    private static final String DATABASE_NAME = "WarGame";
    
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    
    public static MongoDatabase getDatabase() {
        if (database == null) {
            try {
                // Configure codec registry to handle POJOs
                CodecRegistry pojoCodecRegistry = fromRegistries(
                    MongoClientSettings.getDefaultCodecRegistry(),
                    fromProviders(PojoCodecProvider.builder().automatic(true).build())
                );
                
                // Configure connection settings
                MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString(CONNECTION_STRING))
                    .codecRegistry(pojoCodecRegistry)
                    .build();
                
                // Create client and get database
                mongoClient = MongoClients.create(settings);
                database = mongoClient.getDatabase(DATABASE_NAME)
                    .withCodecRegistry(pojoCodecRegistry);
                
                System.out.println("Connected to MongoDB successfully (v4.9.1 driver)");
                
            } catch (Exception e) {
                System.err.println("Connection failed: " + e.getMessage());
                if (mongoClient != null) {
                    mongoClient.close();
                }
                throw new RuntimeException("Failed to connect to MongoDB", e);
            }
        }
        return database;
    }
    
    public static void closeConnection() {
        if (mongoClient != null) {
            try {
                mongoClient.close();
                System.out.println("MongoDB connection closed");
            } catch (Exception e) {
                System.err.println("Error closing connection: " + e.getMessage());
            } finally {
                mongoClient = null;
                database = null;
            }
        }
    }
}