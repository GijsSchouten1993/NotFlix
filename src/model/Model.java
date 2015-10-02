package model;

import java.util.ArrayList;
import java.util.Date;

public class Model {

	private ArrayList<Movie> movies = new ArrayList();
	private ArrayList<User> users = new ArrayList();
	private ArrayList<Rating> ratings = new ArrayList();

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

	public boolean CheckIfNicknameAlreadyExists(User _user) {
		for (User user : this.users) {
			if (user.getNickname().equals(_user.getNickname())) {
				return true;
			}
		}
		return false;
	}

	public boolean CheckIfRatingsAlreadyExists(Rating _rating) {
		for (Rating rat : this.ratings) {
			if (rat.getUserId() == _rating.getUserId() && rat.getMovieId() == _rating.getMovieId()) {
				return true;
			}
		}
		return false;
	}

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

	public ArrayList<Rating> GetAllRatingsForUser(int _userId) {
		ArrayList<Rating> ratingsToReturn = new ArrayList<Rating>();
		for (Rating rat : this.ratings) {
			if (rat.getUserId() == _userId) {
				ratingsToReturn.add(rat);
			}
		}
		return ratingsToReturn;
	}
	
	public void CheckIfRatingIscorrect(Rating _rating) throws Exception
	{
		if(_rating.getStars() > 5)
		{
			throw new Exception("Rating mag niet groter zijn als 5");
		}
		if(_rating.getStars() < 0.5)
		{
			throw new Exception("Rating mag niet kleiner zijn als 0.5");
		}
		
		if(_rating.getStars() % 0.5 != 0)
		{
			throw new Exception("Rating moet een meervoud zijn van 0.5");
		}
		
	}

	public boolean UpdateRating(Rating _rating) throws Exception {
		CheckIfRatingIscorrect(_rating);
		Rating rat = GetRatingById(_rating.getRatingId());
		rat.setStars(_rating.getStars());
		rat.setMovieId(_rating.getMovieId());
		return true;
	}

}
