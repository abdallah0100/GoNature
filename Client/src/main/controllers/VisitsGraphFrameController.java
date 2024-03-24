package main.controllers;

import main.ClientController;
import requests.Message;
import requests.RequestType;

public class VisitsGraphFrameController {

	public static void getVisitsDetails(String[] receivedList) {
		ClientController.getController().accept(new Message(RequestType.VISITS_GRAPH_DATA,receivedList));
	}	
}
