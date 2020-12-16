package com.skankhunt220.api.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.skankhunt220.entity.User;
import com.skankhunt220.service.UserService;

@WebServlet("/users") // servlet url
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final UserService userService = new UserService();
	private static final ObjectMapper mapper = new ObjectMapper();

	private void sendResponse(String message, HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		try (PrintWriter out = response.getWriter()) {
			out.print(message);
		}
	}
	private User getUserInfo(HttpServletRequest request) throws IOException {
		try {
			String bodyInfo = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			return mapper.readValue(bodyInfo, User.class);
		} catch (IOException ex) {
			// ...
		}
		return new User();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Read
		sendResponse(userService.readUser(getUserInfo(request).getId()).toJson(), response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Create
		User user = getUserInfo(request);
		userService.createUser(user);
		sendResponse(new Gson().toJson(user), response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Update
		User user = getUserInfo(request);
		userService.editUser(user);
		sendResponse(new Gson().toJson(user), response);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Delete
		userService.deleteUser(getUserInfo(request).getId());
		sendResponse("Object was deleted", response);
	}
}