package com.daniel.spring.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	
	@RequestMapping(value="/access-denied", method=RequestMethod.GET)
	public String denied() {
		return "denied";
	}
	
}
