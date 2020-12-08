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
	private static final UserService userService = new UserService();;
	private User user;

	private void sendResponse(String message, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html");
		try (PrintWriter writer = response.getWriter()) {
			writer.println(message);
		}
	}

	private void getUserFields(HttpServletRequest request) {
		user = new User();
		user.setId(request.getParameter("id"));
		user.setFirstName(request.getParameter("firstName"));
		user.setMiddleName(request.getParameter("middleName"));
		user.setLastName(request.getParameter("lastName"));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Read
		getUserFields(request);
		sendResponse(userService.readUser(user.getId()).toJson(), request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Create
		getUserFields(request);
		userService.createUser(user);
		sendResponse("I from method doPost()", request, response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Update
		getUserFields(request);
		userService.editUser(user);
		sendResponse("I from method doPut()", request, response);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Delete
		getUserFields(request);
		userService.deleteUser(user.getId());
		sendResponse("I from method doDelete()", request, response);
	}
}