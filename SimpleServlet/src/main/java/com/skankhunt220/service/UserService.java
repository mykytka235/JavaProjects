package main.java.com.skankhunt220.service;

import org.bson.Document;

import main.java.com.skankhunt220.entities.User;
import main.java.com.skankhunt220.repository.UserRepository;

public class UserService {

	private UserRepository userRepository;
	
	public UserService() {
		userRepository = new UserRepository();
	}

	public void createUser(User user) {
		userRepository.Create(user);
	}

	public Document readUser(String userId) {
		Document myDoc = userRepository.Read(userId);
		return myDoc;
	}

	public void editUser(User user) {
		userRepository.Update(user);
	}

	public void deleteUser(String userId) {
		userRepository.Delete(userId);
	}
}
