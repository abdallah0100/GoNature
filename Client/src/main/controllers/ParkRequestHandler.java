package main.controllers;

import entities.Park;
import entities.Report;
import main.ClientController;
import requests.Message;
import requests.RequestType;

/**
 * Sends requests related to park information and changes, including fetching park data, updating park variables,
 * to the server.
 */
public class ParkRequestHandler {
	
	/**
	 * Requests a list of all parks from the server.
	 */
	public static void requestAllParks() {
		ClientController.getController().accept(new Message(RequestType.FETCH_PARKS));
	}
	
	/**
	 * Sends a request to update a park variable, such as capacity or gap.
	 * @param p The park entity with updated variable values.
	 */
	public static void updateVariable(Park p) {
		ClientController.getController().accept(new Message(RequestType.UPDATE_PARK_VARIABLE, p));
	}
	
	/**
	 * Requests a change in the park's variables.
	 * @param r The report entity containing the details of the requested change.
	 */
	public static void requsetChange(Report r) {
		ClientController.getController().accept(new Message(RequestType.REQUEST_CHANGE, r));
	}
	
	/**
	 * Checks if a request for change already exists in the system.
	 * @param r The report entity to check for an existing request.
	 */
	public static void reportExist(Report r) {
		ClientController.getController().accept(new Message(RequestType.CHECK_IF_REQ_EXIST, r));
	}
	
	/**
	 * Updates an existing change request, typically used when the department manager confirms the change.
	 * @param r The updated report entity.
	 */
	public static void UpdateData(Report r) {
		ClientController.getController().accept(new Message(RequestType.UPDATE_REQUEST_CHANGE, r));
	}
	
	/**
	 * Requests detailed information about a specific park.
	 * @param parkName The name of the park for which information is requested.
	 */
	public static void requestPark(String parkName) {
		ClientController.getController().accept(new Message(RequestType.REQUEST_PARK,parkName));
	}
	
	
}
