package main.controllers;

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
		ClientController.getController().accept(new Message(RequestType.REQUEST_BILL,id));
	}
	
	public static void insertInstructor(String instructor_id,String instructorName,String instructor_email,String instructor_tel) {
		ClientController.getController().accept(new Message(RequestType.INSERT_INSTRUCTOR,
												new Visitor(instructor_id,instructorName,instructor_email,instructor_tel)));
	}
	
	public static void fetchReportData(String parkName, String month, String year) {
		Report r = new Report(parkName, month, year, ClientController.connectedUser.getUsername());
		ClientController.getController().accept(new Message(RequestType.FETCH_RESERVATION_DATA, r));
	}
	
	public static void createReport(Report r) {
		ClientController.getController().accept(new Message(RequestType.CREATE_REPORT, r));
	}
}
