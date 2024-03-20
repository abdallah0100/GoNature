package main.handlers;
import java.io.IOException;

import entities.Bill;
import entities.CancelledReservation;
import entities.Order;
import entities.Park;
import entities.Report;
import entities.UsageReport;
import entities.User;
import entities.Visitor;
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
			ClientConnectionHandler.handleConnectRequest(client, false);
			generalRespondMsg = "Client has succesfully disconnected from the server";
			break;
		case VALIDATE_VISITOR:
			if (!(msg.getRequestData() instanceof String)) {
				respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data (not String)"));
				return;
			}
			v = VisitorRequestHandler.handleValidateRequest((String) msg.getRequestData());
			respondToClient(client, new Message(RequestType.VALIDATE_VISITOR, v));
			return;
			
		case LOGIN_USER:
			if (!(msg.getRequestData() instanceof User)) {
				respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data (not User)"));
				return;
			}
			u = UserRequestHandler.handleLogInRequest((User) msg.getRequestData());
			respondToClient(client, new Message(RequestType.LOGIN_USER, u));
			return;
		case MAKE_RESERVATION:
			if (!(msg.getRequestData() instanceof Order)) {
				respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data (not Order)"));
				return;
			}
			Order o = VisitorRequestHandler.handleMakeReservationRequest((Order)msg.getRequestData());
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
				result = ReportRequestHandler.updateNumReport(r);
				generalRespondMsg = result ? "Successfully updated report" : "Error updating report";
			}
			else {
				result = ReportRequestHandler.insertNewNumReport(r);
				generalRespondMsg = result ? "Successfully created report" : "Error creating report";
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
			
		case REQUEST_CHANGE: 
			if (!(msg.getRequestData() instanceof String[])) {
				respondToClient(client, new Message(RequestType.REQUEST_CHANGE, "invalid request data String[]"));
				return;
			}
			String res[] = (String[])msg.getRequestData();
			respondToClient(client, new Message(RequestType.REQUEST_CHANGE,ParkRequestHandler.reqToChange(res)));	
			return;
			
		case UPDATE_REQUEST_CHANGE:
			if (!(msg.getRequestData() instanceof String[])) {
				respondToClient(client, new Message(RequestType.UPDATE_REQUEST_CHANGE, "invalid request data String[]"));
				return;
			}
			String res1[] = (String[])msg.getRequestData();
			respondToClient(client, new Message(RequestType.UPDATE_REQUEST_CHANGE,ParkRequestHandler.reqToChange2(res1)));	
			return;	
			
		//exit from park and delete from temp reservatiom	
		case EXIT_VISITOR:
			if (!(msg.getRequestData() instanceof String)) {
				respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data String"));
				return;
			}
			String s2 = (String)msg.getRequestData();
			o=ReservationRequestHandler.getReservationById(s2,"tempreservation");
			if(o!=null)
				{	//minus to current number and delete from tempreservation
					if(ParkRequestHandler.updateCurrentAmoun(o.getParkName(),((-1)*o.getNumOfVisitors()))) {
						boolean re =ReservationRequestHandler.deleteReservation("tempreservation",o.getOrderID());
						respondToClient(client, new Message(RequestType.EXIT_VISITOR,re));
						return;
					}
					respondToClient(client, new Message(RequestType.EXIT_VISITOR,false));
					return;
				}	
			else
				respondToClient(client, new Message(RequestType.EXIT_VISITOR,false));
				return;
			
			//entry worker enter the reservation id
		case ENTER_VISTOR:
			if (!(msg.getRequestData() instanceof String)) {
				respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data String"));
				return;
			}
			String s3 = (String)msg.getRequestData();
			Order o1=ReservationRequestHandler.getReservationById(s3,"reservations");
			if(o1!=null)
			{
				Order o2=ReservationRequestHandler.getReservationById(s3,"tempreservation");
				if(o2!=null) {
					respondToClient(client, new Message(RequestType.ENTER_VISTOR,false));
					return;
				}
					if(ReservationRequestHandler.createTempReservation(o1))//add to temp
					{	//add to current number and delete from reservation
						if(ParkRequestHandler.updateCurrentAmoun(o1.getParkName(),o1.getNumOfVisitors())) {
							boolean re =ReservationRequestHandler.deleteReservation("reservations",o1.getOrderID());
							respondToClient(client, new Message(RequestType.ENTER_VISTOR,re));
							return;
						}
						respondToClient(client, new Message(RequestType.ENTER_VISTOR,false));
						return;
					}	
			}		
			else
				respondToClient(client, new Message(RequestType.ENTER_VISTOR,false));
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
		default:
			respondToClient(client, new Message(RequestType.UNIMPLEMENTED_RESPOND, "response type is not implemented"));
			break;			
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
