package main.java.com.skankhunt220.repository;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import main.java.com.skankhunt220.entities.User;

public class UserRepository {
	private MongoClient mc;
	private MongoDatabase database;
	private MongoCollection<Document> collection;

	public UserRepository() {
		mc = new MongoClient("localhost", 27017);
		database = mc.getDatabase("UserDB");
		collection = database.getCollection("users");
	}

	public void Create(User user) {
		collection.insertOne(new Document("_id", new ObjectId()).append("firstName", user.getFirstName())
				.append("middleName", user.getMiddleName()).append("lastName", user.getLastName()));
		closeConnection();
	}

	public Document Read(String userId) {
		Document myDoc = collection.find(eq("_id", new ObjectId(userId))).first();
		closeConnection();
		return myDoc;
	}

	public void Update(User user) {
		collection.updateOne(eq("_id", new ObjectId(user.getId())),
				new Document("$set", new Document("firstName", user.getFirstName())
						.append("middleName", user.getMiddleName()).append("lastName", user.getLastName())));
	}

	public void Delete(String userId) {
		collection.deleteOne(eq("_id", new ObjectId(userId)));
		closeConnection();
	}

	public void closeConnection() {
		try {
			mc.close();
		} catch (MongoClientException ex) {
			System.out.println(ex.toString());
		}
	}
}
