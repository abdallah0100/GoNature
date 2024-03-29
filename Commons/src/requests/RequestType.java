package requests;

/**
 * Enum representing different types of requests that can be made within the system.
 * Each constant corresponds to a specific action or request type, identified by a unique request ID.
 */
public enum RequestType {
	/** Error in request processing. */
	REQUEST_ERROR(1),
	/** General response to a request. */
	GENERAL_RESPOND(2),
	/** Response for unimplemented features. */
	UNIMPLEMENTED_RESPOND(3),
	/** Establish a connection to the server. */
	CONNECT_TO_SERVER(4),
	/** Disconnect from the server. */
	DISCONNECT_FROM_SERVER(5),
	/** Validate the visitor identity by his ID. */
	VALIDATE_VISITOR(6),
	/** Log in a user into the system. */
	LOGIN_USER(7),
	/** Request billing information or process a billing request. */
	REQUEST_BILL(8),
	/** Register a new instructor in the system. */
	REGIST_INSTRUCTOR(9),
	/** Fetch reservation data for making number of visitors reports. */
	FETCH_RESERVATION_DATA(10),
	/** Create a report based on specific data. */
	CREATE_REPORT(11),
	/** Make a new reservation. */
	MAKE_RESERVATION(12),
	/** Show all reservations for a specific visitor. */
	SHOW_RESERVATIONS(13),
	/** Fetch the monthly number of visitors for reporting or analysis. */
	FETCH_MONTHLY_VISITOR_NUM(14),
	/** Fetch a list parks managed by the system.*/
	FETCH_PARKS(15), 
	/** Update a variable in a specific park. */
	UPDATE_PARK_VARIABLE(16),
	/** Show a usage report for a specific park. */
	SHOW_USAGE_REPORT(17),
	/** Show reports related to reservation cancellations within the park. */
	SHOW_CANCELLATIONS_REPORTS(18),
	/** Request a change in a park variable as a park manager. */
	REQUEST_CHANGE(19),
	/** Update a previously made change request to a park variable. */
	UPDATE_REQUEST_CHANGE(20),
	/** Process an exiting visitor from a park. */
	EXIT_VISITOR(21),
	/** Process an entering visitor into a park. */
	ENTER_VISTOR(22),
	/** Confirm a reservation. */
	CONFIRM_RESERVATION(23),
	/** Update an existing reservation's details like phone number or e-mail. */
	UPDATE_RESERVATION(24),
	/** Show a report on the number of visitors within a specific period in a park. */
	SHOW_NUM_OF_VISITORS_REPORT(25),
	/** Check if a specific request already exists in the system. */
	CHECK_IF_REQ_EXIST(26),
	/** Cancel an existing reservation. */
	CANCEL_RESERVATION(27),
	/** Log out a user from the system. */
	LOGOUT(28),
	/** Show variables that have been edited or modified. */
	SHOW_EDITED_VARIABLES(29),
	/** Delete a request for a change that was previously made but not confirmed. */
	DELETE_REQUEST_CHANGE(30),
	/** Fetch data for generating cancellations graph. */
	CANCELLATIONS_GRAPH_DATA(31),
	/** Enter a visitor into a waiting list for a reservation. */
	ENTER_WAITING_LIST(32),
	/** Get available places for making a reservation. */
	GET_AVAILABLE_PLACES(33),
	/** Fetch data for generating a graph of visits. */
	VISITS_GRAPH_DATA(34),
	/** Request all information of a specific park.*/
	REQUEST_PARK(35),
	/** Gets an order ID from a specific park*/
	ORDER_ID(36),
	/** Make a reservation as an entry worker for a walk in visitor. */
	MAKE_RESERVATION_ENTRY(37),
	/** Check if a visitor is an instructor.*/
	CHECK_INSTRUCTOR(38),
	/** Fetch messages or notifications in the visitor's inbox. */
	FETCH_INBOX(39),
	/** Delete a message from the inbox. */
	DELETE_MSG(40),
	/** Fetch data on the days were the park was not full in a specific park. */
	FETCH_NOT_FULL_DATA(41),
	/** Fetch data of the waiting list for a specific visitor. */
	FETCH_VISITOR_WAITINGLIST(42),
	/** Delete an order from the waiting list. */
	DELETE_FROM_WAITINGLIST(43);
	
	/** The unique identifier for the request type. */
	private int requestId;
	
	/**
	 * Constructor for the enumeration constants.
	 * @param i The unique identifier for the request type.
	 */
	RequestType(int i) {
		this.requestId = i;
	}

	/**
	 * Retrieves the request ID associated with the request type.
	 * @return The request ID.
	 */
	public int getRequestId() {
		return requestId;
	}
	
	/**
	 * Retrieves the request by its ID.
	 * @param id The request ID.
	 * @return The requested type.
	 */
	public static RequestType getTypeById(int id) {
		for (RequestType t : RequestType.values())
			if (t.getRequestId() == id)
				return t;
		return null;
	}
}
