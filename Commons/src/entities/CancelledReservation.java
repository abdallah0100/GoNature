package entities;

import javafx.beans.property.SimpleStringProperty;

public class CancelledReservation {
	
	private SimpleStringProperty type;
	private SimpleStringProperty date;
	private SimpleStringProperty park;
	
	public CancelledReservation(String type, String date, String park) {
		this.type = new SimpleStringProperty(type);
		this.date = new SimpleStringProperty(date);
		this.park = new SimpleStringProperty(park);
	}

	public String getType() {
		return type.get();
	}

	public void setType(String type) {
		this.type = new SimpleStringProperty(type);
	}

	public String getDate() {
		return date.get();
	}

	public void setDate(String date) {
		this.date = new SimpleStringProperty(date);
	}

	public String getPark() {
		return park.get();
	}

	public void setPark(String park) {
		this.park = new SimpleStringProperty(park);
	}
}
