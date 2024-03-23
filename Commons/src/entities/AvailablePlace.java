package entities;

import java.io.Serializable;

public class AvailablePlace implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String parkname;
	private String date;
	private String time;

	public AvailablePlace(String parkname, String date, String time) {
		this.parkname=parkname;
		this.time=time;
		this.date=date;
	
	}
		
	public String getParkName() {
		return parkname;
	}
	
	public String getTime() {
		return time;
	}
	 
	public String getDate() {
		return date;
	}
	
	@Override
	public String toString() {
		return parkname + " " + date + " " + time;
	}

}
