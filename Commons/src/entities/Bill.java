package entities;

import java.io.Serializable;

/**
 * Represents a bill for visitors in a park, capturing details like visitor type, number, and payment status.
 * It supports calculating the total price based on various conditions.
 */
public class Bill implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id; // Unique identifier for the bill.
	private String type1; // The type of reservation (e.g., "Private", "Organized Group").
	private String numberOfVisitor; // Number of visitors associated with this bill.
	private boolean isInstructor; // Indicates if an instructor is involved in the visit.
	private boolean invited; // Indicates if the visit is by reservation.
	private boolean payed; // Indicates if the bill has been paid
	
	/**
	 * Constructs a Bill with visit details but without a unique identifier.
	 * 
	 * @param type1 Type of the reservation.
	 * @param numberOfVisitor Number of visitors.
	 * @param invited Indicates if the visit reserved before hand.
	 * @param payed Indicates if the bill has been paid.
	 */
	public Bill(String type1,String numberOfVisitor,boolean invited,boolean payed) {
		this.numberOfVisitor=numberOfVisitor;
		this.type1=type1;
		this.invited=invited;
		this.payed=payed;
	} 
	
	/**
	 * Constructs a Bill with a unique identifier.
	 * @param id The unique identifier for the bill.
	 */
	public Bill(String id) {
		this.id=id;
	}

	 /**
	 * @return Number of visitors.
	 */
	public String getNumberOfVisitor() {
		return numberOfVisitor;
	}
	 
	 /**
	 * @return Type of the reservation.
	 */
	public String getType() {
		return type1;
	}
	 
	 /**
	 * @return The id of the visitor.
	 */
	public String getId() {
		return id;
	}
	 
	 /**
	 * @return True if invited false otherwise.
	 */
	public boolean getinvited() {
		return invited;
	}
	 
	 /**
	 * @return True if payed false otherwise.
	 */
	public boolean getPayed() {
		return payed;
	}
	
	 /**
	 * @return True if is instructor false otherwise.
	 */
	public boolean getIsInstructor() {
		return isInstructor; 
	}

	/**
	 * Sets number of visitors.
	 * @param numberOfVisitor Number of visitors.
	 */
	public void setNumberOfVisitor(String numberOfVisitor) {
		this.numberOfVisitor = numberOfVisitor;
	} 
	
	/**
	 * Calculates and returns the price of the visit based on visit type, invitation status, and payment status.
	 * Applies different discounts and charges based on these factors.
	 * 
	 * @return The calculated price for the visit.
	 */
	public double returnPrice() {
		// Reservation type is private 
		if(getType().equals("Private")) 
		{
			if(getinvited()) {
				int numberOfVisitors = Integer.parseInt(getNumberOfVisitor());
				double x =  (double) (numberOfVisitors* 0.85*Park.DEFAULT_PRICE);
				return x;
			}else {
				int numberOfVisitors = Integer.parseInt(getNumberOfVisitor());
				return (numberOfVisitors*Park.DEFAULT_PRICE);
				}
		}
		//Reservation type is organized group 
		else {
			double price = 0.00;
			int numberOfVisitors = Integer.parseInt(getNumberOfVisitor());
			
			if (getinvited()) {
				price = (double)((numberOfVisitors - 1) * Park.DEFAULT_PRICE);
				price *= 0.75;
				if (getPayed())
					price *= 0.88;
			}else {
				price = (double)((numberOfVisitors) * Park.DEFAULT_PRICE);
				price *= 0.9;
			}
			return price;
	
		}
	}
}