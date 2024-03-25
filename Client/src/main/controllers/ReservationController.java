package main.controllers;

import entities.Order;
import main.ClientController;
import requests.Message;
import requests.RequestType;

public class ReservationController {

	public static void sendConfirmReservation(Order o) {
		ClientController.getController().accept(new Message(RequestType.CONFIRM_RESERVATION, o));
	}
	 
	public static void sendCancelReservation(Order o) {
		ClientController.getController().accept(new Message(RequestType.CANCEL_RESERVATION, o));
	}
	
	public static void enterWaitingList(Order o) {
		ClientController.getController().accept(new Message(RequestType.ENTER_WAITING_LIST, o));
	}
	
	public static void getAvailablePlaces(Order o) {
		ClientController.getController().accept(new Message(RequestType.GET_AVAILABLE_PLACES, o));
	}
	
}
