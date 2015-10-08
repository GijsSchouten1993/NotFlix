package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.ws.rs.core.Response;

public class Model {

	private ArrayList<Movie> movies = new ArrayList();
	private ArrayList<User> users = new ArrayList();
	private ArrayList<Rating> ratings = new ArrayList();
	private ArrayList<Token> tokens = new ArrayList();

	public Model() {
		Movie mov = new Movie(1000, "Spectre", new Date(), 120, "Blake fjf", "James bond 007");
		movies.add(mov);

		Movie movie2 = new Movie(1001, "Star wars", new Date(), 150, "George Lucs", "Star wars Episode 9");
		movies.add(movie2);
		
		Movie movie3 = new Movie(1002, "Black Mass", new Date(), 140, "Waschowski siblings", "Depp owns");
		movies.add(movie3);

		// Gijs gebruiker
		User usr = new User("Schouten", "Gijs", "test", "test");
		users.add(usr);

		// Alex
		User usr2 = new User("Uncu", "Alexander", "test2", "test2");
		users.add(usr2);

		Rating rat = new Rating(1, 1, 4);
		ratings.add(rat);
		
		Rating rat2 = new Rating(1, 2, 4);
		ratings.add(rat2);
		
		Rating rat3 = new Rating(2, 1, 2);
		ratings.add(rat3);
		
		Rating rat4 = new Rating(2, 2, 1);
		ratings.add(rat4);
	}
	
	public ArrayList<MovieAverage> getAverageRating() {
		ArrayList<MovieAverage> averages = new ArrayList<MovieAverage>();
		
		for (Movie movie : movies) {
			ArrayList<Rating> ratingsPerMovie = getAllRatingsForMovie(movie.getNumberInternal()); 
			
			if (ratingsPerMovie.size() > 0) {
				double total = 0;
				for (Rating rating : ratingsPerMovie) {
					total += rating.getStars();
				}
				double average = total / ratingsPerMovie.size();
				averages.add(new MovieAverage(movie.getNumberInternal(), average));
			}
			
		}
		return averages;

	}
	

	// Haal gebruiker op obv ID
	public User GetUserById(int ID) {
		for (User user : users) {
			if (user.getUserId() == ID) {
				return user;
			}
		}
		return null;
	}
	
	public Movie getMovieById(int id) {
		for (Movie movie : movies) {
			if (movie.getNumberInternal() == id) {
				return movie;
			}
		}
		return null;
	}
	
	//geeft film terug op basis van titel
	public ArrayList<Movie> getMoviesByTitle(String title) throws Exception {
		ArrayList<Movie> moviess = new ArrayList<>();
		for (Movie movie : movies) {
			if (movie.getTitel().toLowerCase().contains(title.toLowerCase())) {
				moviess.add(movie);
			}
		}		
		return moviess;
	}

	// Voeg een nieuwe gebruiker toe
	public int AddNewUser(User user) throws Exception {
		if (CheckIfNicknameAlreadyExists(user)) {
			throw new Exception("Nickname bestaat al");
		}

		user.setUserId();
		users.add(user);
		return user.getUserId();
	}


	
	//Kijk of een gebruikersnaam al voorkomt
	public boolean CheckIfNicknameAlreadyExists(User _user) {
		for (User user : this.users) {
			if (user.getNickname().equals(_user.getNickname())) {
				return true;
			}
		}
		return false;
	}

	//Kijk of de gebruiker kan inloggen
	public User CheckIfUserCredentialsAreValid(String _username, String _password) {
		for (User user : this.users) {
			if (user.getNickname().equals(_username) && user.getPassword().equals(_password)) {
				return user;
			}
		}
		return null;
	}
	
	//Kijk of een rating al bestaat
	public boolean CheckIfRatingsAlreadyExists(Rating _rating) {
		for (Rating rat : this.ratings) {
			if (rat.getUserId() == _rating.getUserId() && rat.getMovieId() == _rating.getMovieId()) {
				return true;
			}
		}
		return false;
	}

	//Voeg een nieuwe rating toe
	public int AddNewRating(Rating _rating) throws Exception {
		CheckIfRatingIscorrect(_rating);
		if (CheckIfRatingsAlreadyExists(_rating)) {
			throw new Exception("Rating bestaat al");
		}
		_rating.setRatingId();
		this.ratings.add(_rating);
		return _rating.getRatingId();

	}

	// Haal rating op obv ID
	public Rating GetRatingById(int ID) {
		for (Rating rat : this.ratings) {
			if (rat.getRatingId() == ID) {
				return rat;
			}
		}
		return null;
	}

	//Haal alle ratings voor een gebruiker op
	public ArrayList<Rating> GetAllRatingsForUser(int _userId) {
		ArrayList<Rating> ratingsToReturn = new ArrayList<Rating>();
		for (Rating rat : this.ratings) {
			if (rat.getUserId() == _userId) {
				ratingsToReturn.add(rat);
			}
		}
		return ratingsToReturn;
	}
	
	public ArrayList<Rating> getAllRatingsForMovie(int movieId) {
		ArrayList<Rating> ratingsToReturn = new ArrayList<Rating>();
		for (Rating rating : ratings) {
			if (rating.getMovieId() == movieId) {
				ratingsToReturn.add(rating);
			}
		}
		return ratingsToReturn;
	}

	//Kijk of de rating sterren binnen het bereik vallen
	public void CheckIfRatingIscorrect(Rating _rating) throws Exception {
		if (_rating.getStars() > 5) {
			throw new Exception("Rating mag niet groter zijn als 5");
		}
		if (_rating.getStars() < 0.5) {
			throw new Exception("Rating mag niet kleiner zijn als 0.5");
		}

		if (_rating.getStars() % 0.5 != 0) {
			throw new Exception("Rating moet een meervoud zijn van 0.5");
		}

	}
	
	//Update een rating
	public boolean UpdateRating(Rating _rating) throws Exception {
		CheckIfRatingIscorrect(_rating);
		Rating rat = GetRatingById(_rating.getRatingId());
		rat.setStars(_rating.getStars());
		rat.setMovieId(_rating.getMovieId());
		return true;
	}

	//Verwijder een rating
	public boolean DeleteRating(int ratingsId) {
		Rating rat = this.GetRatingById(ratingsId);
		if (rat == null) {
			return false;
		}
		this.ratings.remove(rat);
		return true;
	}
	
	//Genereer een nieuwe token
	private String GenerateNewTokenStringForUser()
	{
		 UUID idOne = UUID.randomUUID();
		return idOne.toString();
	}

	//Kijk of een token bestaat
	public Token CheckIfTokenExists(String _token)
	{
		for (Token tok : this.tokens) {
			if (tok.getToken().equals(_token)) {
				return tok;
			}
		}
		return null;
	}
	
	//Maak een nieuwe token aan
	public String GetNewToken(Userlogin _login) throws Exception {
		User user = CheckIfUserCredentialsAreValid(_login.getNickname(), _login.getPassword());
		if (user == null) {
			throw new Exception("Ongeldige login");
		}
		
		String tokenValue = GenerateNewTokenStringForUser();
		
		Token tok = new Token(user.getUserId(),tokenValue);
		this.tokens.add(tok);
		
		return tokenValue;
	}
	
	//Check of het request het token bevat en de gebruikerID in de url overeenkomt met het token
		public void CheckAuthorizationWithUserId(int id, String _token, Model mod) {
			Token tok = mod.CheckIfTokenExists(_token);
			if(tok == null)
				throw new ResponseWithException("Geen geldige token gevonden, vul het token in de custom header 'token'",Response.Status.UNAUTHORIZED);
			
			if(tok.getUserId() != id)
			{
				throw new ResponseWithException("U heeft onvoldoende rechten voor dit eindpunt",Response.Status.BAD_REQUEST);
			}
		}
		
		//Check of het request een geldig token bevat
		public void CheckAuthorization(String _token, Model mod)
		{
			Token tok = mod.CheckIfTokenExists(_token);
			if(tok == null)
				throw new ResponseWithException("Geen geldige token gevonden, vul het token in de custom header 'token'",Response.Status.UNAUTHORIZED);
		}
		
		// Verkijg alle gebruikers
		public ArrayList<User> GetUsers() {
			return this.users;
		}

		/**
		 * @return the movies
		 */
		public ArrayList<Movie> getMovies() {
			return movies;
		}

		/**
		 * @return the ratings
		 */
		public ArrayList<Rating> getRatings() {
			return ratings;
		}

		/**
		 * @return the tokens
		 */
		public ArrayList<Token> getTokens() {
			return tokens;
		}
		
		
		
		

}
