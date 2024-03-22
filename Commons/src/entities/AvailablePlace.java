package entities;

public class AvailablePlace{

	private String parkname;
	private String date;
	private String time;

	public AvailablePlace(String parkname,String date,String time) {
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
	
	@Override
	public String toString() {
		return parkname + " " + date + " " + time;
	}

}
