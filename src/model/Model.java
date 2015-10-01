package model;

import java.util.ArrayList;
import java.util.Date;

public class Model {

	public ArrayList<Movie> movies = new ArrayList();
	public ArrayList<User> users = new ArrayList();
	public ArrayList<Rating> ratings = new ArrayList();
	
	
	public Model()
	{
		Movie mov = new Movie(1,"Spectre", new Date(),120,"Blake fjf","James bond 007");
		movies.add(mov);
		
		//Gijs gebruiker
		User usr = new User("Schouten","Gijs","test","test");
		users.add(usr);
		
		//Alex
		User usr2 = new User("Uncu","Alexander","test2","test2");
		users.add(usr2);
		
	}
	
}
