package com.daniel.spring.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.daniel.spring.web.model.User;
import com.daniel.spring.web.service.UserService;

@RequestMapping("/admin/")
@Controller
public class AdminController {

	@Autowired
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home() {
		return "admin";
	}
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public String listUsers(Model m) {
		m.addAttribute("users", userService.getAll());
		for(User user : userService.getAll()) {
			System.out.println(user);
		}
		return "admin";
	}
}
