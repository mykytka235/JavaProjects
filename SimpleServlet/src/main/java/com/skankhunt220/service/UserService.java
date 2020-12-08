package main.java.com.skankhunt220.service;

import org.bson.Document;

import main.java.com.skankhunt220.entities.User;
import main.java.com.skankhunt220.repository.UserRepository;

public class UserService {

	private static final UserRepository userRepository = new UserRepository();

	public void createUser(User user) {
		userRepository.create(user);
	}

	public Document readUser(String userId) {		
		return userRepository.read(userId);
	}

	public void editUser(User user) {
		userRepository.update(user);
	}

	public void deleteUser(String userId) {
		userRepository.delete(userId);
	}
}
