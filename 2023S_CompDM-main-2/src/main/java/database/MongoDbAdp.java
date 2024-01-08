package src.main.java.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
/*
import org.bson.Document;
import org.bson.codecs.configuration.CodecConfigurationException;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
*/

public class MongoDbAdp {

    private static final String DATABASE_NAME = "game_database";
    private static String COLLECTION_NAME = "game_collection";
    private static final String MONGO_URI = "mongodb://localhost:27017";

    /*
    private com.mongodb.client.MongoClient mongoClient;
    private MongoCollection<Document> gameCollection;

    public MongoDbAdp() {
        createDatabaseAndCollectionIfNotExist();
        registerGameCodec();
    }

    private void registerGameCodec() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromProviders(pojoCodecProvider);

        MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(CodecRegistries.fromRegistries(
                        MongoClientSettings.getDefaultCodecRegistry(),
                        pojoCodecRegistry))
                .build();

        mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);

        // Register the package containing the GameState class
        CodecProvider domainCodecProvider = PojoCodecProvider.builder()
                .register(GameState.class.getPackage().getName())
                .register(Document.class)  // Register the Document codec
                .build();

        CodecRegistry domainCodecRegistry = CodecRegistries.fromProviders(domainCodecProvider);
        database = database.withCodecRegistry(domainCodecRegistry);

        gameCollection = database.getCollection(COLLECTION_NAME);
    }

    private void createDatabaseAndCollectionIfNotExist() {
        try {
            // Connect to MongoDB
            MongoClientURI uri = new MongoClientURI(MONGO_URI);
            com.mongodb.MongoClient client = new com.mongodb.MongoClient(uri);

            // Create the database if it doesn't exist
            MongoDatabase database = client.getDatabase(DATABASE_NAME);

            // Check if the collection already exists
            if (database.listCollectionNames().into(new ArrayList<>()).contains(COLLECTION_NAME)) {
                int collectionNumber = 1;
                String newCollectionName = COLLECTION_NAME + collectionNumber;

                // Find a new collection name that doesn't exist
                while (database.listCollectionNames().into(new ArrayList<>()).contains(newCollectionName)) {
                    collectionNumber++;
                    newCollectionName = COLLECTION_NAME + collectionNumber;
                }

                COLLECTION_NAME = newCollectionName;
            }

            // Create the collection
            database.createCollection(COLLECTION_NAME);

            // Close the MongoDB connection
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */

}
