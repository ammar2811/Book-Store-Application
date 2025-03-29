public class User {
	private String username;
	private String password;
	private boolean isLoggedIn;

	public User(String username, String password) {
		setUsername(username);
		this.password = password;
		isLoggedIn = false;
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

	public abstract void login();

	public abstract void logout();
}
