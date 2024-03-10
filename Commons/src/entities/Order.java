package entities;

import java.util.ArrayList;

public class Order {

	private String parkName;
	private String orderNum;
	private String timeOfVisit;
	private String numOfVisitors;
	private String telephone;
	private String email;
	
	public Order(String parkName, String orderNum, String timeOfVisit, String numOfVisitors, String telephone
			, String email) {
		this.parkName = parkName;
		this.orderNum = orderNum;
		this.timeOfVisit = timeOfVisit;
		this.numOfVisitors = numOfVisitors;
		this.telephone = telephone;
		this.email = email;
				
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getNumOfVisitors() {
		return numOfVisitors;
	}

	public void setNumOfVisitors(String numOfVisitors) {
		this.numOfVisitors = numOfVisitors;
	}

	public String getTimeOfVisit() {
		return timeOfVisit;
	}

	public void setTimeOfVisit(String timeOfVisit) {
		this.timeOfVisit = timeOfVisit;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public ArrayList<String> getArray(){
		ArrayList<String> list = new ArrayList<>();
		list.add(parkName);
		list.add(orderNum);
		list.add(timeOfVisit);
		list.add(numOfVisitors);
		list.add(telephone);
		list.add(email);
		return list;
	}
	
}
