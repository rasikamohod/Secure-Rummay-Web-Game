package com.rummy.authentication;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * Controller to perform registration for users
 * @author Rasika
 *
 */
@Controller
public class RegisterController {
	
	protected final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);
	private final String LOGIN_JSP_VIEW = "Register";
	private final String LOGIN_REDIRECT = "redirect:/loginUser";

	@RequestMapping("/registerUser")
	public String registerUser() {
		LOGGER.info("/registerUser GET method requested");
		return LOGIN_JSP_VIEW;
	}

	@RequestMapping(value = "/registerUserSubmit", method = RequestMethod.POST)
	public String registerUserSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		LOGGER.info("/registerUserSubmit POST method requested");
		
		//Get all required data from request
		String username = request.getParameter("username");
		String useremail = request.getParameter("email");
		String password = request.getParameter("password");
		
		//Store users data - register user in application
		AuthenticationOperations dbOperationsObject = new AuthenticationOperations();

		//Check if user already exists
		boolean userfound = false;
		userfound = dbOperationsObject.checkUser(username);

		if(userfound)
		{
			LOGGER.info(String.format("User %s already exist in database", username));

			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Username already exists! Please register with different username');");
			out.println("location='loginUser';");
			out.println("</script>");
			out.close();
		}
		else
		{	
			//If user does not exist, register the new user
			dbOperationsObject.registerUser(username, useremail, password);
			LOGGER.info(String.format("User %s created in database.", username));
		}

		return LOGIN_REDIRECT;
	}

}
