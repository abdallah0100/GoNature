package main.controllers;

import entities.Bill;
import entities.Report;
import entities.User;
import entities.Visitor;
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
	
	public static void insertInstructor(String instructor_id,String instructorName,String instructor_email,String instructor_tel) {
		ClientController.getController().accept(new Message(RequestType.INSERT_INSTRUCTOR,
												new Visitor(instructor_id,instructorName,instructor_email,instructor_tel)));
	}
	
	public static void fetchReportData(String parkName, String month, String year, String type) {
		Report r = new Report(parkName, month, year, ClientController.connectedUser.getUsername(), type);
		if (type.contains("Number"))
			ClientController.getController().accept(new Message(RequestType.FETCH_RESERVATION_DATA, r));
		else {
			//ClientController.getController().accept(new Message(RequestType.FETCH_MONTHLY_VISITOR_NUM, r));
			System.out.println("Fetching monthly visitor data is not implemented yet");
			return;
		}
	}

	
	public static void createReport(Report r) {
		ClientController.getController().accept(new Message(RequestType.CREATE_REPORT, r));
	}
}
