package main.controllers;


import main.ClientController;
import requests.Message;
import requests.RequestType;

/**
 * Controller responsible for handling requests to delete existing change requests from the system.
 * This class communicates with the server to perform the deletion based on specified parameters.
 */
public class DeleteRequestController {
	
	/**
	 * Sends a request to delete a change request.
	 * 
	 * @param dataToDelete Identifiers for the change request to be deleted.
	 */
	public static void deleteRequestChange(String[] dataToDelete) {
		ClientController.getController().accept(new Message(RequestType.DELETE_REQUEST_CHANGE,dataToDelete));
	}
}
