package main.controllers;

import entities.Park;
import entities.Report;
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
	
	public static void requsetChange(Report r) {
		ClientController.getController().accept(new Message(RequestType.REQUEST_CHANGE, r));
	}
	
	public static void reportExist(Report r) {
		ClientController.getController().accept(new Message(RequestType.CHECK_IF_REQ_EXIST, r));
	}
	
	//send 2 time or more
	public static void UpdateData(Report r) {
		ClientController.getController().accept(new Message(RequestType.UPDATE_REQUEST_CHANGE, r));
	}
	
	//get park 
	public static void requestPark(String parkName) {
		ClientController.getController().accept(new Message(RequestType.REQUEST_PARK,parkName));
	}
	
	
}
