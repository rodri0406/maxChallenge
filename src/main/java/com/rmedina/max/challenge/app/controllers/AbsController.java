package com.rmedina.max.challenge.app.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.rmedina.max.challenge.app.exception.NotExistElementException;


public abstract class AbsController {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public Map<String, Map<String, String>> validationError(MethodArgumentNotValidException ex) {
		Map<String, String> map = new HashMap<>();
		Map<String, Map<String, String>> errors = new HashMap<>();
		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			map.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		errors.put("errores", map);
		return errors;
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public Map<String, Map<String, String>> requestParamsError(MissingServletRequestParameterException ex) {
		Map<String, String> map = new HashMap<>();
		Map<String, Map<String, String>> errors = new HashMap<>();

		map.put(ex.getParameterName(), ex.getMessage());

		errors.put("errores", map);
		return errors;
	}

	@ExceptionHandler(BindException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public Map<String, Map<String, String>> validationError(BindException ex) {
		Map<String, String> map = new HashMap<>();
		Map<String, Map<String, String>> errors = new HashMap<>();
		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			map.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		errors.put("errores", map);
		return errors;
	}
	
	@ExceptionHandler(NotExistElementException.class)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public Map<String, Map<String, String>> requestParamsError(NotExistElementException ex) {
		Map<String, String> map = new HashMap<>();
		Map<String, Map<String, String>> errors = new HashMap<>();

		map.put("error", ex.getMessage());

		errors.put("errores", map);
		return errors;
	}
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public Map<String, Map<String, String>> requestParamsError(RuntimeException ex) {
		Map<String, String> map = new HashMap<>();
		Map<String, Map<String, String>> errors = new HashMap<>();

		map.put("error", ex.getMessage());

		errors.put("errores", map);
		return errors;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, Map<String, String>> requestParamsError(Exception ex) {
		Map<String, String> map = new HashMap<>();
		Map<String, Map<String, String>> errors = new HashMap<>();

		map.put("error", "Error inesperado");

		errors.put("errores", map);
		return errors;
	}

}
