package entities;

import java.io.Serializable;

/**
 * Represents a cancelled reservation within a park . This class stores information
 * about a reservation that has been cancelled, including the type of reservation,
 * the date of the intended visit, and the park where the reservation was made.
 */
public class CancelledReservation implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String type; // The type of reservation (e.g., "Private", "Organized Group").
	private String date; // The date of the cancelled reservation.
	private String park; // The name of the park where the reservation was originally made.
	
	/**
	 * Constructs a CancelledReservation object with specified reservation type, date, and park.
	 * 
	 * @param type The type of reservation.
	 * @param date The date of the intended visit.
	 * @param park The park where the reservation was made.
	 */
	public CancelledReservation(String type, String date, String park) {
		this.type = type;
		this.date = date;
		this.park = park;
	}

	/**
	 * @return The type of reservation (e.g., "Private", "Organized Group").
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type of the cancelled reservation.
	 * @param type 
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return The date of the intended visit.
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date of the cancelled reservation.
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return The park where the reservation was made.
	 */
	public String getPark() {
		return park;
	}

	/**
	 * Sets the park of the cancelled reservation.
	 * @param park
	 */
	public void setPark(String park) {
		this.park =park;
	}
}
