package model;

public class Rating {
	
	private int stars;
	

	public Rating() {
		
	}
	
	public Rating(int stars) {
		
		assert stars >= 0 : "rating kan niet onder nul zijn";
		assert stars <= 5 : "rating kan niet hoger zijn dan 5";
		
		this.stars = stars;
	}



	public int getStars() {
		return stars;
	}

}
