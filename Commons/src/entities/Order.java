package entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents an order placed by a visitor for park entry or services.
 * Orders include details such as order type, visitor count, date and time, park name, and contact information.
 * Orders can also track payment status, confirmation, and cancellation states.
 */
public class Order implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String orderType; // The type of the reservation.
	private String numOfVisitors; // The number of the visitors for a reservation.
	private String date; // The date of the reservation.
	private String hour; // The hour of the reservation.
	private String minute; // The minute of the reservation.
	private String parkName; // The park name of the reservation.
	private String phone; // The phone for the visitor who made this reservation.
	private String email; // The email for the visitor who made this reservation.
	private String visitorID; //The visitor ID for the visitor who made this reservation.
	private boolean isConfirmed; // Flag to save if the reservation if confirmed or not.
	private boolean invitedInAdvance; // Flag to save if the reservation was reserved in advanced.
	private boolean isPayed; // Flag to save if the visitor payed in advanced.
	private boolean canceled; //Flag to save if the reservation was cancelled.
	private boolean cancelRequest; //Flag to save if there is a cancel request to reservation.
	private String processed; // Flag to save if reservation has done processing and now is in DB.
	
	private boolean sentMsg; // Flag to save if a message has been sent.
	private int msgHour;
	private int msgMinute;
	
	private String orderID; // The order id of the reservation.
	private String time; //The full time of the reservation hours & minutes.
	private String messageTitle;
	
	/**
	 *  Default constructor
	 */
	public Order() {
    }
	
	/**
	 * Constructs an order with the details saved in the DB for every order.
	 * 
	 * @param orderType
	 * @param numberOfVisitors
	 * @param date
	 * @param hour
	 * @param minute
	 * @param parkName
	 * @param phone
	 * @param email
	 * @param reservationID
	 * @param isConfirmed
	 * @param invitedInAdvance
	 * @param isPayed
	 */
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
	/**
	 * Constructs an order with the wanted details.
	 * @param parkName
	 * @param date
	 * @param hour
	 * @param minute
	 * @param numOfVisitors
	 * @param orderType
	 */
	public Order(String parkName, String date, String hour,String minute,int numOfVisitors,String orderType) {
		this.parkName =parkName;
		this.date = date;
		this.hour = hour;
		this.minute = minute;
		this.time = hour+":"+minute;
		this.numOfVisitors =  String.valueOf(numOfVisitors);
		this.orderType = orderType;
	}
	
	/**
	 * 
	 * @param numberOfVisitors
	 * @param parkName
	 * @param orderID
	 */
	public Order(int numberOfVisitors, String parkName,String orderID) {
		this.parkName =parkName;
		this.numOfVisitors = String.valueOf(numberOfVisitors);
		this.orderID=String.valueOf(orderID);
	}
	

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

	public String getMessageTitle() {
		return messageTitle; 
	}
 
	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}
	

	public String isProcessed() {
		return processed;
	}

	public void setProcessed(String processed) {
		this.processed = processed;
	}
	
	public int getYear() {
		String[] dateSplitted = getDate().split("-");
		int orderYear = Integer.parseInt(dateSplitted[0]);
		return orderYear;
	}
	
	public int getMonth() {
		String[] dateSplitted = getDate().split("-");
		int orderYear = Integer.parseInt(dateSplitted[1]);
		return orderYear;
	}
	
	public int getDay() {
		String[] dateSplitted = getDate().split("-");
		int orderYear = Integer.parseInt(dateSplitted[2]);
		return orderYear;
	}
	
	public boolean overlappingOrders(Order o, int estimatedTime) {
		if (o == null)
			return false;
		if (getYear() != o.getYear() || getMonth() != o.getMonth() || getDay() != o.getDay())
			return false;
		try {
			int hour = Integer.parseInt(this.hour);
			int minute = Integer.parseInt(this.minute);
				
			int otherHour = Integer.parseInt(o.getHour());
			int otherMinute = Integer.parseInt(o.getMinute());
	
			
			if (hour - otherHour == estimatedTime)// e.g: hour = 18:30, otherHour = 14: 29
				return !(minute >= otherMinute);
			else if (otherHour - hour == estimatedTime)// e.g: hour = 14:30, otherHour = 18: 29
				return !(otherMinute >= minute);
			else if (hour - otherHour > estimatedTime)//current hour is more than 4 hours ahead of other 18:mm 13:mm
				return false;
			else if (otherHour - hour > estimatedTime)//current hour is more than 4 hours behind of other
				return false;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return true;
	}
}
