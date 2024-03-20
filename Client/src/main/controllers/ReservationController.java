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
	
}
