package es.ajrj.cc.price.exception.handler;

import java.time.LocalDateTime;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import es.ajrj.cc.price.exception.ResourceNotFoundException;
import es.ajrj.cc.price.exception.dto.ErrorMessageDTO;
import jakarta.validation.ConstraintViolationException;

/**
 * Rest Controller Advice’s methods (annotated with @ExceptionHandler) are
 * shared globally across multiple @Controller components to capture exceptions
 * and translate them to HTTP responses.
 * 
 * @author ajrj
 *
 */

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ControllerExceptionHandler {

	/**
	 * Default exception handler for ResourceNotFoundException
	 * 
	 * @param ex      exception caused
	 * @param request general request metadata
	 * @return data transfer object of type ErrorMessage
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorMessageDTO resourceNotFoundExceptionHandler(ResourceNotFoundException ex, WebRequest request) {
		return ErrorMessageDTO.builder().code(HttpStatus.NOT_FOUND.value()).date(LocalDateTime.now())
				.message(ex.getMessage()).path(((ServletWebRequest) request).getRequest().getRequestURI()).build();
	}

	/**
	 * Default exception handler for MissingServletRequestParameterException
	 * 
	 * @param ex      exception caused
	 * @param request general request metadata
	 * @return data transfer object of type ErrorMessage
	 */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessageDTO missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException ex,
			WebRequest request) {
		return ErrorMessageDTO.builder().code(HttpStatus.BAD_REQUEST.value()).date(LocalDateTime.now())
				.message(ex.getMessage()).path(((ServletWebRequest) request).getRequest().getRequestURI()).build();
	}

	/**
	 * Default exception handler for ConstraintViolationException and
	 * MethodArgumentTypeMismatchException
	 * 
	 * @param ex      exception caused
	 * @param request general request metadata
	 * @return data transfer object of type ErrorMessage
	 */
	@ExceptionHandler({ ConstraintViolationException.class, MethodArgumentTypeMismatchException.class })
	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	public ErrorMessageDTO constraintViolationExceptionExceptionHandler(RuntimeException ex, WebRequest request) {
		return ErrorMessageDTO.builder().code(HttpStatus.UNPROCESSABLE_ENTITY.value()).date(LocalDateTime.now())
				.message(ex.getMessage()).path(((ServletWebRequest) request).getRequest().getRequestURI()).build();
	}

}
