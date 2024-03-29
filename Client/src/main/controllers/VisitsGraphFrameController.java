package main.controllers;

import main.ClientController;
import requests.Message;
import requests.RequestType;

/**
 * Handles requests for detailed visitation data intended for graphical representation.
 * This controller communicates with the server to fetch the necessary data based on specified parameters.
 */
public class VisitsGraphFrameController {

	/**
	 * Requests visitation data
	 * @param receivedList Parameters defining the data scope, like dates or park names.
	 */
	public static void getVisitsDetails(String[] receivedList) {
		ClientController.getController().accept(new Message(RequestType.VISITS_GRAPH_DATA,receivedList));
	}	
}
