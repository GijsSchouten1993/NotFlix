package model;

import java.util.ArrayList;
import java.util.Date;

public class Model {

	public ArrayList<Movie> movies = new ArrayList();
	public ArrayList<User> user = new ArrayList();
	public ArrayList<Rating> ratings = new ArrayList();
	
	
	public Model()
	{
		Movie mov = new Movie(1,"Spectre", new Date(),120,"Blake fjf","James bond 007");
		movies.add(mov);
		
			
		
	}
	
}
