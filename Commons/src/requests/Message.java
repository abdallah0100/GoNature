package requests;

import java.io.Serializable;

public class Message implements Serializable{

	private static final long serialVersionUID = 1L;

	private int requestType;
	private Object requestData;
	private String responseMsg;
	
	public Message(RequestType requestType, Object requestData) {
		this.requestType = requestType.getRequestId();
		this.requestData = requestData;
	}
	public Message(RequestType requestType, Object requestData, String responseMsg) {
		this.requestType = requestType.getRequestId();
		this.requestData = requestData;
		this.responseMsg = responseMsg;
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
	public void setRequestData(Object data) {
		this.requestData = data;
	}
	
	@Override
	public String toString() {
		if (requestData != null)
			return "requestType: " + requestType + " requestData: " + requestData.toString();
		return "requestType: " + requestType ;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}
}
