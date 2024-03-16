package entities;

import java.io.Serializable;

public class Bill implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String type1;
	private String numberOfVisitor;
	private boolean isInstructor;
	private boolean invited;
	private boolean payed;
	private	String requestedBill;
	
	public Bill(String type1,String numberOfVisitor,boolean invisted,boolean payed) {
		this.numberOfVisitor=numberOfVisitor;
		this.type1=type1;
		this.invited=invisted;
		this.payed=payed;
	} 
	 public void print() {
		 System.out.println(getType());
		 System.out.println(getNumberOfVisitor());
		 System.out.println(getinvited());
		 System.out.println(getPayed());
	 }
	
	public Bill(String id) {
		this.id=id;
	}

	 public String getNumberOfVisitor() {
		return numberOfVisitor;
	}
	 
	 public String getType() {
		return type1;
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
	
	public void setRequestedBill(String requestedBill) {
		this.requestedBill = requestedBill;
	} 
	
	public double returnPrice() {
		//1 מראש  ביקור אישי 
		if(getType().equals("Private")) 
		{
			
			if(getinvited() &&getPayed()) {
				int numberOfVisitors = Integer.parseInt(getNumberOfVisitor());
				double x =  (double) (numberOfVisitors* 0.85*Park.price);
				return x;
			}
			if(getinvited() && !(getPayed())) {
				int numberOfVisitors = Integer.parseInt(getNumberOfVisitor());
				return (numberOfVisitors*Park.price);
				}
			if(!(getinvited())) {
				int numberOfVisitors = Integer.parseInt(getNumberOfVisitor());
				return (numberOfVisitors*Park.price);
				}
		}
		else {
			
			if(getinvited() &&getPayed()) {
				//ביקור קבוצתי בהזמנה
				int numberOfVisitors = Integer.parseInt(getNumberOfVisitor());
				numberOfVisitors--;
				double x =  (double) (numberOfVisitors* 0.75*Park.price);
				x=(double) (x*0.88);
				return x;
			}
			if(getinvited() && !(getPayed())) {
				int numberOfVisitors = Integer.parseInt(getNumberOfVisitor());
				double x =  (double) (numberOfVisitors* 0.75*Park.price);
				return x;
			} 
			
			if(!(getinvited())) {
				int numberOfVisitors = Integer.parseInt(getNumberOfVisitor());
				double x =  (double) (numberOfVisitors* 0.90*Park.price);
				return x;
			}
			
		}
		return 0;	
	}


}