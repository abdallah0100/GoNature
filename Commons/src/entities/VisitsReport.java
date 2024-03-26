package entities;

import java.io.Serializable;

/**
 * The VisitsReport class represents a report of visitor number in a park.
 * This report is primarily done by park managers to track the number of visitors based on
 * the type of reservation they have. It includes information such as the time of entry and
 * exit, as well as the reservation type.
 * 
 * Each instance of VisitsReport captures the detailed entry and exit timings of a single 
 * visit, along with the associated reservation type. This data can be used for generating
 * statistical reports on park usage and visitor management and shown to department managers.
 */
public class VisitsReport implements Serializable{
	private static final long serialVersionUID = 1L;
	private int enterHour; // The hour of entry.
	private int enterMin; // The minute of entry.
	private int exitHour; // The hour of exit.
	private int exitMin; // The minute of exit.
	private String reservationType; // The type of reservation (e.g., "Private", "Organized Group").
	
	/**
	 * Constructs a new VisitsReport with specified entry and exit times
	 * along with the reservation type.
	 * 
	 * @param enterHour The hour of entry.
	 * @param enterMin The minute of entry.
	 * @param exitHour The hour of exit.
	 * @param exitMin The minute of exit.
	 * @param reservationType The type of reservation (e.g., "Private", "Organized Group").
     */
	public VisitsReport(int enterHour, int enterMin, int exitHour, int exitMin, String reservationType) {
		this.enterHour = enterHour;
		this.enterMin = enterMin;
		this.exitHour = exitHour;
		this.exitMin = exitMin;
		this.reservationType = reservationType;
	}
	
	/**
	 * @return The hour of entry.
	 */
	public int getEnterHour() {
		return enterHour;
	}
	
	/**
	 * Sets the hour of entry.
	 * @param enterHour
	 */
	public void setEnterHour(int enterHour) {
		this.enterHour = enterHour;
	}
	
	/**
	 * @return The minute of entry.
	 */
	public int getEnterMin() {
		return enterMin;
	}
	
	/**
	 * Sets the minute of entry.
	 * @param enterMin 
	 */
	public void setEnterMin(int enterMin) {
		this.enterMin = enterMin;
	}
	
	/**
	 * @return The hour of exit.
	 */
	public int getExitHour() {
		return exitHour;
	}
	
	/**
	 * Sets the hour of exit.
	 * @param exitHour
	 */
	public void setExitHour(int exitHour) {
		this.exitHour = exitHour;
	}
	
	/**
	 * @return The minute of exit.
	 */
	public int getExitMin() {
		return exitMin;
	}
	
	/**
	 * Sets the minute of exit.
	 * @param exitMin
	 */
	public void setExitMin(int exitMin) {
		this.exitMin = exitMin;
	}
	
	/**
	 * @return The type of reservation (e.g., "Private", "Organized Group").
	 */
	public String getReservationType() {
		return reservationType;
	}
	
	/**
	 * Sets the type of reservation (e.g., "Private", "Organized Group").
	 * @param reservationType
	 */
	public void setReservationType(String reservationType) {
		this.reservationType = reservationType;
	}
}