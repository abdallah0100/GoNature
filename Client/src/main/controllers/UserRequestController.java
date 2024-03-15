package main.controllers;

import entities.Bill;
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
}
