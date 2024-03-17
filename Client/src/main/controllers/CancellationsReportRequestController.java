package main.controllers;


import java.util.ArrayList;

import main.ClientController;
import requests.Message;
import requests.RequestType;

public class CancellationsReportRequestController {
	public static void sendShowCancellationsReport(ArrayList<String[]> list,String condition) {
		ClientController.getController().accept(new Message(RequestType.SHOW_CANCELLATIONS_REPORTS,list,condition));
	}

}
