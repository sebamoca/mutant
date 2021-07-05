package co.com.mutants.infrastructure.exceptions;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import co.com.mutants.domain.exceptions.BusinessError;

@ControllerAdvice
public class AppExceptionHandler {

	private static final String ERROR_INTERNO = "Internal Error";

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> handleException(Exception ex) {
		UUID uuid = UUID.randomUUID();
		ErrorInfo error = new ErrorInfo(ERROR_INTERNO, MessageError.DESCONOCIDO.getMessage(), uuid);
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(BusinessError.class)
	public ResponseEntity<ErrorInfo> handleErrorNegocio(BusinessError ex) {
		UUID uuid = UUID.randomUUID();
		ErrorInfo error = new ErrorInfo(ex.getMessage(), MessageError.NEGOCIO.getMessage(), uuid);
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorInfo> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();

		String message = processFieldErrors(fieldErrors);
		UUID uuid = UUID.randomUUID();

		ErrorInfo error = new ErrorInfo(message, MessageError.PETICION.getMessage(), uuid);
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.BAD_REQUEST);
	}

	private String processFieldErrors(List<org.springframework.validation.FieldError> fieldErrors) {
		String message = null;

		for (org.springframework.validation.FieldError fieldError : fieldErrors) {
			message = message != null ? message + " " + fieldError.getDefaultMessage() : fieldError.getDefaultMessage();
		}

		return message;
	}

}
