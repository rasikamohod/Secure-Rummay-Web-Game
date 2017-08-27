package com.rummy.controller;

import java.util.List;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rummy.cards.Card;
import com.rummy.dao.RummyDAO;
import com.rummy.dao.Player;

@Controller
public class DashboardController {
	
	@Autowired
	private RummyDAO rummyDAO;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView showHome() {
		
		ModelAndView homeView = new ModelAndView("Home");
		Player player = TestData.getPlayerOne();
		homeView.addObject("player", player);
		homeView.addObject("stats", rummyDAO.getAllStats());
		homeView.addObject("avail", rummyDAO.getAvailableGame());
		homeView.addObject("audits", rummyDAO.getAudit());
		homeView.addObject("complete", rummyDAO.getCompleteIDs());
		return homeView;
	}
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView showGame() {
		int round = 1;
		List<List<Card>> melds = TestData.getMeld();
		Stack<Card> stockpile = TestData.getStockPile();
		Stack<Card>  discardpile = TestData.getDiscarePile();
		Player player = TestData.getPlayerOne();
		ModelAndView playerView = new ModelAndView("PlayerView");
		playerView.addObject("round", round);
		playerView.addObject("melds", melds);
		playerView.addObject("stockpile", stockpile);
		playerView.addObject("discardpile", discardpile);
		playerView.addObject("player", player);	
		return playerView;
	}

}
