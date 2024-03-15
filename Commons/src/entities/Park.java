package entities;

public class Park {

	private String parkname;
	public static int maxNumber=120;
	public static int availableNumber=0 ;
	public static int estimatedTime=4;
	public static int price=40;



	public Park(String parkname) {
		this.parkname=parkname;
	
	}
	
	public String getNamePark() {
		return parkname;
	}
	public int getPrice() {
		return price;
	}
}
