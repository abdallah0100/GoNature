package main.controllers;


import main.ClientController;
import requests.Message;
import requests.RequestType;

public class DeleteRequestController {
	
	public static void deleteRequestChange(String[] dataToDelete) {
		ClientController.getController().accept(new Message(RequestType.DELETE_REQUEST_CHANGE,dataToDelete));
	}
	
	

}
