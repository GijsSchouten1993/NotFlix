package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Rating {
	
	private static int increment = 1;
	private int ratingId;
	private double stars;
	private int userId;
	private int movieId;

	public Rating() {
		
	}
	
	public Rating(int _userId, int _movieId, double stars) {
		
		this.stars = stars;
		this.userId = _userId;
		this.movieId = _movieId;
		
		this.ratingId = increment++;
	}
	
	public void setStars(double stars) {
		this.stars = stars;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}


	public int getRatingId() {
		return ratingId;
	}

	public double getStars() {
		return stars;
	}

	public int getUserId() {
		return userId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setRatingId() {
		this.ratingId = increment++;
	}
}
