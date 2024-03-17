package main.controllers;


import java.util.ArrayList;
import main.ClientController;
import requests.Message;
import requests.RequestType;

public class UsageReportRequestController {

	public static void sendShowUsageReport(ArrayList<String[]> list) {
		ClientController.getController().accept(new Message(RequestType.SHOW_USAGE_REPORT,list));
	}
}
