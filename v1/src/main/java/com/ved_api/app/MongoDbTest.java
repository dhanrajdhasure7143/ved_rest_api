package com.ved_api.app;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;

public class MongoDbTest {
	public static void main(String[] args) {
        try {
            // Create a new MongoClientSettings object
        	
        	System.out.println("Started Checking Connection...");
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString("mongodb+srv://dhanrajdhasure:Dhanraj%407143@my-j2ee-app.r89ws.mongodb.net/mydatabase?retryWrites=true&w=majority"))
                    .build();

            MongoClient mongoClient = MongoClients.create(settings);
            MongoDatabase database = mongoClient.getDatabase("mySite");
            System.out.println("Connected to database: " + database.getName());
//            System.out.println("Connected to database: " + database.toString());
            mongoClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
