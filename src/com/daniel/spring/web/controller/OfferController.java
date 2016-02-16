package com.daniel.spring.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.daniel.spring.web.model.Offer;
import com.daniel.spring.web.service.OfferService;

@Controller
@RequestMapping(value="/offers/")
public class OfferController {

	private OfferService offerService;
	
	@Autowired
	public void setOfferService(OfferService offerService) {
		this.offerService = offerService;
	}
	
	@RequestMapping(value="create", method=RequestMethod.GET)
	public String addOffer(Offer offer, Model m) {
		m.addAttribute("offer", new Offer());
		return "create";
	}
	
	@RequestMapping(value="create", method=RequestMethod.POST)
	public String addOfferSubmit(@Valid Offer offer, BindingResult result, Model m) {
		if(result.hasErrors()) {
			return "create";
		}
		
		if(offerService.add(offer)) {
			m.addAttribute("id", offerService.getByName(offer.getName()).getId());
		}

		return "redirect:/offers/view/{id}";
	}

	@RequestMapping(value="view/{id}", method=RequestMethod.GET)
	public String singleOffer(@PathVariable("id") Integer id, Model m) {
		m.addAttribute("offer", offerService.getById(id));
		
		return "single";
	}
	
	@RequestMapping(value="edit/{id}", method=RequestMethod.GET)
	public String editOffer(@PathVariable("id") Integer id, Model m) {
		m.addAttribute("offer", offerService.getById(id));
		
		return "edit";
	}
	
	@RequestMapping(value="edit/{id}", method=RequestMethod.POST)
	public String editOfferSubmit(@Valid Offer offer, BindingResult result, Model m) {
		
		if(result.hasErrors()) {
			return "edit";
		}
		
		offerService.update(offer);
		return "redirect:/offers/view/{id}";
	}
	
	@RequestMapping(value="list", method=RequestMethod.GET)
	public String listOffers(Model m, HttpServletRequest request) {
		
		String limitParameter = request.getParameter("limit");
				
		if(limitParameter != null ) {
			m.addAttribute("offers", offerService.getN(Integer.parseInt(limitParameter)));
		} else {
			m.addAttribute("offers", offerService.getAll());
		}

		return "list";
	}

}
