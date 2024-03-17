package main.controllers;

import main.ClientController;
import requests.Message;
import requests.RequestType;

public class ParkRequestHandler {
	public static void requestAllParks() {
		ClientController.getController().accept(new Message(RequestType.FETCH_PARKS));
	}
}
