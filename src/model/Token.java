package model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Een token die je kan linken aa een bepaalde user
 */
@XmlRootElement
public class Token {
	private int userId;
	private String token;
	
	public int getUserId() {
		return userId;
	}
	public String getToken() {
		return token;
	}
	
	public Token() {
		
	}
	
	public Token(int userId, String token) {
		super();
		this.userId = userId;
		this.token = token;
	}
	
	
	
}
