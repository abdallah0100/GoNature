package entities;

import java.io.Serializable;

public class VisitsReport implements Serializable{
	private static final long serialVersionUID = 1L;
	private int enterHour;
	private int enterMin;
	private int exitHour;
	private int exitMin;
	private String reservationType;
	
	public VisitsReport(int enterHour, int enterMin, int exitHour, int exitMin, String reservationType) {
		this.enterHour = enterHour;
		this.enterMin = enterMin;
		this.exitHour = exitHour;
		this.exitMin = exitMin;
		this.reservationType = reservationType;
	}
	
	public int getEnterHour() {
		return enterHour;
	}
	
	public void setEnterHour(int enterHour) {
		this.enterHour = enterHour;
	}
	
	public int getEnterMin() {
		return enterMin;
	}
	
	public void setEnterMin(int enterMin) {
		this.enterMin = enterMin;
	}
	
	public int getExitHour() {
		return exitHour;
	}
	
	public void setExitHour(int exitHour) {
		this.exitHour = exitHour;
	}
	
	public int getExitMin() {
		return exitMin;
	}
	
	public void setExitMin(int exitMin) {
		this.exitMin = exitMin;
	}
	
	public String getReservationType() {
		return reservationType;
	}
	
	public void setReservationType(String reservationType) {
		this.reservationType = reservationType;
	}
}