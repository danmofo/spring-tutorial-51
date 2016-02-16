package com.daniel.spring.web.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DatabaseExceptionHandler {
	
	private static final String ERROR_VIEW = "test";
	
	@ExceptionHandler(NullPointerException.class)
	public String handleNullPointerException(NullPointerException ex, Model m) {
		m.addAttribute("error", ex.getMessage());
		return ERROR_VIEW;
	}
	
	@ExceptionHandler(DataAccessException.class)
	public String handleDatabaseException(DataAccessException ex, Model m) {
		m.addAttribute("error", ex.getMessage());
		return ERROR_VIEW;
	}
}
