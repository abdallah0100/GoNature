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
	
	public static void requsetChange(String s[]) {
		ClientController.getController().accept(new Message(RequestType.REQUEST_CHANGE, s));
	}
	
	//send 2 time or more
	public static void UpdateData(String s[]) {
		ClientController.getController().accept(new Message(RequestType.UPDATE_REQUEST_CHANGE, s));
	}
}
