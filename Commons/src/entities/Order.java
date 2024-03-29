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
	private String visitorID; // The visitor ID for the visitor who made this reservation.
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
	private String time; // The full time of the reservation hours & minutes.
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
	 * Constructs an order with the wanted details.
	 * @param numberOfVisitors
	 * @param parkName
	 * @param orderID
	 */
	public Order(int numberOfVisitors, String parkName,String orderID) {
		this.parkName =parkName;
		this.numOfVisitors = String.valueOf(numberOfVisitors);
		this.orderID=String.valueOf(orderID);
	}

	/**
	 * Constructs an order with the wanted details.
	 * @param orderType
	 * @param numberOfVisitors
	 * @param date
	 * @param hour
	 * @param minute
	 * @param parkName
	 * @param phone
	 * @param email
	 * @param reservationID
	 * @param visitorID
	 * @param isConfirmed
	 * @param invitedInAdvance
	 * @param isPayed
	 * @param processed
	 */
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
	
    /**
     * @return The type of the reservation.
     */
    public String getOrderType() {
		return orderType;
	}  
    
	/**
	 * @return The number of the visitors for a reservation.
	 */
	public int getNumOfVisitors() {
		try {
			return Integer.parseInt(numOfVisitors);
		}catch(Exception ex) {
			return 0;
		}
	}
	
	/**
	 * @return The date of the reservation.
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * @return The hour of the reservation.
	 */
	public String getHour() {
		return hour;
	}
	
	/**
	 * @return The minute of the reservation.
	 */
	public String getMinute() {
		return minute;
	}
	
	/**
	 * @return The park name of the reservation.
	 */
	public String getParkName() {
		return parkName;
	}
	
	/**
	 * @return The phone for the visitor who made this reservation.
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * @return The email for the visitor who made this reservation.
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @return The order ID for the reservation.
	 */
	public String getOrderID() {
		return orderID;
	}
	
	/**
	 * @return The full time of the reservation hours & minutes.
	 */
	public String getTime() {
		return time;
	}
	
	/**
	 * @return True if visitor payed in advanced otherwise false.
	 */
	public boolean getIsPayed() {
		return isPayed;
	}
	
	/**
	 * @return True if the reservation was reserved in advanced otherwise false.
	 */
	public boolean getInvitedInAdvance() {
		return invitedInAdvance;
	}
	
	/**
	 * @return The visitor ID for the visitor who made this reservation.
	 */
	public String getVisitorID() {
		return visitorID;
	}	
	
	/**
	 * @return True if the reservation if confirmed false otherwise.
	 */
	public boolean getIsConfirmed() {
		return isConfirmed;
	}
	
	/**
	 * Sets the type of the reservation.
	 * @param orderType
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	/**
	 * Sets the number of the visitors for a reservation. 
	 * @param numOfVisitors
	 */
	public void setNumOfVisitors(String numOfVisitors) {
		this.numOfVisitors = numOfVisitors;
	}
	
	/**
	 * Sets the date of the reservation.
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * Sets the hour of the reservation.
	 * @param hour
	 */
	public void setHour(String hour) {
		this.hour = hour;
	}
	
	/**
	 * Sets the minute of the reservation.
	 * @param minute
	 */
	public void setMinute(String minute) {
		this.minute = minute;
	}
	
	/**
	 * Sets the park name of the reservation.
	 * @param parkName
	 */
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	
	/**
	 * Sets the phone for the visitor who made this reservation.
	 * @param phone
	 */
	public void setphone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * Sets the email for the visitor who made this reservation.
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Sets the order ID for the reservation.
	 * @param orderID
	 */
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	
	/**
	 * Sets True if visitor payed in advanced otherwise false.
	 * @param isPayed
	 */
	public void setIsPayed(boolean isPayed) {
		this.isPayed = isPayed;
	}
	
	/**
	 * Sets true if the reservation was reserved in advanced otherwise false.
	 * @param invitedInAdvance
	 */
	public void setInvitedInAdvance(boolean invitedInAdvance) {
		this.invitedInAdvance = invitedInAdvance;
	}	
	
	/**
	 * Sets the visitor ID for the visitor who made this reservation.
	 * @param visitorID
	 */
	public void setVisitorID(String visitorID) {
		this.visitorID = visitorID;
	}
	
	/**
	 * Sets true if the reservation if confirmed false otherwise.
	 * @param isConfirmed
	 */
	public void setIsConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}	
	
	/**
	 * Array list saves all order info.
	 * @return this array list with the order data.
	 */
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
	
	/**
	 * @return True if the reservation was cancelled false otherwise.
	 */
	public boolean isCanceled() {
		return canceled;
	}
	
	/**
	 * Sets true if the reservation was cancelled false otherwise.
	 * @param canceled
	 */
	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}
	
	/**
	 * @return message for an hour.
	 */
	public int getMsgHour() {
		return msgHour;
	}
	
	/**
	 * Sets the message for an hour.
	 * @param msgHour
	 */
	public void setMsgHour(int msgHour) {
		this.msgHour = msgHour;
	}
	
	/**
	 * @return message for an minute.
	 */
	public int getMsgMinute() {
		return msgMinute;
	}
	
	/**
	 * Sets the message for a minute.
	 * @param msgMinute
	 */
	public void setMsgMinute(int msgMinute) {
		this.msgMinute = msgMinute;
	}
	
	/**
	 * @return true if a message was sent false otherwise.
	 */
	public boolean isSentMsg() {
		return sentMsg;
	}
	
	/**
	 * Sets true if a message was sent false otherwise.
	 * @param sentMsg
	 */
	public void setSentMsg(boolean sentMsg) {
		this.sentMsg = sentMsg;
	}
	
	/**
	 * @return True if a request is canceled false otherwise.
	 */
	public boolean isCancelRequest() {
		return cancelRequest;
	}
	
	/**
	 * Sets true if a request is canceled false otherwise.
	 * @param cancelRequest
	 */
	public void setCancelRequest(boolean cancelRequest) {
		this.cancelRequest = cancelRequest;
	}
	
	/**
	 * @return message title.
	 */
	public String getMessageTitle() {
		return messageTitle; 
	}
 
	/**
	 * Sets the message title.
	 * @param messageTitle
	 */
	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	/**
	 * @return True if the reservation is processed false otherwise.
	 */
	public String isProcessed() {
		return processed;
	}

	/**
	 * Sets true if the reservation is processed false otherwise.
	 * @param processed
	 */
	public void setProcessed(String processed) {
		this.processed = processed;
	}
	
	/**
	 * @return The year of the reservation in integer.
	 */
	public int getYear() {
		String[] dateSplitted = getDate().split("-");
		int orderYear = Integer.parseInt(dateSplitted[0]);
		return orderYear;
	}
	
	/**
	 * @return The month of the reservation in integer.
	 */
	public int getMonth() {
		String[] dateSplitted = getDate().split("-");
		int orderYear = Integer.parseInt(dateSplitted[1]);
		return orderYear;
	}
	
	/**
	 * @return The the day of the reservation in integer.
	 */
	public int getDay() {
		String[] dateSplitted = getDate().split("-");
		int orderYear = Integer.parseInt(dateSplitted[2]);
		return orderYear;
	}
	
	/**
	 * Checks if this order's time overlaps with another order within a given time frame. Overlap is determined 
	 * by comparing the dates and times of both orders. If the orders are on different dates, there is no overlap.
	 * If on the same date, it checks the time difference against the estimated duration of activities.
	 * 
	 * @param o The order to compare with this one.
	 * @param estimatedTime The maximum time difference (in hours) to consider an overlap.
	 * @return true if the orders overlap within the estimated time, false otherwise.
	 */
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
