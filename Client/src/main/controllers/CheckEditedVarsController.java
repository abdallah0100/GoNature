package main.controllers;

import main.ClientController;
import requests.Message;
import requests.RequestType;

/**
 * Controls the flow of requests to the server for retrieving information about edited variables of a park.
 */
public class CheckEditedVarsController {

	/**
	 * Sends a request to the server to retrieve a list of variables that have been edited for a specific park.
	 * 
	 * @param requestedPark The name of the park for which the edited variables are requested.
	 */
	public static void sendShowEditedVars(String requestedPark) {
		ClientController.getController().accept(new Message(RequestType.SHOW_EDITED_VARIABLES,requestedPark));
	}	
}
