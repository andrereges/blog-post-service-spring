package br.com.andre.blog.post.service.shared.handlers;

import br.com.andre.blog.post.exceptions.BadRequestException;
import br.com.andre.blog.post.service.shared.dtos.ApiStandardHandlerErrorResponseDto;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ApiStandardHandlerErrorResponseDto> handleBadRequestException(BadRequestException ex, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
			.body(ApiStandardHandlerErrorResponseDto.builder()
				.code(1)
				.error("Resource not found")
				.message(ex.getMessage())
				.path(request.getRequestURI())
				.build()
			);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiStandardHandlerErrorResponseDto> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
		List<String> errors = new ArrayList<>();

		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}

		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
			.body(ApiStandardHandlerErrorResponseDto.builder()
				.code(2)
				.error("Invalid request arguments")
				.message(errors)
				.path(request.getRequestURI())
				.build()
			);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ApiStandardHandlerErrorResponseDto> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
			.body(ApiStandardHandlerErrorResponseDto.builder()
				.code(3)
				.error("Argument type mismatch")
				.message(ex.getName() + " should be of type " + ex.getRequiredType().getName())
				.path(request.getRequestURI())
				.build()
			);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ApiStandardHandlerErrorResponseDto> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
			.body(ApiStandardHandlerErrorResponseDto.builder()
				.code(4)
				.error("Missing Servlet Request Parameter")
				.message(ex.getParameterName() + " parameter is missing")
				.path(request.getRequestURI())
				.build()
			);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ApiStandardHandlerErrorResponseDto> handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest request) {

		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
			.body(ApiStandardHandlerErrorResponseDto.builder()
				.code(5)
				.error("Constraint Violation")
				.message(ex.getMessage())
				.path(request.getRequestURI())
				.build()
			);
	}

	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<ApiStandardHandlerErrorResponseDto> handleInvalidFormatException(InvalidFormatException ex, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
			.body(ApiStandardHandlerErrorResponseDto.builder()
				.code(6)
				.error("Invalid format parameter")
				.message(ex.getMessage())
				.path(request.getRequestURI())
				.build()
			);
	}

	@ExceptionHandler({DataIntegrityViolationException.class})
	public ResponseEntity<ApiStandardHandlerErrorResponseDto> handleDataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.CONFLICT.value())
			.body(ApiStandardHandlerErrorResponseDto.builder()
				.code(7)
				.error("Data Integrity Violation")
				.message(ex.getMessage())
				.path(request.getRequestURI())
				.build()
			);
	}
	
}
