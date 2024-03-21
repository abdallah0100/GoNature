package main.controllers;

import main.ClientController;
import requests.Message;
import requests.RequestType;

public class CheckEditedVarsController {

	public static void sendShowEditedVars(String requestedPark) {
		ClientController.getController().accept(new Message(RequestType.SHOW_EDITED_VARIABLES,requestedPark));
	}	
}
