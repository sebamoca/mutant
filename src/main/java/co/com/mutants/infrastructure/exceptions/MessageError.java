package co.com.mutants.infrastructure.exceptions;

public enum MessageError {

	DESCONOCIDO("UNCONTROLLED_ERROR"), NEGOCIO("BUSINESS_ERROR"), PETICION("REQUEST_INCORRECT_ERROR");

	private final String description;

	private MessageError(String description) {
		this.description = description;
	}

	public String getMessage() {
		return this.description;
	}
}
