package main.controllers;

import main.ClientController;
import requests.Message;
import requests.RequestType;

public class UsageReportRequestController {

	public static void sendShowUsageReport() {
		ClientController.getController().accept(new Message(RequestType.SHOW_USAGE_REPORT));
	}
}
