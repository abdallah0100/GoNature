package main.controllers;

import entities.InboxMessage;
import main.ClientController;
import requests.Message;
import requests.RequestType;

public class InboxRequestController {
	public static void fetchInbox(String id) {
		ClientController.getController().accept(new Message(RequestType.FETCH_INBOX, id));
	}
	
	public static void deleteMessage(InboxMessage msg) {
		ClientController.getController().accept(new Message(RequestType.DELETE_MSG, msg));
	}
}
