package co.com.mutants.domain.exceptions;

public class ExceptionBase extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -142097906495712938L;
	private String message;

	public ExceptionBase(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
