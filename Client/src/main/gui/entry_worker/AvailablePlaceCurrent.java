package main.gui.entry_worker;

import javafx.application.Application;
import javafx.stage.Stage;

public class AvailablePlaceCurrent  {

	private String parkname;
	private String date;
	private String time;
	

	public AvailablePlaceCurrent(String parkname,String time,String date) {
		this.parkname=parkname;
		this.time=time;
		this.date=date;
	
	}
	
	
	public String getnamePark() {
		return parkname;
	}
	
	public String gettime() {
		return time;
	}
	 
	public String getdate() {
		return date;
	}


}
