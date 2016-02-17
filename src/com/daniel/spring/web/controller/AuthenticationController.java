package com.daniel.spring.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.daniel.spring.web.model.User;
import com.daniel.spring.web.service.UserService;

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
	public String handleCreateAccount(@Valid User user, BindingResult result, Model m) {
		
		if(result.hasErrors()) {
			return "create-account";
		}
		
		userService.add(user);
		return "create-account";
	}
	
}
