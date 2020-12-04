package classes;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/Example")//servlet url
public class RequestHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;	

	private void sendToMongoDB(String message, String operation, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html");		
		try(PrintWriter printWriter = response.getWriter()){		
			User user = new User();

			user.id = Integer.parseInt(request.getParameter("id"));
			user.firstName = request.getParameter("firstName");
			user.middleName = request.getParameter("middleName");
			user.lastName = request.getParameter("lastName");
			
			printWriter.println("<h1>First name:" + user.firstName + "</h1>" + 
					"<h1>Midle name:" + user.middleName + "</h1>" + 
					"<h1>Last name:" + user.lastName + "</h1>");				
			
			try(MongoClient mc = new MongoClient("localhost", 27017)){
			
				MongoDatabase database = mc.getDatabase("UserDB");
				MongoCollection<Document> collection = database.getCollection("users");
				
				if(operation == "Create") {
					collection.insertOne(new Document("id", user.id)
											.append("firstName", user.firstName)
											.append("middleName", user.middleName)
											.append("lastName", user.lastName));	
				}				

				if(operation == "Read") {
					Document myDoc = collection.find(eq("id", user.id)).first();												
					printWriter.println(myDoc.toJson());						
				}

				if(operation == "Update") {					
					collection.updateOne(eq("id", user.id), new Document("$set", new Document("id", user.id)
																.append("firstName", user.firstName)
																.append("middleName", user.middleName)
																.append("lastName", user.lastName)));
				}
				
				if(operation == "Delete") {
					collection.deleteOne(eq("id", user.id));
				}		
				
			}
			printWriter.println("<h2>" + message + "</h2>");
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Read
		sendToMongoDB("I from method doGet()", "Read",request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Create
		sendToMongoDB("I from method doPost()", "Create", request, response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {			
		// Update
		sendToMongoDB("I from method doPut()", "Update", request, response);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		// Delete
		sendToMongoDB("I from method doDelete()", "Delete", request, response);		
	}
}