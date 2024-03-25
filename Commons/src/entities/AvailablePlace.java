package entities;

import java.io.Serializable;

/**
 * Represents an available place within a park for a specific date and time.
 * This class is used to manage and track the availability of places in a park
 * scheduling system. It implements Serializable to allow instances of this class
 * to be easily sent over the client and the server.
 */
public class AvailablePlace implements Serializable{
	private static final long serialVersionUID = 1L;
	private String parkname; // The name of the park.
	private String date; // The date of the available place.
	private String time; // The time of the available place.

	/**
	 * Constructs a new AvailablePlace with the specified park name, date, and time.
	 * 
	 * @param parkname The name of the park.
	 * @param date The date of the available place.
	 * @param time The time of the available place.
	 */
	public AvailablePlace(String parkname, String date, String time) {
		this.parkname=parkname;
		this.time=time;
		this.date=date;
	}
		
	/**
	 * @return The name of the park.
	 */
	public String getParkName() {
		return parkname;
	}
	
	/**
	 * @return The time of the available place.
	 */
	public String getTime() {
		return time;
	}
	 
	/**
	 * @return The date of the available place.
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * @returns A string representation of this AvailablePlace.
	 */
	@Override
	public String toString() {
		return parkname + " " + date + " " + time;
	}

}
