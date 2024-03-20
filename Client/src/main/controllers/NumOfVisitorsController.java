package main.controllers;

import main.ClientController;
import requests.Message;
import requests.RequestType;

public class NumOfVisitorsController {
	
	public static void sendShowNumOfVisitorsReport(String[] requestedData) {
		ClientController.getController().accept(new Message(RequestType.SHOW_NUM_OF_VISITORS_REPORT,requestedData));
	}

}
 