package resources;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

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

import model.Model;
import model.ResponseWithException;
import model.Token;
import model.User;
import model.Userlogin;

@Path("/authenticate")
public class AuthenticationResource {
	@Context
	ServletContext context;

	/**
	 * Zorgt ervoor dat een nieuwe token gegenereert kan worden
	 * @param login de nickname en het password dat gecontroleerd wordt
	 * @return een Token met het gegenereerde token en userId
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED , "application/x-www-form-urlencoded"})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED , "application/x-www-form-urlencoded"})
	public Token getNewToken(Userlogin login) {
		try {
			// Haal het model op en genereer nieuw token
			Model mod = (Model) context.getAttribute("model");
			Token tokkie = mod.GetNewToken(login);
			
			return tokkie;
		} catch (Exception ex) {
			throw new ResponseWithException(ex.getMessage(),Response.Status.BAD_REQUEST);
		}
	}

}
