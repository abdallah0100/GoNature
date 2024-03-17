package entities;

import java.io.Serializable;

public class Park implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String parkname;
	private int gap;
	private int estimatedTime=4;
	private int maxCapacity;
	
	private int availablePlaces=0 ;
	public static int DEFAULT_PRICE=50;
	
	private String varbToUpdate;
	private int newValue;
	private boolean updated;
 
 

	public Park(String parkname, int gap, int estTime, int capacity) {
		this.parkname = parkname;
		this.gap = gap;
		this.estimatedTime = estTime;
		this.maxCapacity = capacity;
	}
	
	public String getParkName() {
		return parkname;
	}

	public int getGap() {
		return gap;
	}

	public void setGap(int gap) {
		this.gap = gap;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public int getEstimatedTime() {
		return estimatedTime;
	}

	public void setEstimatedTime(int estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	public int getAvailablePlaces() {
		return availablePlaces;
	}

	public void setAvailablePlaces(int availablePlaces) {
		this.availablePlaces = availablePlaces;
	}

	public String getVarbToUpdate() {
		return varbToUpdate;
	}

	public void setVarbToUpdate(String varbToUpdate) {
		this.varbToUpdate = varbToUpdate;
	}

	public int getNewValue() {
		return newValue;
	}

	public void setNewValue(int newValue) {
		this.newValue = newValue;
	}

	public boolean isUpdated() {
		return updated;
	}

	public void setUpdated(boolean updated) {
		this.updated = updated;
	}

}
