package main.controllers;

import entities.Bill;
import entities.Order;
import entities.Report;
import entities.User;
import main.ClientController;
import requests.Message;
import requests.RequestType;

/**
 * Handles user-related requests such as login, bill viewing, instructor registration,
 * report fetching, logout, and other user-specific actions with the server.
 */
public class UserRequestController {
	public static boolean LogedIn = false;
 
	/**
	 * Initiates a user login request with the given password and user name.
	 * @param userName The user's user name.
	 * @param password The user's password.
	 */
	public static void sendUserLogIn(String userName,String password) {
		ClientController.getController().accept(new Message(RequestType.LOGIN_USER, new User(userName,password)));
	} 
	
	/**
	 * Requests bill details for a given reservation ID.
	 * @param id The reservation ID for which the bill is requested. 
	 */
	public static void sendShowBill(String id) {
		ClientController.getController().accept(new Message(RequestType.REQUEST_BILL,new Bill(id)));
	} 
	
	
	/**
	 * Registers an instructor.
	 * @param id The unique id of the instructor.
	 */
	public static void registInstructor(String id) {
		ClientController.getController().accept(new Message(RequestType.REGIST_INSTRUCTOR,id));
	}
	
	/**
	 * Fetches report data for a given park, month, year, and report type.
	 * 
	 * @param parkName The name of the park.
	 * @param month The month for the report.
	 * @param year The year for the report. 
	 * @param type The type of report.
	 */
	public static void fetchReportData(String parkName, String month, String year, String type) {
		Report r = new Report(parkName, month, year, ClientController.connectedUser.getUsername(), type);
		if (type.contains("Number")) {
			r.setNotFullParkReport(false);
			ClientController.getController().accept(new Message(RequestType.FETCH_RESERVATION_DATA, r));
		}
		else {
			r.setNotFullParkReport(true);
			ClientController.getController().accept(new Message(RequestType.FETCH_NOT_FULL_DATA, r));
			return;
		}  
	} 
	
	/**
	 * Sends a request to create a report based on the provided Report object.
	 * @param r The Report object containing the details for the report to be created.
	 */
	public static void createReport(Report r) {
		ClientController.getController().accept(new Message(RequestType.CREATE_REPORT, r));
	}
	
	/**
	 * Sends a request to exit a visitor from the park.
	 * @param s An array containing the park name and reservation ID.
	 */
	public static void exit(String s[]) {
		ClientController.getController().accept(new Message(RequestType.EXIT_VISITOR,s));
	} 
	/**
	 * Sends a request to enter a visitor into the park.
	 * @param s  An array containing the park name and reservation ID.
	 */
	public static void enter(String[]s) {
		ClientController.getController().accept(new Message(RequestType.ENTER_VISTOR,s));
	} 

	/**
	 * Initiates a logout request for the currently logged-in user.
	 * @param toLogout The username of the user to logout.
	 */
	public static void sendLogoutRequest(String toLogout) {
		ClientController.getController().accept(new Message(RequestType.LOGOUT, toLogout));
	}
	
	/**
	 * Requests the latest order ID for a given park to manage walk-in visitors.
	 * @param parkName The name of the park.
	 */
	public static void getOrderId(String parkName) {
		ClientController.getController().accept(new Message(RequestType.ORDER_ID, parkName));
	}
	/**
	 * Processes a walk-in visitor's entry into the park with the given Order details.
	 * @param o The Order object for the walk-in visitor.
	 */
	public static void walkInvist(Order o) {
		ClientController.getController().accept(new Message(RequestType.MAKE_RESERVATION_ENTRY, o));
	}
	
	/**
	 * Checks if the given ID belongs to a registered instructor.
	 * @param id The unique id of the instructor to check.
	 */
	public static void checkInstructor(String id) {
		ClientController.getController().accept(new Message(RequestType.CHECK_INSTRUCTOR, id));
	}
}
