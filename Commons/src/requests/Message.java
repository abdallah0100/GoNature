package requests;

import java.io.Serializable;

public class Message implements Serializable{

	private static final long serialVersionUID = 1L;

	private int requestType;
	private Object requestData;
	
	public Message(RequestType requestType, Object requestData) {
		this.requestType = requestType.getRequestId();
		this.requestData = requestData;
	}
	
	public Message(RequestType requestType) {
		this.requestType = requestType.getRequestId();
		this.requestData = "Request has no data";
	}
	
	public RequestType getRequestEnumType() {
		return RequestType.getTypeById(requestType);
	}
	
	public Object getRequestData() {
		return requestData;
	}
	
	@Override
	public String toString() {
		if (requestData != null)
			return "requestType: " + requestType + " requestData: " + requestData.toString();
		return "requestType: " + requestType ;
	}
}
