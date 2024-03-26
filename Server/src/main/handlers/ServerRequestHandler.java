package main.handlers;
import java.io.IOException;
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
import ocsf.server.src.ConnectionToClient;
import requests.Message;
import requests.RequestType;

public class ServerRequestHandler {

	public static void handleRequest(Message msg, ConnectionToClient client) {
		String generalRespondMsg = "responded with no message.";
		Report r;
		Visitor v;
		User u;
		boolean result;
		switch(msg.getRequestEnumType()) {
		case CONNECT_TO_SERVER:
			ClientConnectionHandler.handleConnectRequest(client, true);
			generalRespondMsg = "New Connection has been added successfully";
			break;
		case DISCONNECT_FROM_SERVER:
			if (msg.getRequestData() instanceof String) {
				String connected = (String) msg.getRequestData();
				if (connected.length() > 0)
					ClientConnectionHandler.handleLogout(connected);
			}
			ClientConnectionHandler.handleConnectRequest(client, false);
			generalRespondMsg = "Client has succesfully disconnected from the server";
			break;
		case VALIDATE_VISITOR:
			if (!(msg.getRequestData() instanceof String)) {
				respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data (not String)"));
				return;
			}
			if (ClientConnectionHandler.clientAlreadyConnected((String) msg.getRequestData())) {
				respondToClient(client, new Message(RequestType.VALIDATE_VISITOR, "already in"));
				return;
			}
			v = VisitorRequestHandler.handleValidateRequest((String) msg.getRequestData());
			ClientConnectionHandler.handleUserLogin(client, v.getId());
			respondToClient(client, new Message(RequestType.VALIDATE_VISITOR, v));
			return;
			
		case LOGIN_USER:
			if (!(msg.getRequestData() instanceof User)) {
				respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data (not User)"));
				return;
			}
			User tempU = (User) msg.getRequestData();
			if (ClientConnectionHandler.clientAlreadyConnected(tempU.getUsername())) {
				respondToClient(client, new Message(RequestType.LOGIN_USER, "alreadyIn"));
				return;
			}
			u = UserRequestHandler.handleLogInRequest((User) msg.getRequestData());
			if (u != null)
				ClientConnectionHandler.handleUserLogin(client, u.getUsername());
			respondToClient(client, new Message(RequestType.LOGIN_USER, u));
			return;
		case MAKE_RESERVATION:
			if (!(msg.getRequestData() instanceof Order)) {
				respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data (not Order)"));
				return;
			}
			Order receivedOrder = (Order)msg.getRequestData();
			
			if (!ParkRequestHandler.parkHasSpace(receivedOrder)) {
				System.out.println("1");
				AvailablePlace[] arr = ParkRequestHandler.getAvailableTimes(receivedOrder);
				System.out.println(2);
				respondToClient(client, new Message(RequestType.MAKE_RESERVATION, arr));
				return;
			}
			System.out.println(3);
			Order o = VisitorRequestHandler.handleMakeReservationRequest(receivedOrder,"reservations");
			respondToClient(client, new Message(RequestType.MAKE_RESERVATION, o));
			return;
			
			
		case REQUEST_BILL:
			if (!(msg.getRequestData() instanceof Bill)) {
				respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data (Bill)"));
				return;
			}
			Bill b = UserRequestHandler.billExists((Bill) msg.getRequestData());
			respondToClient(client, new Message(RequestType.REQUEST_BILL, b));
			return;
			
			
		case REGIST_INSTRUCTOR:
			if(!(msg.getRequestData() instanceof String)) {	
			respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data String"));
			return;
		}
			String x=UserRequestHandler.activated((String) msg.getRequestData());
			respondToClient(client, new Message(RequestType.REGIST_INSTRUCTOR, x));
			return;
		
		case FETCH_RESERVATION_DATA:
			if (!(msg.getRequestData() instanceof Report)) {
				respondToClient(client, new Message(RequestType.FETCH_RESERVATION_DATA, "invalid request data (Report -> fetching data)"));
				return;
			}
			r = (Report) msg.getRequestData();
			int individuals = ReportRequestHandler.getReservationCountByType("Private", r);
			int group = ReportRequestHandler.getReservationCountByType("Organized Group", r);
			boolean reportExist = ReportRequestHandler.numReportExist(r);
			r.setReportExist(reportExist);
			r.setIndividuals(individuals);
			r.setGroups(group);
			respondToClient(client, new Message(RequestType.FETCH_RESERVATION_DATA, r));
		return;
		case FETCH_MONTHLY_VISITOR_NUM:
			if (!(msg.getRequestData() instanceof Report)) {
				respondToClient(client, new Message(RequestType.FETCH_RESERVATION_DATA, "invalid request data (Report -> fetching monthly visitor num data)"));
				return;
			}
			r = (Report) msg.getRequestData();
			boolean reportExists = ReportRequestHandler.notFullReportExist(r);
			r.setReportExist(reportExists);
			return;
		case CREATE_REPORT:
			if (!(msg.getRequestData() instanceof Report)) {
				respondToClient(client, new Message(RequestType.FETCH_RESERVATION_DATA, "invalid request data (Report -> Creation)"));
				return;
			}
			r = (Report) msg.getRequestData();
			if (r.isReportExist()) {
				if (r.isNotFullParkReport()) {
					result = ReportRequestHandler.updateEmptyReport(r);
					generalRespondMsg = result ? "Successfully updated report" : "Error updating report";
				}else {
					result = ReportRequestHandler.updateNumReport(r);
					generalRespondMsg = result ? "Successfully updated report" : "Error updating report";
				}
			}else {
				if (r.isNotFullParkReport()) {
					result = ReportRequestHandler.createEmptyReport(r);
					generalRespondMsg = result ? "Successfully updated report" : "Error updating report";
				}else {
					result = ReportRequestHandler.insertNewNumReport(r);
					generalRespondMsg = result ? "Successfully created report" : "Error creating report";
				}
			}
			respondToClient(client, new Message(RequestType.CREATE_REPORT, generalRespondMsg));
			return;
			
		case SHOW_RESERVATIONS:
			if (!(msg.getRequestData() instanceof String)) {
				respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data (not String)"));
				return;
			}
			Order[] oa = VisitorRequestHandler.handleShowReservationsRequest((String)msg.getRequestData());
			respondToClient(client, new Message(RequestType.SHOW_RESERVATIONS, oa));
			return;
			
		case FETCH_PARKS:
			Park[] parks = ParkRequestHandler.getAllParks();
			if (parks == null || parks.length == 0)
				System.out.println("[ServerRequestHandler] - found no parks");
			respondToClient(client, new Message(RequestType.FETCH_PARKS, parks));
			return;
		case UPDATE_PARK_VARIABLE:
			if (!(msg.getRequestData() instanceof Park)) {
				respondToClient(client, new Message(RequestType.FETCH_RESERVATION_DATA, "invalid request data (Expected Park)"));
				return;
			}
			Park p = (Park)msg.getRequestData();
			p.setUpdated(ParkRequestHandler.updateParkVariable(p));
			if (p.isUpdated()) 
				respondToClient(client, new Message(RequestType.UPDATE_PARK_VARIABLE, p, "Successfully update variable"));
			else
				respondToClient(client, new Message(RequestType.UPDATE_PARK_VARIABLE, p, "Failed to update variable"));
			return;
		case SHOW_USAGE_REPORT:
			UsageReport[] listToReturn = UsageReportHandler.getUsageReports();
			respondToClient(client, new Message(RequestType.SHOW_USAGE_REPORT,listToReturn));
			return;
			
		case SHOW_CANCELLATIONS_REPORTS:
			if (!(msg.getRequestData() instanceof String)) {
				respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data (not String)"));
				return;
			}
			CancelledReservation[] listToReturn2 = CancellationsReportHandler.getReservations((String)msg.getRequestData());
			respondToClient(client, new Message(RequestType.SHOW_CANCELLATIONS_REPORTS,listToReturn2,(String)msg.getRequestData()));
			return; 
			
		
		case CHECK_IF_REQ_EXIST:
			if (!(msg.getRequestData() instanceof Report)) {
				respondToClient(client, new Message(RequestType.REQUEST_CHANGE, "invalid request data Report"));
				return;
			}
			r = (Report)msg.getRequestData();	
			respondToClient(client, new Message(RequestType.CHECK_IF_REQ_EXIST,ParkRequestHandler.checkReportRequest(r)));
			return; 
			
		case CONFIRM_RESERVATION:
			if (!(msg.getRequestData() instanceof Order)) {
			    respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data (Expected Order)"));
			    return;
			}
			o = (Order)msg.getRequestData();
			boolean confirmed = VisitorRequestHandler.admitReservation(o);
			o.setIsConfirmed(confirmed);
			respondToClient(client, new Message(RequestType.CONFIRM_RESERVATION, o));
			return;
			
		case UPDATE_REQUEST_CHANGE:
			if (!(msg.getRequestData() instanceof Report)) {
				respondToClient(client, new Message(RequestType.UPDATE_REQUEST_CHANGE, "invalid request data Report"));
				return;
			}
			r = (Report)msg.getRequestData();
			r.setReportExist(ParkRequestHandler.reqToChange2(r));
			respondToClient(client, new Message(RequestType.REQUEST_CHANGE,r.isReportExist()));	
			return;
			
		case REQUEST_CHANGE: 
			if (!(msg.getRequestData() instanceof Report)) {
				respondToClient(client, new Message(RequestType.REQUEST_CHANGE, "invalid request data Report"));
				return;
			}
			boolean i=false;
			r = (Report)msg.getRequestData();
			i=ParkRequestHandler.reqToChange(r);//add it
			respondToClient(client, new Message(RequestType.REQUEST_CHANGE,i));	
			return;
			
			
			//exit from park 	
			case EXIT_VISITOR://for visitor with reservation
				if (!(msg.getRequestData() instanceof String[])) {
					respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data String"));
					return;
				}
				//s[0]  ClientController.connectedUser.getParkName s[1] resevation id
				String[] s2 = (String[])msg.getRequestData();
				o=ReservationRequestHandler.getReservationById(s2,"reservations");
				if(o!=null){	
					//minus to current number 
					//update data in processedres table
					if(o.isProcessed().equals("0"))
					{
						if(ParkRequestHandler.updateCurrentAmoun(o,(-1)*(o.getNumOfVisitors()))) {
							//ParkRequestHandler.updateCurrentAmoun(o.getParkName(),((-1)*o.getNumOfVisitors()))
							if(ReservationRequestHandler.exitProcessed(o.getOrderID())) {
								boolean re =ReservationRequestHandler.updateStatus("reservations",o.getOrderID(),1);//change the processed to 1
								respondToClient(client, new Message(RequestType.EXIT_VISITOR,re));
								return;
						}
						respondToClient(client, new Message(RequestType.EXIT_VISITOR,false));
						return;
						}
					}
					else
					{
						respondToClient(client, new Message(RequestType.EXIT_VISITOR,false));
						return;
					}			
				}
				respondToClient(client, new Message(RequestType.EXIT_VISITOR,false));
				return;
				//entry worker enter the reservation id
			case ENTER_VISTOR://for visitor with reservation
				if (!(msg.getRequestData() instanceof String[])) {
					respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data String"));
					return;
				}////s[0]  ClientController.connectedUser.getParkName s[1] reservation id
				String[] s3 = (String[])msg.getRequestData();
				Order o1=ReservationRequestHandler.getReservationById(s3,"reservations");
				if(o1!=null)
				{//-1 not used 0 in the park 1 exit
					if(o1.isProcessed().equals("-1")) {//to be sure that no entered before
						Order o2=ReservationRequestHandler.getReservationById(s3,"processedres");
						if(o2!=null) {//if the reservation allready entered
							respondToClient(client, new Message(RequestType.ENTER_VISTOR,false));
							return;
						}
							if(ReservationRequestHandler.enterProcessed(o1))//add to processedres
							{	//add to current number and delete from reservation
								if(ParkRequestHandler.updateCurrentAmoun(o1,o1.getNumOfVisitors())) {
									
									//if the park is full after entering new visitors, create a full park instance
									if (ParkRequestHandler.parkReachedMaxCapacity(o1.getParkName()))
										ParkRequestHandler.createFullParkInstance(o1);
								boolean re =ReservationRequestHandler.updateStatus("reservations",o1.getOrderID(),0);
								respondToClient(client, new Message(RequestType.ENTER_VISTOR,re));
								return;
							}
							respondToClient(client, new Message(RequestType.ENTER_VISTOR,false));
							return;
						}	
					}
					respondToClient(client, new Message(RequestType.ENTER_VISTOR,false));
					return;
				}		
				else
					respondToClient(client, new Message(RequestType.ENTER_VISTOR,false));
				return;
	/////////////////////////////				//////////////////////////////////////////////////////////////
				case UPDATE_RESERVATION:
					if (!(msg.getRequestData() instanceof Order)) {
						respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data (not String)"));
						return;
					}
					Order uo = VisitorRequestHandler.handleUpdateReservationRequest((Order)msg.getRequestData());
					respondToClient(client, new Message(RequestType.UPDATE_RESERVATION, uo));
					return;
					
					
				case SHOW_NUM_OF_VISITORS_REPORT:
					if (!(msg.getRequestData() instanceof String[])) {
						respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data (not Strings list)"));
						return;
					}
					//Number of organizedGroup and individuals
					String[] listToReturn3 = NumOfVisitorsReportHandler.getNumOfVisitors((String[])msg.getRequestData());
					respondToClient(client, new Message(RequestType.SHOW_NUM_OF_VISITORS_REPORT,listToReturn3));
				return;
		case CANCEL_RESERVATION:
			if (!(msg.getRequestData() instanceof Order)) {
			    respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data (Expected Order)"));
			    return;
			}
			o = (Order) msg.getRequestData();
			boolean canceled = ReservationRequestHandler.deleteReservation("reservations",o.getOrderID());
			if (canceled)
				InboxRequestHandler.addMessage(o.getVisitorID(), new InboxMessage("Order Cancellation", "Your order #"+o.getOrderID() + " has been canceled."));
			boolean addedToCanceled = ReservationRequestHandler.addToCanceledReports(o);
			WaitingListRequestHandler.checkWaitingListForAdmittableOrder(o.getParkName());
			o.setCanceled(addedToCanceled && canceled);
			respondToClient(client, new Message(RequestType.CANCEL_RESERVATION, o));
			return;
		case LOGOUT:
			if (!(msg.getRequestData() instanceof String)) {
			    respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data (Expected String)"));
			    return;
			}
			ClientConnectionHandler.handleLogout((String) msg.getRequestData());
			generalRespondMsg = "Logout has finished successfully";
			break;
		case SHOW_EDITED_VARIABLES:
			if (!(msg.getRequestData() instanceof String)) {
				respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data (not String)"));
				return;
			}
			// key is the variable to edit with the new value
			HashMap<String, String> hashMapWithEdits = EditedVariablesRequestHandler.getEditedVariables((String)msg.getRequestData());
			respondToClient(client, new Message(RequestType.SHOW_EDITED_VARIABLES,hashMapWithEdits));
			return;
		case DELETE_REQUEST_CHANGE:
			if (!(msg.getRequestData() instanceof String[])) {
				respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data (not String list)"));
				return;
			}			
			respondToClient(client, new Message(RequestType.SHOW_EDITED_VARIABLES,
			DeletedRequestChangeHandler.deleteData((String[])msg.getRequestData())));
			return ;
		case CANCELLATIONS_GRAPH_DATA:
			if (!(msg.getRequestData() instanceof String[])) {
				respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data (not String list)"));
				return;
			}
			int[] sendNumsRequested = CancellationsGraphDataHandler.getDataForGraph((String[])(msg.getRequestData()));
			respondToClient(client, new Message(RequestType.CANCELLATIONS_GRAPH_DATA,sendNumsRequested));
			return;
		case ENTER_WAITING_LIST://insert to waiting list
				if (!(msg.getRequestData() instanceof Order)) {
						respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data (not String list)"));
						return;
				}
				o = (Order) msg.getRequestData();
				o=VisitorRequestHandler.handleMakeReservationRequest(o,"waiting_list");
				respondToClient(client, new Message(RequestType.ENTER_WAITING_LIST, o));
				return;
				
		case FETCH_INBOX:
			if (!(msg.getRequestData() instanceof String)) {
				respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data (expected String)"));
				return;
			}
			String id = (String)msg.getRequestData();
			InboxMessage[] msgs = InboxRequestHandler.getAllMessages(id);
			respondToClient(client, new Message(RequestType.FETCH_INBOX, msgs));
			return;
		case DELETE_MSG:
			if (!(msg.getRequestData() instanceof InboxMessage)) {
				respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data (expected InboxMessage)"));
				return;
			}
			InboxMessage inboxMsg = (InboxMessage)msg.getRequestData();			
			result = InboxRequestHandler.deleteMsg(inboxMsg.getId());
			inboxMsg.setDeleted(result);
			respondToClient(client, new Message(RequestType.DELETE_MSG, inboxMsg));
			return;
		case VISITS_GRAPH_DATA:
			if (!(msg.getRequestData() instanceof String[])) {
				respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data (not String list)"));
				return;
			}
			VisitsReport[] dataToReturn = VisitsReportGraphHandler.getVisitsDataForGraph((String[])(msg.getRequestData()));
			respondToClient(client, new Message(RequestType.VISITS_GRAPH_DATA,dataToReturn));
			return;	
			//get park
		case REQUEST_PARK:
			if (!(msg.getRequestData() instanceof String)) {
				respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data (not String )"));
				return;
			}	
			p=ParkRequestHandler.getParkData((String)msg.getRequestData());
			respondToClient(client, new Message(RequestType.REQUEST_PARK,p));
			return;	
		case ORDER_ID:
			if (!(msg.getRequestData() instanceof String)) {
				respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data (not String )"));
				return;
			}
			 int orderId=ReservationRequestHandler.OrderId((String)msg.getRequestData());
			respondToClient(client, new Message(RequestType.ORDER_ID,orderId));
			return;	
			
		case MAKE_RESERVATION_ENTRY:
			if (!(msg.getRequestData() instanceof Order)) {
				respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data (not Order)"));
				return;
			}
			Order receivedOrder1 = (Order)msg.getRequestData();
			Order o11 = VisitorRequestHandler.handleMakeReservationRequest(receivedOrder1,"reservations");
			respondToClient(client, new Message(RequestType.MAKE_RESERVATION_ENTRY, o11));
			return;
		case CHECK_INSTRUCTOR:
			if (!(msg.getRequestData() instanceof String)) {
				respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data (not Order)"));
				return;
			}
			id=(String)msg.getRequestData();
			boolean z=UserRequestHandler.isInstructor(id);
			respondToClient(client, new Message(RequestType.CHECK_INSTRUCTOR, z));
			return;
		case FETCH_NOT_FULL_DATA:
			if (!(msg.getRequestData() instanceof Report)) {
				respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data (expected Report)"));
				return;
			}
			r = (Report)msg.getRequestData();
			int amountOfFullDays = ReportRequestHandler.getFullDays(r);
			result = ReportRequestHandler.notFullReportExist(r);
			r.setReportExist(result);
			r.setAmountOfFullDays(amountOfFullDays);
			respondToClient(client, new Message(RequestType.FETCH_NOT_FULL_DATA, r));
			return;
		default:
			respondToClient(client, new Message(RequestType.UNIMPLEMENTED_RESPOND, "response type is not implemented"));
			return;			
		}
		 
		respondToClient(client, new Message(RequestType.GENERAL_RESPOND, generalRespondMsg));
	}
	
	public static void respondToClient(ConnectionToClient client, Message msg) {
		try {
			client.sendToClient(msg);
		} catch (IOException e) {
			System.out.println("[ServerRequestHandler] - Error responding to client");
			e.printStackTrace();
		}
	}
	
}
