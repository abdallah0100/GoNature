package main.controllers;
import entities.Order;
import main.ClientController;
import requests.Message;
import requests.RequestType;

/**
 * Manages visitor-related requests including validation, making, showing, and updating reservations.
 * This class requests these requests from the server.
 */
public class VisitorRequestController {
	
	// Flags to track completion status of visitor related requests.
	public static boolean finishedValidating = false;
	public static boolean finishedMakingReservation = false;
	public static boolean finishedShowingReservations = false;
	public static boolean finishedUpdatingReservation = false;

	/**
	 * Sends a request to validate a visitor based on their ID.
	 * @param id The unique id of the visitor to be validated.
	 */
	public static void sendVisitorValidation(String id) {
		ClientController.getController().accept(new Message(RequestType.VALIDATE_VISITOR, id));
	}
	
	/**
	 * Sends a reservation order to the server for processing.
	 * @param o The Order object containing the reservation details.
	 */
	public static void sendReservation(Order o) {
		ClientController.getController().accept(new Message(RequestType.MAKE_RESERVATION, o));		
	}
	
	/**
	 * Requests a list of reservations associated with a specific visitor ID.
	 * @param id The unique id of the visitor whose reservations are to be displayed.
	 */
	public static void showReservations(String id) {
		ClientController.getController().accept(new Message(RequestType.SHOW_RESERVATIONS, id));
	}
	
	/**
	 * Sends an updated reservation order to the server to process changes.
	 * @param o The updated Order object with new reservation details.
	 */
	public static void updateReservation(Order o) {
		ClientController.getController().accept(new Message(RequestType.UPDATE_RESERVATION, o));
	}
	
	public static void fetchWaitingList(String id) {
		ClientController.getController().accept(new Message(RequestType.FETCH_VISITOR_WAITINGLIST, id));
	}
	
	public static void deleteOrderFromWaitingList(String orderId) {
		ClientController.getController().accept(new Message(RequestType.DELETE_FROM_WAITINGLIST, orderId));
	}
}
