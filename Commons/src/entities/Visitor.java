package entities;

public class Visitor {
	private String id;
	private String email;
	private String phone;
	private boolean isInstructor;
	
	public Visitor(String id, String email, String phone, boolean instructor) {
		this.setId(id);
		this.setEmail(email);
		this.setPhone(phone);
		this.setInstructor(instructor);
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
}
