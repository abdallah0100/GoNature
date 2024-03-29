package main.controllers;

import main.ClientController;
import requests.Message;
import requests.RequestType;

/**
 * Manages requests for cancellations reports from the server.
 */
public class CancellationsReportRequestController {
	/**
	 * Requests cancellation report data with a specified filter.
	 * @param condition Filter applied to the cancellations report.
	 */
	public static void sendShowCancellationsReport(String condition) {
		ClientController.getController().accept(new Message(RequestType.SHOW_CANCELLATIONS_REPORTS,condition));
	}

}
