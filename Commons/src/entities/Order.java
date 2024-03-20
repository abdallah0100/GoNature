package entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String orderType;
	private String numOfVisitors;
	private String date;
	private String hour;
	private String minute;
	private String parkName;
	private String phone;
	private String email;
	private String orderID;
	private String time;
	
	public Order() {
    }
	
	public Order(String orderType, String numOfVisitors, String date, String hour, String minute
			, String parkName , String phone, String email ,String orderID) {
		this.orderType = orderType;
		this.numOfVisitors = numOfVisitors;
		this.date = date;
		this.hour = hour;
		this.minute = minute;
		this.parkName =parkName;
		this.phone = phone;
		this.email = email;
		this.orderID=orderID;
				 
	}
	public Order(String parkName, String date, String hour,String minute,int numOfVisitors,String orderType) {
		this.parkName =parkName;
		this.date = date;
		this.time = hour+":"+minute;
		this.numOfVisitors =  String.valueOf(numOfVisitors);
		this.orderType = orderType;


	}
	
    public String getOrderType() {
		return orderType;
	}  
	public int getNumOfVisitors() {
		return Integer.parseInt(numOfVisitors);
	}
	public String getDate() {
		return date;
	}
	public String getHour() {
		return hour;
	}
	public String getMinute() {
		return minute;
	}
	public String getParkName() {
		return parkName;
	}
	public String getPhone() {
		return phone;
	}
	public String getEmail() {
		return email;
	}
	public String getOrderID() {
		return orderID;
	}
	public String getTime() {
		return time;
	}
	
	
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public void setNumOfVisitors(String numOfVisitors) {
		this.numOfVisitors = numOfVisitors;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public void setMinute(String minute) {
		this.minute = minute;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	public void setphone(String phone) {
		this.phone = phone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	
	public ArrayList<String> getArray(){
		ArrayList<String> list = new ArrayList<>();
		list.add(orderType);
		list.add(numOfVisitors);
		list.add(date);
		list.add(hour);
		list.add(minute);
		list.add(parkName);
		list.add(phone);
		list.add(email);
		list.add(orderID);
		return list;
	}
}
