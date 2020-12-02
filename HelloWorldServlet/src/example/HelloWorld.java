package example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloWorld
 */
@WebServlet("/SimplExample")//servlet url
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void sendResponse(String message, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		try(PrintWriter printWriter = response.getWriter()){
			printWriter.println(message);		
		}			
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sendResponse("I'm from doGet()!", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sendResponse("I'm from doPost()!", request, response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		sendResponse("I'm from doPut()!", request, response);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		sendResponse("I'm from doDelete()!", request, response);
	}
}
