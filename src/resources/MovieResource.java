package resources;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Model;
import model.Movie;
import model.MovieAverage;
import model.Rating;
import model.ResponseWithException;

@Path("/movies")
public class MovieResource {
	
	@Context
	ServletContext context;
	
	/**
	 * Haalt een film op op basis van id.
	 * @param id het id waarop gezocht moet worden
	 * @param token voor beveiliging
	 * @return een film met het meegegeven id
	 */
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Movie getMovie(@PathParam("id") int id, @HeaderParam("token") String token) {
		try{
			Model model = (Model) context.getAttribute("model");
			model.CheckAuthorization(token, model);
			return model.getMovieById(id);
		} catch (Exception e){
			throw new ResponseWithException(e.getMessage(),Response.Status.BAD_REQUEST);
		}
		
	}
	
	/**
	 * Haalt alle films op.
	 * @param token voor identificatie
	 * @return een lijst van alle movies
	 */	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public ArrayList<Movie> getMovies() {
		try{
			//haal het model op en verkrijg alle movies
			Model model = (Model) context.getAttribute("model");
			return model.getMovies();
		} catch (Exception ex) {
 			throw new ResponseWithException(ex.getMessage(),Response.Status.BAD_REQUEST);
		}
	}
	
	/**
	 * Haalt alle films op met een bepaalde titel.
	 * @param title de titel waarop gezocht wordt
	 * @param token de token voor identificatie
	 * @return alle films met de meegegeven titel of een deel van de titel
	 */
	@GET
	@Path("/query")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public ArrayList<Movie> getMovieTitle(@QueryParam("title") String title, @HeaderParam("token") String token) {
		try {
			//haal het model op en verkrijg movies op basis van titel
			Model model = (Model) context.getAttribute("model");
			return model.getMoviesByTitle(title);
		} catch (Exception e) {
			throw new ResponseWithException(e.getMessage(),Response.Status.BAD_REQUEST);
		}
	}
	
	
	/**
	 * Haalt de gemiddelde rating op per film.
	 * @return een lijst van movieId's met een gemiddelde rating
	 */
	@GET
	@Path("/ratings")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public ArrayList<MovieAverage> getMovieRatings(@HeaderParam ("token") String token) {
		try{
			//haal het model op en verkrijg all ratings van een movie
			Model model = (Model) context.getAttribute("model");
			return model.getAverageRating();
		} catch (Exception ex) {
			throw new ResponseWithException(ex.getMessage(),Response.Status.BAD_REQUEST);
		}
	}

	
}
