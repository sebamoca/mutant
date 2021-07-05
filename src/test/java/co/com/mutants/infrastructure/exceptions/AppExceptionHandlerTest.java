package co.com.mutants.infrastructure.exceptions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import co.com.mutants.domain.exceptions.BusinessError;

public class AppExceptionHandlerTest {

	private AppExceptionHandler appExceptionHandler = new AppExceptionHandler();

	@Test
	public void testExceptionError() {
		ResponseEntity<ErrorInfo> error = appExceptionHandler.handleException(new Exception());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, error.getStatusCode());
	}

	@Test
	public void testBusinessError() {
		ResponseEntity<ErrorInfo> error = appExceptionHandler
				.handleErrorNegocio(new BusinessError("Character sequences can only contain letters: [ATCG]"));
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
	}

	@Test
	public void testRequestError() {
		BeanPropertyBindingResult result = new BeanPropertyBindingResult(new String(), "The array dna cannot be empty");

		ResponseEntity<ErrorInfo> error = appExceptionHandler
				.handleMethodArgumentNotValidException(new MethodArgumentNotValidException(null, result));
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
	}
}
