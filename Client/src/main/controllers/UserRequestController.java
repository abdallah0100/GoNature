package main.controllers;

import entities.User;
import main.ClientController;
import requests.Message;
import requests.RequestType;

public class UserRequestController {
	public static boolean LogedIn = false;

	public static void sendUserLogIn(String id,String password) {
		ClientController.getController().accept(new Message(RequestType.LOGIN_USER, new User(id,password)));
	}
	
	public static void sendShowBill(String id) {
		ClientController.getController().accept(new Message(RequestType.SHOW_BILL,id));
	}

}
