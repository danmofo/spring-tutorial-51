package com.daniel.spring.web.controller;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
	
	private static final String ERROR_VIEW = "test";
	
	@ExceptionHandler(NullPointerException.class)
	public String handleNullPointerException(NullPointerException ex, Model m) {
		m.addAttribute("error", ex.getMessage());
		return ERROR_VIEW;
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public String handleAccessDeniedException(AccessDeniedException ex, Model m) {
		m.addAttribute("error", ex.getMessage());
		return ERROR_VIEW;
	}
}
