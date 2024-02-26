package main.client_requests;

import java.util.ArrayList;

import main.ClientUI;
import main.commons.requests.RequestType;

public class RequestHandler {
	
	public static void requestOrderData() {
		ArrayList<String> request = new ArrayList<>();
		String r = RequestType.REQUEST_DATA.getRequestId() + "";
		request.add(r);
		ClientUI.clientController.accept(request);
	}

}
