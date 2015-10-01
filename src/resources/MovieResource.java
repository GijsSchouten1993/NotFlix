package resources;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Movie;

@Path("/movies")
public class MovieResource {
	
	Movie movie = new Movie(35564, "The Martian", new Date(), 133, 
			"Ridley Scott", "Guy alone on Mars");
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Movie getMovie(@PathParam("id") int id) {
		return movie;
	}
}