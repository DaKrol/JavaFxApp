package Data;

import java.io.Serializable;

public class User implements Serializable {
	private int id;
	private String login;
	private String haslo;
	public int getId() {
		return id;
	}
	public String getLogin() {
		return login;
	}
	public String getHaslo() {
		return haslo;
	}
	public User(int id, String login, String haslo) {
		super();
		this.id = id;
		this.login = login;
		this.haslo = haslo;
	}
	
	
	
}
