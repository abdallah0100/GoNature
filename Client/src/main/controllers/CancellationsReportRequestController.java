package main.controllers;

import main.ClientController;
import requests.Message;
import requests.RequestType;

public class CancellationsReportRequestController {
	public static void sendShowCancellationsReport(String condition) {
		ClientController.getController().accept(new Message(RequestType.SHOW_CANCELLATIONS_REPORTS,condition));
	}

}
