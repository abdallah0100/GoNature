package main.client_requests;

import java.util.ArrayList;
import java.util.HashMap;
import entities.AvailablePlace;
import entities.Bill;
import entities.CancelledReservation;
import entities.InboxMessage;
import entities.Order;
import entities.Park;
import entities.Report;
import entities.UsageReport;
import entities.User;
import entities.Visitor;
import entities.VisitsReport;
import main.ClientController;
import main.controllers.UserRequestController;
import main.controllers.VisitorRequestController;
import main.gui.LogInFrameController;
import main.gui.dep_manager.CancellationsGraphFrameController;
import main.gui.dep_manager.CancellationsReportFrameController;
import main.gui.dep_manager.DecideVarEditFrameController;
import main.gui.dep_manager.ReportDetailsFrameController;
import main.gui.dep_manager.SelectVisitsDetailsFrameController;
import main.gui.dep_manager.UsageReportFrameController;
import main.gui.entry_worker.EnterVisitorsFrameController;
import main.gui.park_manager.EditParkVariablesController;
import main.gui.park_manager.PrepareReportFrameController;
import main.gui.service_agent.RegisterInstructorFrameController;
import main.gui.visitor.DeclinedReservationOptions;
import main.gui.visitor.MakeReservationFrameController;
import main.gui.visitor.ValidationFrameController;
import main.gui.visitor.WaitingListFrameController;
import main.threads.VisitorReminder;
import requests.Message;


/**
 * The RequestHandler class manages incoming requests from the server and handles them accordingly.
 * It contains a static method `handleIncomingRequests` which interprets the incoming messages
 * and takes appropriate actions based on the type of request.
 */
public class RequestHandler {
	
	
	/**
     * Handles incoming requests from the server.
     * @param msg The message received from the server.
     */
	public static void handleIncomingRequests(Message msg) {
		Order o;
		String response;
		switch(msg.getRequestEnumType()) {
		case REQUEST_ERROR:
			System.out.println("[GoNatureClient] - Server responded with an error: " + msg.getRequestData());
			break;
		case GENERAL_RESPOND:
			System.out.println("[GoNatureClient] - ServerResponse: " + msg.getRequestData());
			break; 
		case VALIDATE_VISITOR:
			if (msg.getRequestData() instanceof Visitor) {
				ValidationFrameController.alreadyIn = false;
				ClientController.connectedVisitor = (Visitor) msg.getRequestData();
			}
			else if (msg.getRequestData() instanceof String) {
				response = (String)msg.getRequestData();
				System.out.println(response);
				if (response.equals("already in")) {
					ValidationFrameController.alreadyIn = true;
					break;
				}
			}else {
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
					LogInFrameController.alreadyIn = false;
					UserRequestController.LogedIn = true;
					return;
				}else if (msg.getRequestData() instanceof String) {
					response = (String)msg.getRequestData();
					if (response.equals("alreadyIn")) {
						LogInFrameController.alreadyIn = true;
						return;
					}
				}
				else {
					System.out.println("[RequestHandler] - invalid LOGIN_USER response");
					return;
				}
		case MAKE_RESERVATION:
			if (msg.getRequestData() instanceof Order) {
				ClientController.reservationMade = (Order) msg.getRequestData();
				VisitorRequestController.finishedMakingReservation = true;
				MakeReservationFrameController.hasSpace = true;
				return;
			}else if (msg.getRequestData() instanceof AvailablePlace[]) {
				DeclinedReservationOptions.setAvbl((AvailablePlace[]) msg.getRequestData());
				MakeReservationFrameController.hasSpace = false;
				VisitorRequestController.finishedMakingReservation = true;
				return;
			}else {
				System.out.println("[RequestHandler] - invalid MAKE_RESERVATION response");
				MakeReservationFrameController.hasSpace = false;
				VisitorRequestController.finishedMakingReservation = false;
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
		case REGIST_INSTRUCTOR:
				if (msg.getRequestData() instanceof String) {
					RegisterInstructorFrameController.regist=((String) msg.getRequestData());
					return; 
				}
				else {
					System.out.println("[RequestHandler] - invalid REGIST_INSTRUCTOR ");
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
		case SHOW_RESERVATIONS:
			if (msg.getRequestData() instanceof Order[]) {
				ClientController.reservationshowed = (Order[]) msg.getRequestData();
				VisitorRequestController.finishedShowingReservations = true;
				return;
			}
			else {
				System.out.println("[RequestHandler] - invalid SHOW_RESERVATIONS response");
				return;
			}
			
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
		case UPDATE_PARK_VARIABLE:
			if (!(msg.getRequestData() instanceof Park) || msg.getResponseMsg() == null) {
				System.out.println("[RequestHandler] - invalid park variable update response");
				return;
			}
			Park p1 = (Park)msg.getRequestData();
			EditParkVariablesController.updateResult = msg.getResponseMsg();
			if (p1.isUpdated()) {
				if (p1.getVarbToUpdate().equals("gap"))
					ClientController.getParks().get(p1.getParkName()).setGap(p1.getNewValue());
				else if (p1.getVarbToUpdate().equals("EstimatedTime"))
					ClientController.getParks().get(p1.getParkName()).setEstimatedTime(p1.getNewValue());
				else ClientController.getParks().get(p1.getParkName()).setMaxCapacity(p1.getNewValue());
				System.out.println("[ClientController] - Successfully updated new varb");
			}else {
				System.out.println("[ClientController] - Failed to update varb");
			}
			break;
			
		case SHOW_USAGE_REPORT:
			if (msg.getRequestData() instanceof UsageReport[]) {
				UsageReportFrameController.setList((UsageReport[])msg.getRequestData());
				return; 
			}
			else {
				UsageReportFrameController.setList(null);
				System.out.println("[RequestHandler] - invalid SHOW_USAGE_REPORT response");
				return;
			}
			
		case SHOW_CANCELLATIONS_REPORTS:
			if (msg.getRequestData() instanceof CancelledReservation[]) {
				// for left table
				if("Yes".equals((String)msg.getResponseMsg())) { 
					CancellationsReportFrameController.setArrayListLeft((CancelledReservation[])msg.getRequestData());
					return; 
				}
				// for right table
				CancellationsReportFrameController.setArrayListRight((CancelledReservation[])msg.getRequestData());
				return; 
			}
			else {
				CancellationsReportFrameController.setArrayListLeft(null);
				CancellationsReportFrameController.setArrayListRight(null);
				System.out.println("[RequestHandler] - invalid SHOW_CANCELLATIONS_REPORTS response");
				return;
			}
			
		case CHECK_IF_REQ_EXIST:		
		case REQUEST_CHANGE:
			if (!(msg.getRequestData() instanceof Boolean)) {
				System.out.println("[RequestHandler] - invalid REQUEST_CHANGE response type (String)");
				return;
			}
			EditParkVariablesController.exist=(Boolean)(msg.getRequestData());
			return;
			
		case UPDATE_REQUEST_CHANGE:
			if (!(msg.getRequestData() instanceof Boolean)) {
				System.out.println("[RequestHandler] - invalid UPDATE_REQUEST_CHANGE response type (Boolean)");
				return;
			}
			return;
		case ENTER_VISTOR:
			if (!(msg.getRequestData() instanceof Boolean)) {
				System.out.println("[RequestHandler] - invalid ENTER_VISTOR response type (Boolean)");
				return;
			}
			ClientController.monitoring=(Boolean)msg.getRequestData();
			return;
		case EXIT_VISITOR:
			if (!(msg.getRequestData() instanceof Boolean)) {
				System.out.println("[RequestHandler] - invalid EXIT_VISITOR response type (Boolean)");
				return;
			}
			ClientController.monitoring=(Boolean)msg.getRequestData();
			return;
			
		case CONFIRM_RESERVATION:
			if (!(msg.getRequestData() instanceof Order)) {
				System.out.println("[RequestHandler] - invalid server response");
				return;
			}
			Order temp = (Order)msg.getRequestData();
			if (temp.getIsConfirmed() && ClientController.reservationshowed != null) {
				for (Order o1 : ClientController.reservationshowed)
					if (o1.getOrderID().equals(temp.getOrderID())) {
						o1.setIsConfirmed(true);
						VisitorReminder.updateAndGetMessages();
						return;
					}
			}
		return;
		case CANCEL_RESERVATION:
			if (!(msg.getRequestData() instanceof Order)) {
				System.out.println("[RequestHandler] - invalid server response");
				return;
			}
			o = (Order)msg.getRequestData();
			if (o.isCanceled()) {
				ArrayList<Order> arr = new ArrayList<>();
				for (Order o2 : ClientController.reservationshowed)
					if (!o2.getOrderID().equals(o.getOrderID()))
						arr.add(o2);
				ClientController.reservationshowed = arr.toArray(new Order[arr.size()]);
				VisitorReminder.updateAndGetMessages();
			}			
			break;
		
		case UPDATE_RESERVATION:
			if (msg.getRequestData() instanceof Order) {
				ClientController.updatedReservation = (Order) msg.getRequestData();
				VisitorRequestController.finishedUpdatingReservation = true;
				return;
			}
			else {
				System.out.println("[RequestHandler] - invalid UPDATE_RESERVATION response");
				return;
			}
		case SHOW_NUM_OF_VISITORS_REPORT:
			if (msg.getRequestData() instanceof String[]) {
				ReportDetailsFrameController.setData((String[])msg.getRequestData());
				return;	
			}
			else {
				ReportDetailsFrameController.setData(null);
				System.out.println("[RequestHandler] - invalid SHOW_NUM_OF_VISITORS_REPORT response");
				return;
			}
		case SHOW_EDITED_VARIABLES:
			if (msg.getRequestData() instanceof HashMap<?, ?>) {
				DecideVarEditFrameController.setReturnedHashMap((HashMap<?,?>)msg.getRequestData());
				System.out.println("[RequestHandler] - Data edited successfully.");
				return;
			}
			else {
				System.out.println("[RequestHandler] - invalid SHOW_EDITED_VARIABLES response");
				return;
			}
		case DELETE_REQUEST_CHANGE:			
			if(msg.getRequestData() instanceof Boolean ) {
				if((Boolean)(msg.getRequestData())){
		            System.out.println("[RequestHandler] - Data deleted successfully.");
		            return;
				}
                System.out.println("[RequestHandler] - invalid DELETE_REQUEST_CHANGE response");
			}
			return;
		case CANCELLATIONS_GRAPH_DATA:
			if (msg.getRequestData() instanceof int[]) {
				int[] received = (int[]) msg.getRequestData();
				CancellationsGraphFrameController.setValues(received[0],received[1],received[2]);		
				System.out.println("[RequestHandler] - Data received successfully.");
	            return;
			}
			else {
				System.out.println("[RequestHandler] - invalid CANCELLATIONS_GRAPH_DATA response");
				return;
			}
		case ENTER_WAITING_LIST:
			if(!(msg.getRequestData() instanceof Order )) {
		            System.out.println("[RequestHandler] -  not order int waintlist (Order).");
		            return;
			}if((Order)(msg.getRequestData())!=null)
				DeclinedReservationOptions.inserted=true;
			return;
		case FETCH_INBOX:
			if((msg.getRequestData() instanceof InboxMessage[])) {
	           VisitorReminder.setServerInboxResponse((InboxMessage[]) msg.getRequestData());
				return;
			}else {
		         VisitorReminder.setServerInboxResponse(null);
		         return;
		    }
		case DELETE_MSG:
			if (msg.getRequestData() instanceof InboxMessage) {
				InboxMessage inboxMsg = (InboxMessage)msg.getRequestData();
				if (inboxMsg.isDeleted())
					VisitorReminder.removeMsgIfExist(inboxMsg.getId());
				VisitorReminder.updateAndGetMessages();
			}
			return;
		case VISITS_GRAPH_DATA:
			if (msg.getRequestData() instanceof VisitsReport[]) {
				VisitsReport[] received = (VisitsReport[]) msg.getRequestData();
				SelectVisitsDetailsFrameController.setReturnedTimesData(received);		
				System.out.println("[RequestHandler] - Data received successfully.");
	            return;
			}
			else {
				System.out.println("[RequestHandler] - invalid VISITS_GRAPH_DATA response");
				return;
			}
		case REQUEST_PARK:
			if(!(msg.getRequestData() instanceof Park )) {
	            System.out.println("[RequestHandler] -  not order int waintlist (Park).");
	            return;
		}
			EnterVisitorsFrameController.p=(Park)msg.getRequestData();
			return;
		case ORDER_ID:
			if(!(msg.getRequestData() instanceof Integer )) {
	            System.out.println("[RequestHandler] -  not order int waintlist (Integer).");
	            return;
			}
			EnterVisitorsFrameController.orderID=(int)msg.getRequestData();
			return;
		case MAKE_RESERVATION_ENTRY:
			if (msg.getRequestData() instanceof Order) {
				ClientController.reservationMade = (Order) msg.getRequestData();
				VisitorRequestController.finishedMakingReservation = true;
				return;
			}
			System.out.println("[RequestHandler] -  not order int waintlist (Order).");
            return;
		case CHECK_INSTRUCTOR:
			if(!(msg.getRequestData() instanceof Boolean )) {
	            System.out.println("[RequestHandler] -  not order int waintlist (Boolean).");
	            return;
			}
			EnterVisitorsFrameController.isInstructor=(Boolean)msg.getRequestData();
			return;
		case FETCH_NOT_FULL_DATA:
			if (!(msg.getRequestData() instanceof Report)) {
				System.out.println("[RequestHandler] - Invalid response type FETCH_NOT_FULL_DATA");
				return;
			}
			PrepareReportFrameController.report_withData = (Report)msg.getRequestData();
			break;
		case FETCH_VISITOR_WAITINGLIST:
			if ((msg.getRequestData() instanceof Order[])) {
				ClientController.connectedVisitor.setWaitingList((Order[]) msg.getRequestData());
			}else {
				System.out.println("[RequestHandler] - user had no orders in the waiting list");
				ClientController.connectedVisitor.setWaitingList(null);
			}			
			break;
		case DELETE_FROM_WAITINGLIST:
			if (!(msg.getRequestData() instanceof Boolean)) {
				System.out.println("[RequestHandler] - Invalid response type DELETE_FROM_WAITINGLIST");
			}
			WaitingListFrameController.deleteResult = (Boolean)msg.getRequestData();
			break;
		default:
				System.out.println("[GoNatureClient] - unimplemented message type: " + msg.toString());
				if (msg.getRequestData() != null)
					System.out.println("[GoNatureClient] - Received data: " + msg.getRequestData());
				break;
				
		}
	}

}