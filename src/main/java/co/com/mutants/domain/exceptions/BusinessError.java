package co.com.mutants.domain.exceptions;

public class BusinessError extends ExceptionBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3615730023621341812L;

	public BusinessError(String message) {
		super(message, new Throwable());
	}

}
