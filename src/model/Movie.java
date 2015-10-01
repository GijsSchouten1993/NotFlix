package model;

public class Movie {
	
	private int numberInternal;
	private static int increment = 1;
	private int numberIMDB;
	private String titel;
	private String datePublished;
	private int lengthFilm;
	private String director;
	private String description;
	
	public Movie() {
		
	}
	
	public Movie(int numberIMDB, String titel, String datePublished, int lengthFilm, String director,
			String description) {
		this.numberIMDB = numberIMDB;
		this.titel = titel;
		this.datePublished = datePublished;
		this.lengthFilm = lengthFilm;
		this.director = director;
		this.description = description;
		
		numberInternal = numberInternal + increment;
	}

	/**
	 * @return the titel
	 */
	public String getTitel() {
		return titel;
	}

	/**
	 * @param titel the titel to set
	 */
	public void setTitel(String titel) {
		this.titel = titel;
	}

	/**
	 * @return the datePublished
	 */
	public String getDatePublished() {
		return datePublished;
	}

	/**
	 * @param datePublished the datePublished to set
	 */
	public void setDatePublished(String datePublished) {
		this.datePublished = datePublished;
	}

	/**
	 * @return the lengthFilm
	 */
	public int getLengthFilm() {
		return lengthFilm;
	}

	/**
	 * @param lengthFilm the lengthFilm to set
	 */
	public void setLengthFilm(int lengthFilm) {
		this.lengthFilm = lengthFilm;
	}

	/**
	 * @return the director
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * @param director the director to set
	 */
	public void setDirector(String director) {
		this.director = director;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	
	
	
 
}
