package com.alura.forum.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alura.forum.dto.NullPointerValidationDto;

@RestControllerAdvice
public class ValidationErrorHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<NullPointerValidationDto> handle(MethodArgumentNotValidException exception) {
		
		List<NullPointerValidationDto> errors = new ArrayList<>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		
		for(int i = 0; i < fieldErrors.size(); i++) {
			String message = messageSource.getMessage(fieldErrors.get(i), LocaleContextHolder.getLocale());
			NullPointerValidationDto nullPointerValidationDto = new NullPointerValidationDto(fieldErrors.get(i).getField(), message);
			errors.add(nullPointerValidationDto);
		}
		return errors;

	}
}
