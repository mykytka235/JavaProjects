package main.java.com.skankhunt220.api.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import main.java.com.skankhunt220.entities.User;
import main.java.com.skankhunt220.service.UserService;

@WebServlet("/Example") // servlet url
public class RequestHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;

	private void sendResponse(String message, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		response.setContentType("text/html");
		try (PrintWriter printWriter = response.getWriter()) {
			User user = new User();

			user.setId(request.getParameter("id"));
			user.setFirstName(request.getParameter("firstName"));
			user.setMiddleName(request.getParameter("middleName"));
			user.setLastName(request.getParameter("lastName"));

			printWriter.println("<h1>First name:" + user.getFirstName() + "</h1>" + "<h1>Midle name:"
					+ user.getMiddleName() + "</h1>" + "<h1>Last name:" + user.getLastName() + "</h1>");

			userService = new UserService(user);

			printWriter.println("<h2>" + message + "</h2>");
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Read
		sendResponse("I from method doGet()", request, response);
		Document doc = userService.Read();
		System.out.println(doc.toJson());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Create
		sendResponse("I from method doPost()", request, response);
		userService.Create();
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Update
		sendResponse("I from method doPut()", request, response);
		userService.Update();
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Delete
		sendResponse("I from method doDelete()", request, response);
		userService.Delete();
	}
}