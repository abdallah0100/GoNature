package main.client_requests;

import java.util.HashMap;

import entities.Bill;
import entities.Order;
import entities.Park;
import entities.Report;
import entities.User;
import entities.Visitor;
import main.ClientController;
import main.controllers.UserRequestController;
import main.controllers.VisitorRequestController;
import main.gui.park_manager.PrepareReportFrameController;
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
					String parkName = ClientController.connectedUser.getParkName();
					ClientController.connectedUser.setPark(ClientController.getParks().get(parkName));
					if (ClientController.connectedUser.getPark() == null)
						System.out.println("[RequestHandler] - invalid park name");
					UserRequestController.LogedIn = true;
					return;
				}
				else {
					System.out.println("[RequestHandler] - invalid LOGIN_USER response");
					return;
				}
		case MAKE_RESERVATION:
			if (msg.getRequestData() instanceof Order) {
				ClientController.reservationMade = (Order) msg.getRequestData();
				VisitorRequestController.finishedMakingReservation = true;
				return;
			}
			else {
				System.out.println("[RequestHandler] - invalid MAKE_RESERVATION response");
				return;
			}
				
				
		case REQUEST_BILL:
				if (msg.getRequestData() instanceof Bill) {
					ClientController.showBill=(Bill) msg.getRequestData();
					return; 
				}
				else {
					ClientController.showBill=null;
					System.out.println("[RequestHandler] - invalid SHOW_BILL response");
					return;
				}
				
				
				
		case INSERT_INSTRUCTOR:
				if (msg.getRequestData() instanceof Integer) {
					RegisterInstructorFrameController.result=((Integer) msg.getRequestData());
					return; 
				}
				else {
					RegisterInstructorFrameController.result=-1;//fail
					System.out.println("[RequestHandler] - invalid INSERT_INSTRUCTOR response");
					return;
				}
		case FETCH_RESERVATION_DATA:
			if (!(msg.getRequestData() instanceof Report)) {
				System.out.println("[RequestHandler] - invalid FETCH_RESERVATION_DATA response type");
				return;
			}
			PrepareReportFrameController.report_withData = (Report) msg.getRequestData();
			break;
		case CREATE_REPORT:
			if (!(msg.getRequestData() instanceof String)) {
				System.out.println("[RequestHandler] - invalid CREATE_REPORT response type");
				return;
			}
			PrepareReportFrameController.report_withData.setCreationStatus((String)msg.getRequestData());
			break;
		case FETCH_PARKS:
			if (!(msg.getRequestData() instanceof Park[])) {
				System.out.println("[RequestHandler] - received no parks");
				return;
			}
			Park[] parks = (Park[]) msg.getRequestData();
			HashMap<String, Park> parkMap = new HashMap<>();
			for (Park p : parks)
				parkMap.put(p.getParkName(), p);
			ClientController.setParks(parkMap);
			break;
		default:
				System.out.println("[GoNatureClient] - unimplemented message type: " + msg.toString());
				if (msg.getRequestData() != null)
					System.out.println("[GoNatureClient] - Received data: " + msg.getRequestData());
				break;
				
		}
	}

}