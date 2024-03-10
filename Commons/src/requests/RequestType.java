package requests;

public enum RequestType {
	REQUEST_DATA(1),
	UPDATE(2);
	
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
