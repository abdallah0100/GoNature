package main.controllers;

import main.ClientController;
import requests.Message;
import requests.RequestType;

public class DataForAnalyingController {

	public static void getNumOfCancellations(String[] receivedList) {
		ClientController.getController().accept(new Message(RequestType.CANCELLATIONS_GRAPH_DATA,receivedList));
	}	
}
