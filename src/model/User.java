package model;

/**
 * This class allows you to create a user
 * @author Alec
 *
 */
public class User {
	
	private static int increment;
	private int userId;
	private String lastName;
	private String preposition; //tussenvoegsel
	private String firstName;
	private String nickname;
	private String password;

	
	public User() {
		
	}
	
	public User(String lastName, String preposition, String firstName, String nickname, String password) {
		super();
		this.lastName = lastName;
		this.preposition = preposition;
		this.firstName = firstName;
		this.nickname = nickname;
		this.password = password;
		
		userId = userId + increment;
	}
	
	public User(String lastName, String firstName, String nickname, String password) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.nickname = nickname;
		this.password = password;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the preposition
	 */
	public String getPreposition() {
		return preposition;
	}

	/**
	 * @param preposition the preposition to set
	 */
	public void setPreposition(String preposition) {
		this.preposition = preposition;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	

}
