package com.rummy.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController {
	protected final Logger LOGGER = LoggerFactory.getLogger(LogoutController.class);
	private final String LOGIN_REDIRECT = "redirect/loginUser";
	
	@RequestMapping(value = "/logoutUser")
	public String logoutUser(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("/logoutUser GET method requested");
		HttpSession session = request.getSession();
		session.invalidate();
		return LOGIN_REDIRECT;
	}
	
	@RequestMapping(value = "/logoutUserSubmit", method = RequestMethod.POST)
	public String logoutUserSubmit(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("/logoutUserSubmit POST method requested");
		HttpSession session = request.getSession();
		session.invalidate();
		return LOGIN_REDIRECT;
	}

}
