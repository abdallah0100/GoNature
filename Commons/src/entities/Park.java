package entities;

public class Park {

	private String parkname;
	public static int maxNumber;
	public static int availableNumber  ;
	public static int estimatedTime=4;
	public static int price=40;



	public Park(String parkname) {
		this.parkname=parkname;
	
	}
	
	public String getnamePark() {
		return parkname;
	}
	 
}
