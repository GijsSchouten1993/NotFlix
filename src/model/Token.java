package model;

public class Token {
	private int userId;
	private String token;
	public int getUserId() {
		return userId;
	}
	public String getToken() {
		return token;
	}
	
	public Token(int userId, String token) {
		super();
		this.userId = userId;
		this.token = token;
	}
	
}
