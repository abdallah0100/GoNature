package requests;

import java.io.Serializable;

/**
 * Represents a message used in our client-server system. This class encapsulates the type of request,
 * the data associated with the request, and an optional response message. It implements the Serializable
 * interface to allow instances of this class to be serialized for network communication or file storage.
 */
public class Message implements Serializable{

	private static final long serialVersionUID = 1L;
	private int requestType;
	private Object requestData;
	private String responseMsg;
	
	/**
	 * Constructs a new Message with the specified request type and request data.
	 * 
	 * @param requestType The type of the request.
	 * @param requestData The data associated with the request.
	 */
	public Message(RequestType requestType, Object requestData) {
		this.requestType = requestType.getRequestId();
		this.requestData = requestData;
	}
	/**
	 * Constructs a new Message with the specified request type, request data, and response message.
	 * 
	 * @param requestType The type of the request.
	 * @param requestData The data associated with the request.
	 * @param responseMsg The response message associated with the request.
	 */
	public Message(RequestType requestType, Object requestData, String responseMsg) {
		this.requestType = requestType.getRequestId();
		this.requestData = requestData;
		this.responseMsg = responseMsg;
	}
	
	/**
	 * Constructs a new Message with the specified request type. The request data is set to a default
	 * string indicating that no data is present.
	 * 
	 * @param requestType The type of the request, not null.
	 */
	public Message(RequestType requestType) {
		this.requestType = requestType.getRequestId();
		this.requestData = "Request has no data";
	}
	
	/**
	 * @return The request type as a RequestType enum.
	 */
	public RequestType getRequestEnumType() {
		return RequestType.getTypeById(requestType);
	}
	
	/**
	 * @return  The request data.
	 */
	public Object getRequestData() {
		return requestData;
	}
	/**
	 * Sets the request data for this message.
	 * @param data  The new request data.
	 */
	public void setRequestData(Object data) {
		this.requestData = data;
	}
	
	/**
	 * @return A string representation of this message.
	 */
	@Override
	public String toString() {
		if (requestData != null)
			return "requestType: " + requestType + " requestData: " + requestData.toString();
		return "requestType: " + requestType ;
	}

	/**
	 * @return The response message.
	 */
	public String getResponseMsg() {
		return responseMsg;
	}

	/**
	 * Sets the response message for this message.
	 * @param responseMsg The new response message.
	 */
	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}
}
