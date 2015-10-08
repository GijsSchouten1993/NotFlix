package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MovieAverage {

	private int movieId;
	double rating;
	
	public MovieAverage() {
		
	}

	/**
	 * @param movieId
	 * @param averageRating
	 */
	public MovieAverage(int movieId, double averageRating) {
		super();
		this.movieId = movieId;
		this.rating = averageRating;
	}

	/**
	 * @return the movieId
	 */
	public int getMovieId() {
		return movieId;
	}

	/**
	 * @return the rating
	 */
	public double getRating() {
		return rating;
	}
	
	
}
