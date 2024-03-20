package requests;

public enum RequestType {
	REQUEST_ERROR(1),
	GENERAL_RESPOND(2),
	UNIMPLEMENTED_RESPOND(3),
	CONNECT_TO_SERVER(4),
	DISCONNECT_FROM_SERVER(5),
	VALIDATE_VISITOR(6),
	LOGIN_USER(7),
	REQUEST_BILL(8),
	INSERT_INSTRUCTOR(9),
	FETCH_RESERVATION_DATA(10),
	CREATE_REPORT(11),
	MAKE_RESERVATION(12),
	SHOW_RESERVATIONS(13),
	FETCH_MONTHLY_VISITOR_NUM(14),
	FETCH_PARKS(15),
	UPDATE_PARK_VARIABLE(16),
	SHOW_USAGE_REPORT(17),
	SHOW_CANCELLATIONS_REPORTS(18),
	CONFIRM_RESERVATION(19),
	UPDATE_RESERVATION(20),
	SHOW_NUM_OF_VISITORS_REPORT(21),
	CANCEL_RESERVATION(22);
	
	private int requestId;
	
	RequestType(int i) {
		this.requestId = i;
	}

	public int getRequestId() {
		return requestId;
	}
	
	public static RequestType getTypeById(int id) {
		for (RequestType t : RequestType.values())
			if (t.getRequestId() == id)
				return t;
		return null;
	}
	
	
}
