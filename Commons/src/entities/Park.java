package entities;

import java.io.Serializable;

/**
 * Represents a park. This class contains information about a park, including its name, 
 * operational parameters like gap (allowed visits without a reservation), estimated 
 * visit time, and capacity,s well as the current number of visitors with and without a reservation.
 */
public class Park implements Serializable{

	private static final long serialVersionUID = 1L;
	private String parkname; // The name of the park.
	private int gap; // The allowed walk-in visits.
	private int estimatedTime=4;  // Default estimated visit time in hours.
	private int maxCapacity;  // The maximum capacity of the park.
	private int visitorsWithOrder; // The number of visitors with a reservation.
	private int visitorsWithoutOrder; // The number of walk-in visitors.
	private int availablePlaces=0 ; // Available places for new visitors.
	public static int DEFAULT_PRICE=50; // Default entry price.
	private String varbToUpdate; // The variable to be updated in the park settings.
	private int newValue; // The new value for the variable to be updated.
	private boolean updated; // Flag to indicate if the park's settings have been updated.
 
	/**
	 * Constructor for creating a park with only its name.
	 * @param parkname The name of the park.
	 */
	public Park(String parkname) {
		this.parkname=parkname;
	}

	/**
	 * Constructor for creating a park with basic settings.
	 * @param parkname The name of the park.
	 * @param gap The allowed walk-in visits.
	 * @param estTime Estimated visit time in hours.
	 * @param capacity Maximum capacity of the park.
	 */
	public Park(String parkname, int gap, int estTime, int capacity) {
		this.parkname = parkname;
		this.gap = gap;
		this.estimatedTime = estTime;
		this.maxCapacity = capacity;
	}
	
	/**
	 * Constructor for creating a park with detailed settings including current visitor counts.
	 * @param parkname The name of the park.
	 * @param gap The allowed walk-in visits.
	 * @param estTime Estimated visit time in hours.
	 * @param capacity Maximum capacity of the park.
	 * @param visitorsWithOrder The number of visitors with a reservation.
	 * @param visitorsWithoutOrder The number of walk-in visitors.
	 */
	public Park(String parkname, int gap, int estTime, int capacity, int visitorsWithOrder, int visitorsWithoutOrder) {
		this.parkname = parkname;
		this.gap = gap;
		this.estimatedTime = estTime;
		this.maxCapacity = capacity;
		this.visitorsWithOrder = visitorsWithOrder;
		this.visitorsWithoutOrder = visitorsWithoutOrder;
	}
	
	/**
	 * @return The name of the park.
	 */
	public String getParkName() {
		return parkname;
	}

	/**
	 * @return The allowed walk-in visits.
	 */
	public int getGap() {
		return gap;
	}

	/**
	 * Sets the allowed walk-in visitors.
	 * @param gap
	 */
	public void setGap(int gap) {
		this.gap = gap;
	}

	/**
	 * @return The maximum capacity of the park.
	 */
	public int getMaxCapacity() {
		return maxCapacity;
	}

	/**
	 * Sets the maximum capacity of the park.
	 * @param maxCapacity 
	 */
	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	/**
	 * @return estimated visit time in hours.
	 */
	public int getEstimatedTime() {
		return estimatedTime;
	}

	/**
	 * Sets the estimated visit time in hours.
	 * @param estimatedTime
	 */
	public void setEstimatedTime(int estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	/**
	 * @return Available places for new visitors.
	 */
	public int getAvailablePlaces() {
		return availablePlaces;
	}

	/**
	 * Sets the available places for new visitors.
	 * @param availablePlaces
	 */
	public void setAvailablePlaces(int availablePlaces) {
		this.availablePlaces = availablePlaces;
	}

	/**
	 * @return The variable to be updated in the park settings.
	 */
	public String getVarbToUpdate() {
		return varbToUpdate;
	}

	/**
	 * Sets the variable to be updated in the park settings.
	 * @param varbToUpdate
	 */
	public void setVarbToUpdate(String varbToUpdate) {
		this.varbToUpdate = varbToUpdate;
	}

	/**
	 * @return The new value for the variable to be updated.
	 */
	public int getNewValue() {
		return newValue;
	}

	/**
	 * Sets the new value for the variable to be updated.
	 * @param newValue
	 */
	public void setNewValue(int newValue) {
		this.newValue = newValue;
	}

	/**
	 * @return True if park's settings have been updated false otherwise.
	 */
	public boolean isUpdated() {
		return updated;
	}

	/**
	 * Sets True or false if park's settings have been updated.
	 * @param updated
	 */
	public void setUpdated(boolean updated) {
		this.updated = updated;
	}

	/**
	 * @return The number of visitors with a reservation.
	 */
	public int getVisitorsWithOrder() {
		return visitorsWithOrder;
	}

	/**
	 * @return The number of walk-in visitors.
	 */
	public int getVisitorsWithoutOrder() {
		return visitorsWithoutOrder;
	}

}
