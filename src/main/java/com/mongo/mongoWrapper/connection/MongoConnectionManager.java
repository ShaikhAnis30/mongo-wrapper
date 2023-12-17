package com.mongo.mongoWrapper.connection;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class MongoConnectionManager extends AbstractMongoClientConfiguration {


    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Value("${spring.data.mongodb.database}")
    private String databaseName;


    @Override
    protected String getDatabaseName() {
        return databaseName; // Replace with your actual database name
    }

    @Override
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(mongoUri);
    }


//    @Value("${spring.data.mongodb.uri}")
//    private final String dbUrl;
//
//    private final String connectionString;
//
//    public MongoConnectionManager(String url) {
//        this.connectionString = String.format("mongodb://%s:%d", url);
//    }
//
//    public MongoClient connect() {
//        return MongoClients.create(connectionString);
//    }
//
//    public void close(MongoClient mongoClient) {
//        if (mongoClient != null) {
//            mongoClient.close();
//        }
//    }


    public static void main(String[] args) {
        // Example usage
//        String mongodbUrl = "localhost"; // Replace with your MongoDB server URL
//        int mongodbPort = 27017; // Replace with your MongoDB server port
//
//        MongoConnectionManager connectionManager = new MongoConnectionManager(mongodbUrl, mongodbPort);
//
//        // Connecting to MongoDB
//        MongoClient mongoClient = connectionManager.connect();
//
//        // Perform MongoDB operations using the MongoClient...
//
//        // Closing the connection when done
//        connectionManager.close(mongoClient);

//        MongoConnectionManager client =
    }
}
