package entities;

import java.io.Serializable;

public class CancelledReservation implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String type;
	private String date;
	private String park;
	
	public CancelledReservation(String type, String date, String park) {
		this.type = type;
		this.date = date;
		this.park = park;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPark() {
		return park;
	}

	public void setPark(String park) {
		this.park =park;
	}
}
