package com.daniel.spring.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.daniel.spring.web.model.User;
import com.daniel.spring.web.service.UserService;
import com.daniel.spring.web.validaton.groups.CreateAccountForm;

@Controller
public class AuthenticationController {
	
	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "login";
	}
		
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String createAccount(Model m) {
		m.addAttribute("user", new User());
		return "create-account";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String handleCreateAccount(@Validated(value=CreateAccountForm.class) User user, BindingResult result, Model m) {
		
		// Standard field validation
		if(result.hasErrors()) {
			return "create-account";
		}
				
		// Check if the username exists already
		if(userService.getById(user.getUsername()) != null) {
			result.rejectValue("username", "DuplicateKey.user.username");
			return "create-account";
		}
		
		// Safe to add
		userService.addAdmin(user);

		return "create-account";
	}
	
}
