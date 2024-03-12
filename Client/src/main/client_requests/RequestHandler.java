package main.client_requests;

import entities.Visitor;
import main.ClientController;
import main.controllers.VisitorRequestController;
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
			default:
				System.out.println("[GoNatureClient] - unimplemented message type: " + msg.toString());
				if (msg.getRequestData() != null)
					System.out.println("[GoNatureClient] - Received data: " + msg.getRequestData());
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
		}
	}

}
