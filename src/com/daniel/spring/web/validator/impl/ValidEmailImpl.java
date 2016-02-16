package com.daniel.spring.web.validator.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.routines.EmailValidator;

import com.daniel.spring.web.validator.ValidEmail;

public class ValidEmailImpl implements ConstraintValidator<ValidEmail, String> {

	private int min;
	
	@Override
	public void initialize(ValidEmail constraintAnnotation) {
		this.min = constraintAnnotation.min();
	}

	@Override
	public boolean isValid(String emailAddress, ConstraintValidatorContext context) {
		
		if(emailAddress.length() < min) {
			return false;
		}
		
		if(!EmailValidator.getInstance().isValid(emailAddress)) {
			return false;
		}
		
		return true;
	}

}
