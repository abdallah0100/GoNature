package entities;

public class Bill {
	
	private String id;
	private String numberOfVisitor;
	private boolean isInstructor;
	private boolean invited;
	private boolean payed;
	
	public Bill(String numberOfVisitor,boolean isInstructor,boolean invisted,boolean payed) {
		this.numberOfVisitor=numberOfVisitor;
		this.isInstructor=isInstructor;
		this.invited=invisted;
		this.payed=payed;
	}
	
	public Bill(String id) {
		this.id=id;
	}

	 public String getNumberOfVisitor() {
		return numberOfVisitor;
	}
	 
	 public String getId() {
		return id;
	}
	 
	 public boolean getinvited() {
		return invited;
	}
	 
	 public boolean getPayed() {
		return payed;
	}
	 public boolean getIsInstructor() {
		return isInstructor;
	}
	public void setNumberOfVisitor(String numberOfVisitor) {
		this.numberOfVisitor = numberOfVisitor;
	}


}