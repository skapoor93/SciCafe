package springrest.hibernate.util;

public class ApiErrorResponse {

	private String message;
	
	/**
	 * 
	 */
	public ApiErrorResponse() {

	}

	/**
	 * @param message
	 */
	public ApiErrorResponse(String message) {
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
