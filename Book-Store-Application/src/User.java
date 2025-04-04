
/*
 * @Author: Hassan
 */

public abstract class User {
	private String username;
	private String password;

	public User(String username, String password) {
		setUsername(username);
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public abstract boolean login();

	public abstract boolean logout();
}
