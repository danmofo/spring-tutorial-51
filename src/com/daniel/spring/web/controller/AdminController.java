package com.daniel.spring.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
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
		return "/admin/home";
	}
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public String listUsers(Model m) {
		m.addAttribute("users", userService.getAll());
		return "/admin/list-users";
	}
	
	@RequestMapping(value="/users/edit/{username}", method=RequestMethod.GET)
	public String getEditUser(@PathVariable("username") String username, Model m) {
		
		m.addAttribute("user", userService.getById(username));
		
		return "/admin/single-user";
	}
	
	@RequestMapping(value="/users/edit/{username}", method=RequestMethod.POST)
	public String handleEditUser(@PathVariable("username") String username, @Valid User user, BindingResult result, Model m) {
		if(result.hasErrors()) {
			return "/admin/single-user";
		}
		
		String suppliedUsername = user.getUsername();
		boolean usernameChanged = !suppliedUsername.equals(username);
		
		// todo: move this validation into a validator
		if(usernameChanged && userService.getById(user.getUsername()) != null) {
			result.rejectValue("username", "DuplicateKey.user.username");
			return "/admin/single-user";
		}
		
		userService.update(user);
		m.addAttribute("success", true);
		return "/admin/single-user";
	}
}
