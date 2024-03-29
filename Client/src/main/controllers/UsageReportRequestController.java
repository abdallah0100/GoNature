package main.controllers;

import main.ClientController;
import requests.Message;
import requests.RequestType;

/**
 * Controls the requests related to fetching usage reports from the server.
 */
public class UsageReportRequestController {

	/**
	 * Requests the server to send a usage report.
	 */
	public static void sendShowUsageReport() {
		ClientController.getController().accept(new Message(RequestType.SHOW_USAGE_REPORT));
	}
}
