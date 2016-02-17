package com.daniel.spring.web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.daniel.spring.web.service.OfferService;

@Controller
@RequestMapping(value="/")
public class HomeController {
	
	private OfferService offerService;
	
	@Autowired
	public void setOfferService(OfferService offerService) {
		this.offerService = offerService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getHome(Model m) {
		m.addAttribute("offers", offerService.getCurrent());
		return "home";
	}
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String test(Model m) {
		m.addAttribute("offer", offerService.getUnsafe(111111));
		return "test";
	}
	
}
