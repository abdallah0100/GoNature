package main.client_requests;

import entities.User;
import entities.Visitor;
import main.ClientController;
import main.controllers.UserRequestController;
import main.controllers.VisitorRequestController;
import main.gui.service_agent.RegisterInstructorFrameController;
import requests.Message;

public class RequestHandler {
	
	public static void handleIncomingRequests(Message msg) {
		switch(msg.getRequestEnumType()) {
		case REQUEST_ERROR:
			System.out.println("[GoNatureClient] - Server responded with an error: " + msg.getRequestData());
			break;
		case GENERAL_RESPOND:
			System.out.println("[GoNatureClient] - ServerResponse: " + msg.getRequestData());
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
				if (msg.getRequestData() instanceof User) {
					ClientController.connectedUser = (User) msg.getRequestData();
					UserRequestController.LogedIn = true;
					return;
				}
				else {
					System.out.println("[RequestHandler] - invalid LOGIN_USER response");
					return;
				}
		case REQUEST_BILL:
				if (msg.getRequestData() instanceof String) {
					ClientController.connectedUser.setRequestedBill((String) msg.getRequestData());
					return; 
				}
				else {
					ClientController.connectedUser.setRequestedBill(null);
					System.out.println("[RequestHandler] - invalid SHOW_BILL response");
					return;
				}
		case INSERT_INSTRUCTOR:
				if (msg.getRequestData() instanceof Integer) {
					RegisterInstructorFrameController.result=((Integer) msg.getRequestData());
					return; 
				}
				else {
					ClientController.connectedUser.setRequestedBill(null);
					System.out.println("[RequestHandler] - invalid INSERT_INSTRUCTOR response");
					return;
				}
		default:
				System.out.println("[GoNatureClient] - unimplemented message type: " + msg.toString());
				if (msg.getRequestData() != null)
					System.out.println("[GoNatureClient] - Received data: " + msg.getRequestData());
				break;
				
		}
	}

}