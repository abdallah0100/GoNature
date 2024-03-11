package entities;

public class AvailablePlace  {

	private String parkname;
	private String date;
	private String time;
	

	public AvailablePlace(String parkname,String time,String date) {
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
