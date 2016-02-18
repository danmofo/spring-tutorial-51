package com.daniel.spring.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/admin/")
@Controller
public class AdminController {

	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home() {
		return "/admin";
	}
}
