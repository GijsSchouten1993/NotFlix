package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Userlogin {

	private String password,nickname;
	
	public String getPassword() {
		return password;
	}

	public String getNickname() {
		return nickname;
	}

	public Userlogin(String _nickname, String _password) {
		super();
		this.nickname = _nickname;
		this.password = _password;
	}
	
	public Userlogin(){}
	
	
}
