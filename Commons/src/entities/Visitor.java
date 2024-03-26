package entities;

import java.io.Serializable;

/**
 * Represents a visitor within a park system.
 * The class tracks visitor information including identification, contact details, and roles.
 */
public class Visitor implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id; // The id of the visitor , he login into the system using his id.
	private String email; // The email of the visitor.
	private String phone; // The phone number of the visitor so the system can sent messages to the visitor.
	private String name; // The name of the visitor.
	private boolean isInstructor; // Flag indicating whether the visitor is an instructor.
	private boolean foundInDB; // Flag indicating whether the visitor is in the DB.
	
	/**
	 * Constructs a new Visitor with specified ID, email, phone number, and instructor status.
	 * @param id
	 * @param email
	 * @param phone
	 * @param instructor
	 */
	public Visitor(String id, String email, String phone, boolean instructor) {
		this.setId(id);
		this.setEmail(email);
		this.setPhone(phone);
		this.setInstructor(instructor);
	}
	
	/**
	 * Constructs a new Visitor with specified ID, email, phone number, and name.
	 * @param id
	 * @param email
	 * @param phone
	 * @param name
	 */
	public Visitor(String id, String email, String phone, String name) {
		this.setId(id);
		this.setName(name);
		this.setEmail(email);
		this.setPhone(phone);

	}
	/**
	 * @return The name of the visitor.
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the name of the visitor.
	 * @param Name
	 */
	public void setName(String Name) {
		this.name = Name;
	}

	/**
	 * Sets the id of the visitor.
	 * And sets if found in DB.
	 * @param id 
	 */
	public Visitor (String id) {
		this.id = id;
		this.foundInDB = false;
	}

	/**
	 * @return the id of the visitor.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id of the visitor.
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return The email of the visitor.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email of the visitor.
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return The phone number of the visitor so the system can sent messages to the visitor.
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the phone number of the visitor so the system can sent messages to the visitor.
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return True if the visitor is an instructor false otherwise.
	 */
	public boolean isInstructor() {
		return isInstructor;
	}

	/**
	 * Sets true if the visitor is an instructor false otherwise.
	 * @param isInstructor
	 */
	public void setInstructor(boolean isInstructor) {
		this.isInstructor = isInstructor;
	}

	/**
	 * @return True if the visitor is in DB false otherwise.
	 */
	public boolean isFoundInDB() {
		return foundInDB;
	}

	/**
	 * Sets true if the visitor is found in DB false otherwise.
	 * @param foundInDB
	 */
	public void setFoundInDB(boolean foundInDB) {
		this.foundInDB = foundInDB;
	}
}
