package entities;

import java.io.Serializable;

/**
 * Represents a user in the system, which could be an entry worker, department manager, park manager , service agent.
 * This class includes information about the user's personal details, authentication credentials, role, and associated park, if applicable.
 */
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	private String firstName; // The first name of the user.
	private String lastName; // The last name of the user.
	private String userName; // The user name of the user to login into the system.
	private String password; // The password of the user to login into the system.
	private String workerNumber; // The number of the worker.
	private String email; // The email for the user.
	private String role; // The specific role for the user. 
	private boolean foundInDB; // Flag that saves if the user is found in the DB.
	private String parkName; // The park name that the user is working in.
	private Park park; // The specific park that the user is working in.
	
	/**
	 * Constructs a new User with detailed information.
	 * @param firstName
	 * @param lastName
	 * @param id
	 * @param password
	 * @param workerNumber
	 * @param email
	 * @param role
	 * @param park
	 */
	public User(String firstName, String lastName,String id,String password ,String workerNumber,String email,String role, String park) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setPassword(password);
		this.setID(id);
		this.setWorkerNumber(workerNumber);
		this.setEmail(email);
		this.setRole(role);
		this.setParkName(park);
	}
		 
	/**
	 * Constructs a new User with only identification and password.
	 * @param id
	 * @param password
	 */
	public User (String id,String password) {
		this.userName = id;
		this.password = password;
	}
	
	 /**
	 * @return The first name of the user.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return The last name of the user.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return The user name of the user to login into the system.
	 */
	public String getUsername() {
		return userName;
	}

	/**
	 * @return The number of the worker.
	 */
	public String getWorkerNumber() {
		return workerNumber;
	}

	/**
	 * @return The email for the user.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return The specific role for the user.
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets the first name of the user.
	 * @param firstName 
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Sets the last name of the user.
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Sets the user name of the user.
	 * @param iD
	 */
	public void setID(String iD) {
		userName = iD;
	}

	/**
	 * Sets the number of the worker.
	 * @param workerNumber
	 */
	public void setWorkerNumber(String workerNumber) {
		this.workerNumber = workerNumber;
	}

	/**
	 * Sets the email for the user.
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Sets the specific role for the user.
	 * @param role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the password for the user.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the password for the user.
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return True if user is found in DB false otherwise.
	 */
	public boolean isFoundInDB() {
		return foundInDB;
	}

	/**
	 * Sets true if user is found in DB false otherwise.
	 * @param foundInDB
	 */
	public void setFoundInDB(boolean foundInDB) {
		this.foundInDB = foundInDB;
	}

	/**
	 * @return The specific park that the user is working in.
	 */
	public Park getPark() {
		return park;
	}

	/**
	 * Sets the specific park that the user is working in.
	 * @param park 
	 */
	public void setPark(Park park) {
		this.park = park;
	}

	/**
	 * @return The park name that the user is working in.
	 */
	public String getParkName() {
		return parkName;
	}

	/**
	 * Sets the park name that the user is working in.
	 * @param parkName
	 */
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
}
