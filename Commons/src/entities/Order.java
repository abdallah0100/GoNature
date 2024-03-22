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
	private String visitorID;
	private boolean isConfirmed;
	private boolean invitedInAdvance;
	private boolean isPayed;
	private boolean canceled;
	private boolean cancelRequest;
	private String processed;
	
	private boolean sentMsg;
	private int msgHour;
	private int msgMinute;
	
	private String orderID;
	private String time;
	

	public Order() {
    }
	
	public Order(String orderType, int numberOfVisitors, String date, String hour, String minute
			, String parkName , String phone, String email ,int reservationID,boolean isConfirmed,boolean invitedInAdvance,boolean isPayed) {
		this.orderType = orderType;
		this.numOfVisitors = String.valueOf(numberOfVisitors);
		this.date = date;
		this.hour = hour;
		this.minute = minute;
		this.time = hour + ":" + minute;
		this.parkName =parkName;
		this.phone = phone;
		this.email = email;
		this.orderID = String.valueOf(reservationID);
		this.isConfirmed = isConfirmed;
		this.invitedInAdvance = invitedInAdvance;
		this.isPayed = isPayed;
	}
	public Order(String parkName, String date, String hour,String minute,int numOfVisitors,String orderType) {
		this.parkName =parkName;
		this.date = date;
		this.time = hour+":"+minute;
		this.numOfVisitors =  String.valueOf(numOfVisitors);
		this.orderType = orderType;


	}
	
	public Order(int numberOfVisitors, String parkName,String orderID) {
		this.parkName =parkName;
		this.numOfVisitors = String.valueOf(numberOfVisitors);
		this.orderID=String.valueOf(orderID);
	}
	
	//new 
	public Order(String orderType, int numberOfVisitors, String date, String hour, String minute
			, String parkName , String phone, String email ,int reservationID,String visitorID ,boolean isConfirmed,boolean invitedInAdvance,boolean isPayed,String processed) {
		this.orderType = orderType;
		this.numOfVisitors = String.valueOf(numberOfVisitors);
		this.date = date;
		this.hour = hour;
		this.minute = minute;
		this.time = hour + ":" + minute;
		this.parkName =parkName;
		this.phone = phone;
		this.email = email;
		this.orderID = String.valueOf(reservationID);
		this.isConfirmed = isConfirmed;
		this.invitedInAdvance = invitedInAdvance;
		this.isPayed = isPayed;
		this.visitorID=visitorID;
		this.processed=processed;
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
	public boolean getIsPayed() {
		return isPayed;
	}
	public boolean getInvitedInAdvance() {
		return invitedInAdvance;
	}
	public String getVisitorID() {
		return visitorID;
	}	
	public boolean getIsConfirmed() {
		return isConfirmed;
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
	public void setIsPayed(boolean isPayed) {
		this.isPayed = isPayed;
	}
	public void setInvitedInAdvance(boolean invitedInAdvance) {
		this.invitedInAdvance = invitedInAdvance;
	}	
	public void setVisitorID(String visitorID) {
		this.visitorID = visitorID;
	}
	public void setIsConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
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

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	public int getMsgHour() {
		return msgHour;
	}

	public void setMsgHour(int msgHour) {
		this.msgHour = msgHour;
	}

	public int getMsgMinute() {
		return msgMinute;
	}

	public void setMsgMinute(int msgMinute) {
		this.msgMinute = msgMinute;
	}

	public boolean isSentMsg() {
		return sentMsg;
	}

	public void setSentMsg(boolean sentMsg) {
		this.sentMsg = sentMsg;
	}

	public boolean isCancelRequest() {
		return cancelRequest;
	}

	public void setCancelRequest(boolean cancelRequest) {
		this.cancelRequest = cancelRequest;
	}

	public String isProcessed() {
		return processed;
	}

	public void setProcessed(String processed) {
		this.processed = processed;
	}	
}
