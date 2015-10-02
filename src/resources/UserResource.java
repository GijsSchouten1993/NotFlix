package resources;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.org.apache.xml.internal.utils.URI;
import com.sun.org.glassfish.gmbal.Description;

import model.Model;
import model.User;

@Path("/users")
public class UserResource {
	
	@Context ServletContext context;
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public User getUser(@PathParam("id") int id) {
		//Haal het model op en return de gebruiker met het juiste ID
		Model mod = (Model)context.getAttribute("model");
		return mod.GetUserById(id);
	}
	
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public ArrayList<User> getUsers() {
		//Haal het model op en return alle gebruikers
		Model mod = (Model)context.getAttribute("model");
		return mod.GetUsers();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public int AddNewUser(User user) {
		//Haal het model op en maak een nieuwe gebruiker aan en geef het ID terug
		Model mod = (Model)context.getAttribute("model");
		int userId = mod.AddNewUser(user);
		return userId;	
	}
	
	@POST
	@Path("{id}/ratings")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public int AddNewRatingForUser(Rating user) {
		//Haal het model op en voeg een nieuwe rating toe
		Model mod = (Model)context.getAttribute("model");
		int userId = mod.AddNewUser(user);
		return userId;	
	}

}
