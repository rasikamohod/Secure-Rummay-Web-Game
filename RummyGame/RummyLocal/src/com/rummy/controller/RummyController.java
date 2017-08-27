package com.rummy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RummyController {
	
	@RequestMapping(value = "/drawStock", method = RequestMethod.POST)
	public String drawStock(Model model) {
		
		
		
		
		return "redirect:/PlayerView";
	}
	
	@RequestMapping(value = "/drawDiscard", method = RequestMethod.POST)
	public String drawDiscard(Model model) {
		return "";
	}
	
	@RequestMapping(value = "/layCard", method = RequestMethod.POST)
	public String layCard(Model model) {
		return "";
	}
	
	@RequestMapping(value = "/createMeld", method = RequestMethod.POST)
	public String createMeld(Model model) {
		return "";
	}

}
