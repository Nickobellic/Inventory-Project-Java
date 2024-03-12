package model;

public class Login {
	private String username;
	private String password;
	private String role;
	
	public String getPass() {
		return password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setUsername(String name) {
		this.username = name;
	}
	
	public void setPassword(String pass) {
		this.password = pass;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
}
