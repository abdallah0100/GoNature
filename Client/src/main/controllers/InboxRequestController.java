package main.controllers;

import entities.InboxMessage;
import main.ClientController;
import requests.Message;
import requests.RequestType;

/**
 * Manages inbox-related requests, including fetching and deleting inbox messages.
 * This class communicates with the server to perform the inbox-related requests.
 */
public class InboxRequestController {
	
	/**
	 * Requests the inbox messages for a specific user.
	 * @param id The unique id of the user whose inbox messages are to be fetched.
	 */
	public static void fetchInbox(String id) {
		ClientController.getController().accept(new Message(RequestType.FETCH_INBOX, id));
	}
	
	/**
	 * Requests the deletion of a specific inbox message.
	 * @param msg The inbox message to be deleted.
	 */
	public static void deleteMessage(InboxMessage msg) {
		ClientController.getController().accept(new Message(RequestType.DELETE_MSG, msg));
	}
}
