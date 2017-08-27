package com.rummy.authentication;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rummy.dbutilities.DataService;

/**
 * Object create login controller for user authentication
 * @author Rasika Mohod
 *
 */
@Controller
public class LoginController {
	
	protected final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	private final String LOGIN_VIEW = "Login";
	private final String DASHBOARD_REDIRECT = "/home";
	
	/**
	 * Redirects jsp/Login&Register.jsp if method used to retrieve
	 * "/loginUser" is GET
	 * @return 
	 */
	@RequestMapping("/loginUser")
	public String loginUser() {
		LOGGER.info("/loginUser GET method requested");
		return LOGIN_VIEW;
	}
	
	/**
	 * Authenticates user if username is found and password is correct.
	 * If authentication is successful redirects user to the Rummy's game 
	 * dash board, otherwise redirects back to "/loginUser"
	 * @param request Servlet Request
	 * @param response Servlet Response
	 * @return Redirection to Spring MVC
	 * @throws IOException
	 * @throws SQLException 
	 */
	@RequestMapping(value = "/loginUserSubmit", method = RequestMethod.POST)
	public String loginUserSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		LOGGER.info("/loginUserSubmit POST method requested");
		
		//Get all required data from request
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		AuthenticationOperations dbOperationsObject = new AuthenticationOperations();
		boolean userfound = false;
		boolean uservalid = false;
		boolean userlocked = false;
		
		//Check of user exists
		userfound = dbOperationsObject.checkUser(username);
		if(userfound)
		{
			LOGGER.info(String.format("User %s exist in database", username));
			
			//Check the user lock status - number of login tries should not exceed 3
			userlocked = dbOperationsObject.userLockStatus(username);
			
			if(!userlocked)
			{
				//If user exists, then perform the validation
				uservalid = dbOperationsObject.validateUser(username, password);
				if(uservalid)
				{
					//Successful Login
					LOGGER.info(String.format("User %s login successful.", username));
					
					//Create a session for user
					HttpSession session=request.getSession();
					session.setAttribute("username", username);
					
					//Set maximum inactive/idle time allowed to user
					session.setMaxInactiveInterval(2*60);
					
					//Redirect user to game page
					return "redirect:"+ DASHBOARD_REDIRECT;
				}
				else
				{
					LOGGER.info(String.format("User %s login unsuccessful.", username));	
					
					PrintWriter out = response.getWriter();
					out.println("<script type=\"text/javascript\">");
					out.println("alert('User or password incorrect');");
					out.println("location='loginUser';");
					out.println("</script>");
					out.close();
					return LOGIN_VIEW;
				}
			}
			else
			{
				LOGGER.info(String.format("User %s locked.", username));	
				
				PrintWriter out = response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('User account locked as number of login attempts exceeded 3.');");
				out.println("location='loginUser';");
				out.println("</script>");
				out.close();
				return LOGIN_VIEW;
			}
		}
		else
		{
			//If user does not exist, alert the user on screen
			LOGGER.info(String.format("User %s does not exist.", username));

			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('User does not exist! Please register.');");
			out.println("location='loginUser';");
			out.println("</script>");
			out.close();
			return LOGIN_VIEW;
		}
		//return String.format("redirect:%s", view);
	}

}
