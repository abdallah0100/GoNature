package main.client_requests;

import entities.User;
import entities.Visitor;
import main.ClientController;
import main.controllers.UserRequestController;
import main.controllers.VisitorRequestController;
import requests.Message;

public class RequestHandler {
	
	public static void handleIncomingRequests(Message msg) {
		switch (msg.getRequestEnumType()) {
	    case REQUEST_ERROR:
	        System.out.println("[RequestHandler] - Server responded with an error: " + msg.getRequestData());
	        break;
	    case GENERAL_RESPOND:
	        System.out.println("[RequestHandler] - ServerResponse: " + msg.getRequestData());
	        break;
	    case VALIDATE_VISITOR:
	        if (msg.getRequestData() instanceof Visitor)
	            ClientController.connectedVisitor = (Visitor) msg.getRequestData();
	        else {
	            System.out.println("[RequestHandler] - invalid VALIDATE_VISITOR response");
	            return;
	        }
	        VisitorRequestController.finishedValidating = true;
	        break;
	    case LOGIN_USER:
	        if (msg.getRequestData() instanceof User)
	            ClientController.connectedUser = (User) msg.getRequestData();
	        else {
	            System.out.println("[RequestHandler] - invalid LOGIN_USER response");
	            return;
	        }
	        UserRequestController.LogedIn = true;
	        break;
	    default:
	        System.out.println("[RequestHandler] - unimplemented message type: " + msg.toString());
	        if (msg.getRequestData() != null)
	            System.out.println("[RequestHandler] - Received data: " + msg.getRequestData());
	        break;
				
		}
	}

}
