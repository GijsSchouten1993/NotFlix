package resources;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.org.apache.xml.internal.utils.URI;
import com.sun.org.glassfish.gmbal.Description;

import model.Model;
import model.Rating;
import model.ResponseWithException;
import model.Token;
import model.User;

@Path("/users")
public class UserResource {

	@Context
	ServletContext context;

	/**
	 * Haalt een user op op basis van id.
	 * @param id het id waarop gezocht moet worden
	 * @param _token voor beveiliging
	 * @return een user met een bepaald id
	 */
	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public User getUser(@PathParam("id") int id, @HeaderParam("token") String _token) {

		// Haal het model op en return de gebruiker met het juiste ID
		Model mod = (Model) context.getAttribute("model");
		mod.CheckAuthorizationWithUserId(id, _token, mod);	
		return mod.GetUserById(id);
	}

	/**
	 * Haalt alle users op.
	 * @param _token voor identificatie
	 * @return alle users
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ArrayList<User> getUsers( @HeaderParam("token") String _token) {
		// Haal het model op en return alle gebruikers
		Model mod = (Model) context.getAttribute("model");
		mod.CheckAuthorization(_token,mod);
		return mod.GetUsers();
	}

	/**
	 * Maakt een nieuwe gebruiker aan.
	 * @param user de user die moet worden toegevoegd
	 * @return een userId
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public int AddNewUser(User user) {
		try {
		// Haal het model op en maak een nieuwe gebruiker aan en geef het ID
		// terug
		Model mod = (Model) context.getAttribute("model");
		int userId = mod.AddNewUser(user);
		return userId;
		} catch (Exception ex) {
			throw new ResponseWithException(ex.getMessage(),Response.Status.BAD_REQUEST);
		}
	}

	/**
	 * Haalt de ratings van een bepaalde user op.
	 * @param id het Id van de user
	 * @param _token voor identificatie
	 * @return een lijst met ratings van een bepaalde user
	 */
	@GET
	@Path("{id}/ratings")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ArrayList<Rating> GetRatingsFromUser(@PathParam("id") int id, @HeaderParam("token") String _token) {
		try {
		// Haal het model op en verkrijg alle ratings van een gebruiker
		Model mod = (Model) context.getAttribute("model");
		mod.CheckAuthorizationWithUserId(id, _token, mod);
		return (mod.GetAllRatingsForUser(id));
		} catch (Exception ex) {
			throw new ResponseWithException(ex.getMessage(),Response.Status.BAD_REQUEST);
		}
	}
	
	/**
	 * Zorgt ervoor dat een user een rating kan toevoegen.
	 * @param id het id van de user
	 * @param rating de rating die de user toegevoegd wilt hebben
	 * @param _token voor identificatie
	 * @return het ratingId
	 */
	@POST
	@Path("{id}/ratings")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public int AddNewRatingForUser(@PathParam("id") int id, Rating rating, @HeaderParam("token") String _token) {
		if (rating.getUserId() != id) {
			throw new ResponseWithException("UserID moet overeen komen met rating UserID",Response.Status.BAD_REQUEST);
		}
		try {
			// Haal het model op en voeg een nieuwe rating toe
			Model mod = (Model) context.getAttribute("model");
			mod.CheckAuthorizationWithUserId(id, _token, mod);	
			int ratingId = mod.AddNewRating(rating);
			return ratingId;
		} catch (Exception ex) {
			throw new ResponseWithException(ex.getMessage(),Response.Status.BAD_REQUEST);
		}
	}
	
	/**
	 * Zorgt ervoor dat een user een rating kan wijzigen
	 * @param id het id van de user
	 * @param rating de rating die de user gewijzigd wilt hebben
	 * @param _token voor identificatie
	 * @return true als rating gewijzigd is en false als die niet gewijzigd is
	 */
	@PUT
	@Path("{id}/ratings")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public boolean UpdateRatingForUser(@PathParam("id") int id, Rating rating, @HeaderParam("token") String _token) {
		if (rating.getUserId() != id) {
			throw new ResponseWithException("UserID moet overeen komen met rating UserID",Response.Status.BAD_REQUEST);
		}
		try {
			// Haal het model op en wijzig een rating
			Model mod = (Model) context.getAttribute("model");
			mod.CheckAuthorizationWithUserId(id, _token, mod);	
			return mod.UpdateRating(rating);
		} catch (Exception ex) {
			throw new ResponseWithException(ex.getMessage(),Response.Status.BAD_REQUEST);
		}
	}
	
	/**
	 * Zorgt ervoor dat een user een rating kan verwijderen.
	 * @param id id het id van de user
	 * @param ratingsid het ratingId van de rating die verwijderd moet worden
	 * @param _token voor identificatie
	 * @return true als rating verwijderd is en false als die niet verwijderd is
	 */
	@DELETE
	@Path("{id}/ratings/{ratingsid}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public boolean DeleteRating(@PathParam("id") int id, @PathParam("ratingsid")int ratingsid, @HeaderParam("token") String _token) {
		if (ratingsid == 0) {
			throw new ResponseWithException("Ratingsid moet groter zijn als 0",Response.Status.BAD_REQUEST);
		}
		try {
			// Haal het model op en wijzig een rating
			Model mod = (Model) context.getAttribute("model");
			mod.CheckAuthorizationWithUserId(id, _token, mod);	
			return mod.DeleteRating(ratingsid);
		} catch (Exception ex) {
			throw new ResponseWithException(ex.getMessage(),Response.Status.BAD_REQUEST);
		}
	}
	
	

}
