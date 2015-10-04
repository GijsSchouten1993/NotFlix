package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

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

	// Voeg een nieuwe gebruiker toe
	public int AddNewUser(User user) throws Exception {
		if (CheckIfNicknameAlreadyExists(user)) {
			throw new Exception("Nickname bestaat al");
		}

		user.setUserId();
		users.add(user);
		return user.getUserId();
	}

	// Verkijg alle gebruikers
	public ArrayList<User> GetUsers() {
		return this.users;
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

}
