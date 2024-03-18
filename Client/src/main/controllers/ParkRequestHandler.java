package main.controllers;

import entities.Park;
import main.ClientController;
import requests.Message;
import requests.RequestType;

public class ParkRequestHandler {
	public static void requestAllParks() {
		ClientController.getController().accept(new Message(RequestType.FETCH_PARKS));
	}
	
	public static void updateVariable(Park p) {
		ClientController.getController().accept(new Message(RequestType.UPDATE_PARK_VARIABLE, p));
	}
}
