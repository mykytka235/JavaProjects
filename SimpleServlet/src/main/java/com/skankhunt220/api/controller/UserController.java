package main.java.com.skankhunt220.api.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.skankhunt220.entities.User;
import main.java.com.skankhunt220.service.UserService;

@WebServlet("/User") // servlet url
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private User user;

	private void sendResponse(String message, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		response.setContentType("text/html");
		try (PrintWriter writer = response.getWriter()) {
			user = new User();
			getUserFields(user, request);
			userService = new UserService();			
			writer.println(message);
		}
	}

	private void getUserFields(User user, HttpServletRequest request) {
		user.setId(request.getParameter("id"));
		user.setFirstName(request.getParameter("firstName"));
		user.setMiddleName(request.getParameter("middleName"));
		user.setLastName(request.getParameter("lastName"));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Read
		response.setContentType("text/html");
		try(PrintWriter writer = response.getWriter()){
			user = new User();
			user.setId(request.getParameter("id"));
			userService = new UserService();
			writer.println(userService.readUser(user.getId()).toJson());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Create
		sendResponse("I from method doPost()", request, response);
		userService.createUser(user);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Update
		sendResponse("I from method doPut()", request, response);
		userService.editUser(user);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Delete
		sendResponse("I from method doDelete()", request, response);
		userService.deleteUser(user.getId());
	}
}