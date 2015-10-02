package model;

import java.util.ArrayList;
import java.util.Date;

public class Model {

	private ArrayList<Movie> movies = new ArrayList();
	private ArrayList<User> users = new ArrayList();
	private ArrayList<Rating> ratings = new ArrayList();

	public Model() {
		Movie mov = new Movie(1, "Spectre", new Date(), 120, "Blake fjf", "James bond 007");
		movies.add(mov);

		// Gijs gebruiker
		User usr = new User("Schouten", "Gijs", "test", "test");
		users.add(usr);

		// Alex
		User usr2 = new User("Uncu", "Alexander", "test2", "test2");
		users.add(usr2);

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
	
	//Voeg een nieuwe gebruiker toe
	public int AddNewUser(User user)
	{
		user.setUserId();
		users.add(user);
		return user.getUserId();
	}
	
	//Verkijg alle gebruikers
	public ArrayList<User> GetUsers()
	{
		return this.users;
	}
	
	public int AddNewRating(Rating)
	{
		
	}

}
