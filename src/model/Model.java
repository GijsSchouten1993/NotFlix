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
		
		Movie movie4 = new Movie(1000, "The Martian", new Date(), 120, "Blake fjf", "James bond 007");
		movies.add(movie4);

		Movie movie5 = new Movie(1001, "Bourne Identity", new Date(), 150, "George Lucs", "Star wars Episode 9");
		movies.add(movie5);
		
		Movie movie6 = new Movie(1002, "Bourne Supremacy", new Date(), 140, "Waschowski siblings", "Depp owns");
		movies.add(movie6);
		
		Movie movie7 = new Movie(1000, "Bourne Ultimatum", new Date(), 120, "Blake fjf", "James bond 007");
		movies.add(movie7);

		Movie movie8 = new Movie(1001, "The Raid: redemption", new Date(), 150, "George Lucs", "Star wars Episode 9");
		movies.add(movie8);
		
		Movie movie9 = new Movie(1002, "The raid 2", new Date(), 140, "Waschowski siblings", "Depp owns");
		movies.add(movie9);
		
		Movie movie10 = new Movie(1000, "Underworld", new Date(), 120, "Blake fjf", "James bond 007");
		movies.add(movie10);

		Movie movie11 = new Movie(1001, "The Incredibles", new Date(), 150, "George Lucs", "Star wars Episode 9");
		movies.add(movie11);
		
		Movie movie12 = new Movie(1002, "Fantastic four", new Date(), 140, "Waschowski siblings", "Depp owns");
		movies.add(movie12);
		
		Movie movie13 = new Movie(1000, "The Avengers", new Date(), 120, "Blake fjf", "James bond 007");
		movies.add(movie13);

		Movie movie14 = new Movie(1001, "Avengers: Age of Ultron", new Date(), 150, "George Lucs", "Star wars Episode 9");
		movies.add(movie14);
		
		Movie movie15 = new Movie(1002, "The Rock", new Date(), 140, "Waschowski siblings", "Depp owns");
		movies.add(movie15);

		// Gijs gebruiker
		User usr = new User("Schouten", "Gijs", "test", "test");
		users.add(usr);

		// Alex
		User usr2 = new User("Uncu", "Alexander", "test2", "test2");
		users.add(usr2);

		Rating rat = new Rating(1, 1, 4);
		ratings.add(rat);
		
		Rating rat2 = new Rating(1, 2, 1);
		ratings.add(rat2);
		
		Rating rat3 = new Rating(2, 1, 2);
		ratings.add(rat3);
		
		Rating rat4 = new Rating(2, 2, 2);
		ratings.add(rat4);
		
		Rating rat5 = new Rating(1, 7, 4);
		ratings.add(rat5);
		
		Rating rat6 = new Rating(1, 12, 3);
		ratings.add(rat6);
		
		Rating rat7 = new Rating(2, 9, 2.5);
		ratings.add(rat7);
		
		Rating rat8 = new Rating(2, 15, 4.5);
		ratings.add(rat8);
		
		Rating rat9 = new Rating(1, 1, 4);
		ratings.add(rat9);
		
		Rating rat10 = new Rating(1, 7, 1);
		ratings.add(rat10);
		
		Rating rat11 = new Rating(2, 14, 2);
		ratings.add(rat11);
		
		Rating rat12 = new Rating(2, 7, 5);
		ratings.add(rat12);
	}
	
	/**
	 * Maakt een lijst waarin de gemiddeldes staan van films
	 * @return een lijst met een movieId en de gemiddelde rating die erbij hoort
	 */
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
				//rond het getal naar boven af naar een heel of half getal.
				average = Math.round(average * 2) / 2.0f;
				averages.add(new MovieAverage(movie.getNumberInternal(), average));
			}
			
		}
		return averages;

	}
	

	/**
	 * Haalt user op op basis van id.
	 * @param ID het id van de user
	 * @return de user met het gegeven id
	 */
	public User GetUserById(int ID) {
		for (User user : users) {
			if (user.getUserId() == ID) {
				return user;
			}
		}
		return null;
	}
	
	/**
	 * Haalt movie op op basis van id.
	 * @param ID het id van de movie
	 * @return de movie met het gegeven id
	 */
	public Movie getMovieById(int id) {
		for (Movie movie : movies) {
			if (movie.getNumberInternal() == id) {
				return movie;
			}
		}
		return null;
	}
	
	/**
	 * Geeft films terug op basis van titel
	 * @param title de titel waarop gezocht wordt
	 * @return een lijst met movies die de titel bevatten
	 */
	public ArrayList<Movie> getMoviesByTitle(String title){
		ArrayList<Movie> moviess = new ArrayList<>();
		for (Movie movie : movies) {
			if (movie.getTitel().toLowerCase().contains(title.toLowerCase())) {
				moviess.add(movie);
			}
		}		
		return moviess;
	}

	/**
	 * Voegt een nieuwe user toe.
	 * @param user De user die toegevoegd moet worden
	 * @return het id van de toegevoegde user
	 * @throws Exception geeft een bericht wanneer nickname van nieuwe user al bestaat
	 */
	public int AddNewUser(User user) throws Exception {
		if (CheckIfNicknameAlreadyExists(user)) {
			throw new Exception("Nickname bestaat al");
		}

		user.setUserId();
		users.add(user);
		return user.getUserId();
	}


	
	/**
	 * Controleert of nickname al bestaat.
	 * @param _user De user waarvan de nickname gecheckt moet worden
	 * @return true als bestaat en false als niet bestaat
	 */
	public boolean CheckIfNicknameAlreadyExists(User _user) {
		for (User user : this.users) {
			if (user.getNickname().equals(_user.getNickname())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checkt of de user's inloggegevens kloppen.
	 * @param _username gebruikersnaam van de user
	 * @param _password wachtwoord van user
	 * @return geeft de user terug als gegevens kloppen
	 */
	public User CheckIfUserCredentialsAreValid(String _username, String _password) {
		for (User user : this.users) {
			if (user.getNickname().equals(_username) && user.getPassword().equals(_password)) {
				return user;
			}
		}
		return null;
	}
	
	/**
	 * Kijkt of een bepaalde rating al bestaat.
	 * @param _rating de rating die gecheckt moet worden
	 * @return true als rating bestaat en false als die niet bestaat
	 */
	public boolean CheckIfRatingsAlreadyExists(Rating _rating) {
		for (Rating rat : this.ratings) {
			if (rat.getUserId() == _rating.getUserId() && rat.getMovieId() == _rating.getMovieId()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Voegt een rating toe
	 * @param _rating de rating die toegevoegd moet worden
	 * @return het id van de nieuwe rating
	 * @throws Exception gooit exception als rating al bestaat
	 */
	public int AddNewRating(Rating _rating) throws Exception {
		CheckIfRatingIscorrect(_rating);
		if (CheckIfRatingsAlreadyExists(_rating)) {
			throw new Exception("Rating bestaat al");
		}
		_rating.setRatingId();
		this.ratings.add(_rating);
		return _rating.getRatingId();

	}

	/**
	 * Haalt een rating op op basis van id.
	 * @param ID het id van de rating die je wilt hebben
	 * @return de rating waarvan je het id hebt meegegeven
	 */
	public Rating GetRatingById(int ID) {
		for (Rating rat : this.ratings) {
			if (rat.getRatingId() == ID) {
				return rat;
			}
		}
		return null;
	}

	/**
	 * Haalt alle ratings van een gebruiker op.
	 * @param _userId het id van de gebruiker waar je de ratings van wilt hebben
	 * @return
	 */
	public ArrayList<Rating> GetAllRatingsForUser(int _userId) {
		ArrayList<Rating> ratingsToReturn = new ArrayList<Rating>();
		for (Rating rat : this.ratings) {
			if (rat.getUserId() == _userId) {
				ratingsToReturn.add(rat);
			}
		}
		return ratingsToReturn;
	}
	
	/**
	 * Haalt de ratings op van een bepaalde movie.
	 * @param movieId het movieId waarvan je de ratings wilt hebben.
	 * @return een lijst met alle ratings van de betreffende film
	 */
	public ArrayList<Rating> getAllRatingsForMovie(int movieId) {
		ArrayList<Rating> ratingsToReturn = new ArrayList<Rating>();
		for (Rating rating : ratings) {
			if (rating.getMovieId() == movieId) {
				ratingsToReturn.add(rating);
			}
		}
		return ratingsToReturn;
	}

	/**
	 * Kijkt of de gegeven rating correct is.
	 * @param _rating de rating die gecheckt moet worden
	 * @throws Exception geeft exception als rating niet klopt
	 */
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
	
	/**
	 * Update een rating.
	 * @param _rating de rating die geupdate moet worden
	 * @return true als rating geupdate is en false zo niet
	 * @throws Exception gooit exception als rating niet klopt
	 */
	public boolean UpdateRating(Rating _rating) throws Exception {
		CheckIfRatingIscorrect(_rating);
		Rating rat = GetRatingById(_rating.getRatingId());
		rat.setStars(_rating.getStars());
		rat.setMovieId(_rating.getMovieId());
		return true;
	}

	/**
	 * Delete een rating
	 * @param ratingsId het id van de rating die gedelete moet worden
	 * @return true als rating gedelete is en false zo niet
	 */
	public boolean DeleteRating(int ratingsId) {
		Rating rat = this.GetRatingById(ratingsId);
		if (rat == null) {
			return false;
		}
		this.ratings.remove(rat);
		return true;
	}
	
	/**
	 * Genereert een nieuwe random token
	 * @return het gegenereerde token
	 */
	private String GenerateNewTokenStringForUser()
	{
		 UUID idOne = UUID.randomUUID();
		return idOne.toString();
	}

	/**
	 * kijkt of een token al bestaat
	 * @param _token de token die gecheckt wordt
	 * @return returnt token als die bestaat en null zo niet
	 */
	public Token CheckIfTokenExists(String _token)
	{
		for (Token tok : this.tokens) {
			if (tok.getToken().equals(_token)) {
				return tok;
			}
		}
		return null;
	}
	
	/**
	 * Maakt een nieuwe token aan voor een gebruiker.
	 * @param _login de login van de user
	 * @return de token die meegegeven wordt aan de user
	 * @throws Exception geeft exception als login ongeldig is
	 */
	public Token GetNewToken(Userlogin _login) throws Exception {
		User user = CheckIfUserCredentialsAreValid(_login.getNickname(), _login.getPassword());
		if (user == null) {
			throw new Exception("Ongeldige login");
		}
		
		String tokenValue = GenerateNewTokenStringForUser();
		
		Token tok = new Token(user.getUserId(),tokenValue);
		this.tokens.add(tok);
		
		return tok;
	}
	
		/**
		 * Kijkt of het request het token bevat en 
		 * de userID in de url overeenkomt met het token.
		 * @param id id van de user
		 * @param _token de token die overeen moet komen met id van user
		 * @param mod het model waarmee je het token kan checken
		 */
		public void CheckAuthorizationWithUserId(int id, String _token, Model mod) {
			Token tok = mod.CheckIfTokenExists(_token);
			if(tok == null)
				throw new ResponseWithException("Geen geldige token gevonden, vul het token in de custom header 'token'",Response.Status.UNAUTHORIZED);
			
			if(tok.getUserId() != id)
			{
				throw new ResponseWithException("U heeft onvoldoende rechten voor dit eindpunt",Response.Status.BAD_REQUEST);
			}
		}
		
		/**
		 * kijkt of het request een geldig token bevat.
		 * @param _token het token die geldig moet zijn
		 * @param mod het model waarmee je het token kan checken
		 */
		public void CheckAuthorization(String _token, Model mod)
		{
			Token tok = mod.CheckIfTokenExists(_token);
			if(tok == null)
				throw new ResponseWithException("Geen geldige token gevonden, vul het token in de custom header 'token'",Response.Status.UNAUTHORIZED);
		}
		
		/**
		 * 
		 * @return the users
		 */
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
