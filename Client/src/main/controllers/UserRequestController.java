package main.controllers;

import entities.Bill;
import entities.Order;
import entities.Report;
import entities.User;
import main.ClientController;
import requests.Message;
import requests.RequestType;

public class UserRequestController {
	public static boolean LogedIn = false;
 
	public static void sendUserLogIn(String userName,String password) {
		ClientController.getController().accept(new Message(RequestType.LOGIN_USER, new User(userName,password)));
	} 
	
	public static void sendShowBill(String id) {
		ClientController.getController().accept(new Message(RequestType.REQUEST_BILL,new Bill(id)));
	} 
	
	
	public static void registInstructor(String id) {
		ClientController.getController().accept(new Message(RequestType.REGIST_INSTRUCTOR,id));
	}
	
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
	
	public static void createReport(Report r) {
		ClientController.getController().accept(new Message(RequestType.CREATE_REPORT, r));
	}
	
	public static void exit(String s[]) {
		ClientController.getController().accept(new Message(RequestType.EXIT_VISITOR,s));
	} 
	public static void enter(String[]s) {
		ClientController.getController().accept(new Message(RequestType.ENTER_VISTOR,s));
	} 

	public static void sendLogoutRequest(String toLogout) {
		ClientController.getController().accept(new Message(RequestType.LOGOUT, toLogout));
	}
	
	public static void getOrderId(String parkName) {
		ClientController.getController().accept(new Message(RequestType.ORDER_ID, parkName));
	}
	public static void walkInvist(Order o) {
		ClientController.getController().accept(new Message(RequestType.MAKE_RESERVATION_ENTRY, o));
	}
	
	public static void checkInstructor(String id) {
		ClientController.getController().accept(new Message(RequestType.CHECK_INSTRUCTOR, id));
	}
}
