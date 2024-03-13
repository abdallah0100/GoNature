package entities;

import java.io.Serializable;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String id;
	private String workerNumber;
	private String email;
	private String role;
	private String parkWork;
	private boolean foundInDB;
	
	public User(String firstName, String lastName, String username,String password,String id, String workerNumber,String email,String role,String parkWork) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setUsername(username);
		this.setPassword(password);
		this.setID(id);
		this.setWorkerNumber(workerNumber);
		this.setEmail(email);
		this.setRole(role);
		this.setParkWork(parkWork);
	}
		 
	 
	 public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getID() {
		return id;
	}

	public String getWorkerNumber() {
		return workerNumber;
	}

	public String getEmail() {
		return email;
	}

	public String getRole() {
		return role;
	}

	public String getParkWork() {
		return parkWork;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setID(String iD) {
		id = iD;
	}

	public void setWorkerNumber(String workerNumber) {
		this.workerNumber = workerNumber;
	}

	public void setEmail(String email) {
		email = email;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}


	public String getPassword() {
		return password;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setParkWork(String parkWork) {
		this.parkWork = parkWork;
	}
	
	public boolean isFoundInDB() {
		return foundInDB;
	}

	public void setFoundInDB(boolean foundInDB) {
		this.foundInDB = foundInDB;
	}
	public User (String userName,String password) {
		this.username=userName;
		this.password=password;
	}
	 
}
