package main.controllers;

import main.ClientController;
import requests.Message;
import requests.RequestType;

/**
 * Controller responsible for managing data requests for analysis purposes,
 * particularly focusing on obtaining data related to cancellations.
 */
public class DataForAnalyingController {

	/**
	 * Requests the number of cancellations based on specified standards from the server.
	 * This method sends a request to the server, asking for data that can be used to analyze cancellations.
	 * 
	 * @param receivedList An array containing the standards based on which cancellations data is requested.
	 */
	public static void getNumOfCancellations(String[] receivedList) {
		ClientController.getController().accept(new Message(RequestType.CANCELLATIONS_GRAPH_DATA,receivedList));
	}	
}
