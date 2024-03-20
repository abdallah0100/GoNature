package main.controllers;
import entities.Order;
import main.ClientController;
import requests.Message;
import requests.RequestType;

public class VisitorRequestController {
	
	public static boolean finishedValidating = false;
	public static boolean finishedMakingReservation = false;
	public static boolean finishedShowingReservations = false;
	public static boolean finishedUpdatingReservation = false;


	public static void sendVisitorValidation(String id) {
		ClientController.getController().accept(new Message(RequestType.VALIDATE_VISITOR, id));	
	}
	public static void sendReservation(Order o) {
		ClientController.getController().accept(new Message(RequestType.MAKE_RESERVATION, o));		
	}
	public static void showReservations(String id) {
		ClientController.getController().accept(new Message(RequestType.SHOW_RESERVATIONS, id));
	}
	public static void updateReservation(Order o) {
		ClientController.getController().accept(new Message(RequestType.UPDATE_RESERVATION, o));

	}
}
