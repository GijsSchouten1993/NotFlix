package model;

public class Rating {
	
	private static int increment = 1;
	private int ratingId;
	private int stars;
	

	public Rating() {
		
	}
	
	public Rating(int stars) {
		
		assert stars >= 0 : "rating kan niet onder nul zijn";
		assert stars <= 5 : "rating kan niet hoger zijn dan 5";
		
		this.stars = stars;
		
		ratingId = ratingId + increment;
	}



	public int getStars() {
		return stars;
	}

}
