package main.java.com.skankhunt220.service;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

import main.java.com.skankhunt220.entities.User;

public class UserService {

	private User user;
	private MongoClient mc;
	private MongoDatabase database;
	private MongoCollection<Document> collection;

	public UserService(User user) {
		this.user = user;
		mc = new MongoClient("localhost", 27017);
		database = mc.getDatabase("UserDB");
		collection = database.getCollection("users");
	}

	public void Create() {
		collection.insertOne(new Document("_id", new ObjectId()).append("firstName", user.getFirstName())
				.append("middleName", user.getMiddleName()).append("lastName", user.getLastName()));
		mc.close();
	}

	public Document Read() {
		Document myDoc = collection.find(eq("_id", new ObjectId(user.getId()))).first();
		mc.close();
		return myDoc;
	}

	public void Update() {
		collection.updateOne(eq("_id", new ObjectId(user.getId())),
				new Document("$set", new Document("id", user.getId()).append("firstName", user.getFirstName())
						.append("middleName", user.getMiddleName()).append("lastName", user.getLastName())));
		mc.close();
	}

	public void Delete() {
		collection.deleteOne(eq("id", user.getId()));
		mc.close();
	}
}
