package main.controllers;

import main.ClientController;
import requests.Message;
import requests.RequestType;

/**
 * Communication between the client and server for requests related to the number of visitors report.
 */
public class NumOfVisitorsController {
	
	/**
	 * Sends a request to the server to retrieve a report on the number of visitors.
	 * @param requestedData An array containing the data for the report, such as date range and park name.
	 */
	public static void sendShowNumOfVisitorsReport(String[] requestedData) {
		ClientController.getController().accept(new Message(RequestType.SHOW_NUM_OF_VISITORS_REPORT,requestedData));
	}

}
 