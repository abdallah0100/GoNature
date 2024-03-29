package main.controllers;

import entities.Order;
import main.ClientController;
import requests.Message;
import requests.RequestType;

/**
 * Manages reservation-related requests from the server including confirming, 
 * cancelling reservations, entering the waiting list, and checking for available places.
 */
public class ReservationController {

	/**
	 * Sends a request to confirm a reservation for a visitor.
	 * @param o The order object containing reservation details to be confirmed.
	 */
	public static void sendConfirmReservation(Order o) {
		ClientController.getController().accept(new Message(RequestType.CONFIRM_RESERVATION, o));
	}
	 
	/**
	 * Sends a request to cancel an existing reservation.
	 * @param o The order object representing the reservation to be cancelled.
	 */
	public static void sendCancelReservation(Order o) {
		if (o.getVisitorID() == null)
			o.setVisitorID(ClientController.connectedVisitor.getId());
		ClientController.getController().accept(new Message(RequestType.CANCEL_RESERVATION, o));
	}
	
	/**
	 * Sends a reservation order to the waiting list, used when the desired reservation time is fully booked.
	 * @param o The order object to be added to the waiting list.
	 */
	public static void enterWaitingList(Order o) {
		ClientController.getController().accept(new Message(RequestType.ENTER_WAITING_LIST, o));
	}
	
	/**
	 * Requests information about available places for a potential reservation.
	 * @param o The order object containing the initial reservation request details.
	 */
	public static void getAvailablePlaces(Order o) {
		ClientController.getController().accept(new Message(RequestType.GET_AVAILABLE_PLACES, o));
	}
	
}
