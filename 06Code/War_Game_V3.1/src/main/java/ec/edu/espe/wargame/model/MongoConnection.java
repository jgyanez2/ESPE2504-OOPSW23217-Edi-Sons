package ec.edu.espe.wargame.model;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 * Clase que establece la conexión con la base de datos MongoDB Atlas.
 */
public class MongoConnection {
    private static final String URI = "mongodb+srv://<usuario>:<contraseña>@<cluster>.mongodb.net/?retryWrites=true&w=majority";
    private static final String DATABASE_NAME = "wargameDB"; // Cambia por el nombre real de tu base

    /**
     * Establece y devuelve una conexión a la base de datos MongoDB.
     * @return Objeto MongoDatabase conectado
     */
    public static MongoDatabase connect() {
        MongoClient client = MongoClients.create(URI);
        return client.getDatabase(DATABASE_NAME);
    }
}
