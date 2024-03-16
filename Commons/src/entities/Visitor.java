package entities;

import java.io.Serializable;

public class Visitor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String email;
	private String phone;
	private String name;
	private boolean isInstructor;
	
	private boolean foundInDB;
	
	public Visitor(String id, String email, String phone, boolean instructor) {
		this.setId(id);
		this.setEmail(email);
		this.setPhone(phone);
		this.setInstructor(instructor);
	}
	
	public Visitor(String id, String email, String phone) {
		this.setId(id);
		this.setEmail(email);
		this.setPhone(phone);

	}
	public String getName() {
		return name;
	}
	public void setName(String Name) {
		this.name = Name;
	}

	public Visitor (String id) {
		this.id = id;
		this.foundInDB = false;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isInstructor() {
		return isInstructor;
	}

	public void setInstructor(boolean isInstructor) {
		this.isInstructor = isInstructor;
	}

	public boolean isFoundInDB() {
		return foundInDB;
	}

	public void setFoundInDB(boolean foundInDB) {
		this.foundInDB = foundInDB;
	}
}
