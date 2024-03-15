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
	CREATE_REPORT(11)
	;
	
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
