package main.java.com.skankhunt220.repository;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import main.java.com.skankhunt220.entities.User;

public class UserRepository {
	private MongoDatabase database;
	private MongoCollection<Document> collection;

	public void create(User user) {
		try (MongoClient mc = new MongoClient("localhost", 27017)) {
			database = mc.getDatabase("UserDB");
			collection = database.getCollection("users");

			collection.insertOne(new Document("_id", new ObjectId()).append("firstName", user.getFirstName())
					.append("middleName", user.getMiddleName()).append("lastName", user.getLastName()));
		}
	}

	public Document read(String userId) {
		try (MongoClient mc = new MongoClient("localhost", 27017)) {
			database = mc.getDatabase("UserDB");
			collection = database.getCollection("users");
			Document myDoc = collection.find(eq("_id", new ObjectId(userId))).first();
			return myDoc;
		}
	}

	public void update(User user) {
		try (MongoClient mc = new MongoClient("localhost", 27017)) {
			database = mc.getDatabase("UserDB");
			collection = database.getCollection("users");
			collection.updateOne(eq("_id", new ObjectId(user.getId())),
					new Document("$set", new Document("firstName", user.getFirstName())
							.append("middleName", user.getMiddleName()).append("lastName", user.getLastName())));
		}
	}

	public void delete(String userId) {
		try (MongoClient mc = new MongoClient("localhost", 27017)) {
			database = mc.getDatabase("UserDB");
			collection = database.getCollection("users");
			collection.deleteOne(eq("_id", new ObjectId(userId)));
		}
	}
}
