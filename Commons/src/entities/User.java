package entities;

import java.io.Serializable;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String workerNumber;
	private String email;
	private String role;
	private String parkWork;
	private boolean foundInDB;
	
	public User(String firstName, String lastName,String id,String password ,String workerNumber,String email,String role,String parkWork) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setPassword(password);
		this.setID(id);
		this.setWorkerNumber(workerNumber);
		this.setEmail(email);
		this.setRole(role);
		this.setParkWork(parkWork);
	}
		 
	public User (String id,String password) {
		this.userName = id;
		this.password = password;
	}
	
	 public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getID() {
		return userName;
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
		userName = iD;
	}

	public void setWorkerNumber(String workerNumber) {
		this.workerNumber = workerNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
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
}
