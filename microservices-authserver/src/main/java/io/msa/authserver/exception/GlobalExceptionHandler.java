package io.msa.authserver.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceResp = new ExceptionResponse(ex.getMessage(), request.getDescription(false), new Date());
		return new ResponseEntity<>(exceResp, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(UserAlreadyExistException.class)
	public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserAlreadyExistException ex,
			WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), request.getDescription(false),
				new Date());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.FOUND);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleUserNotFoundException1(ResourceNotFoundException ex,
			WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), request.getDescription(false),
				new Date());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleUserNotFoundException2(UsernameNotFoundException ex,
			WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), request.getDescription(false),
				new Date());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

}
