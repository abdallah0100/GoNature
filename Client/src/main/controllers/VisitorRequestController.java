package main.controllers;

import main.ClientController;
import requests.Message;
import requests.RequestType;

public class VisitorRequestController {
	
	public static boolean finishedValidating = false;

	public static void sendVisitorValidation(String id) {
		ClientController.getController().accept(new Message(RequestType.VALIDATE_VISITOR, id));
		
	}
}
