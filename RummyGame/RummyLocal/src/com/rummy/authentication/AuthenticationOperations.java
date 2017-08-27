package com.rummy.authentication;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rummy.dbutilities.DataService;
/**
 * Class to perform database operations
 * 
 * @author Rasika
 *
 */
public class AuthenticationOperations {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationOperations.class);

	/**
	 * Method to validate user login
	 * @param username
	 * @param password
	 * @return true if user validated else false
	 * @throws SQLException
	 */
	public boolean validateUser(String username, String password) throws SQLException
	{
		LOGGER.info(String.format("Validating user %s", username));

		try{ 
			int idUsers = 0;
			int numberoftries = 0;
			
			/**
			 * Get User Id of User
			 */
			String sql1 = "SELECT idUsers,numberoftries FROM users WHERE username = '"+ username +"'";
			ResultSet rs1 = DataService.getResultSet(sql1);
			if(rs1.next())
			{
				idUsers = rs1.getInt("idUsers");
				numberoftries = rs1.getInt("numberoftries");
			}

			/**
			 * Get password associated with retrieved User Id
			 */
			String sql2 = "SELECT userpassword FROM password WHERE iduserpassword = '"+ idUsers +"'";
			ResultSet rs2 = DataService.getResultSet(sql2);
			LOGGER.info(String.format("User %s returned ID of %s", username, idUsers));
			if(rs2.next()) {
				String passwordindb = rs2.getString("userpassword");

				//check if original input password matches the stored password
				boolean matched = PasswordEncryption.validatePassword(password, passwordindb);

				if(matched){
					LOGGER.info("User validated.");
					
					//Set the numberoftries count to zero
					numberoftries = 0;
					String sql3 = "UPDATE users SET numberoftries = "+ numberoftries +" WHERE users.username = '"+ username +"'";
					DataService.runQuery(sql3);
					
					return true;
				}
				else{
					LOGGER.info("Invalid User.");
					
					//Increase the numberoftries count
					numberoftries = numberoftries + 1;
					String sql4 = "UPDATE users SET numberoftries = "+ numberoftries +" WHERE users.username = '"+ username +"'";
					DataService.runQuery(sql4);
					
					if(numberoftries == 3)
					{
						int lock = 1;
						String sql5 = "UPDATE password SET locked = "+ lock +" WHERE password.iduserpassword = '"+ idUsers +"'";
						DataService.runQuery(sql5);
					}
					return false;
				}
			}
			else
				return false;

		}
		catch(Exception e){
			LOGGER.error(e.getMessage());
		}  

		return false;
	}
	/**
	 * Method to check if user which is to be registered already exists or not
	 * @param username of user to be registered
	 * @return true is user is already registered else false
	 * @throws SQLException
	 */
	public boolean checkUser(String username) throws SQLException
	{
		try{ 

			// execute insert SQL statement
			String sql = "SELECT username FROM users WHERE username = '"+ username +"'";
			int count = DataService.getRowCount(sql);

			//Check if user exists
			if(count > 0){
				LOGGER.info("User already exists!");
				return true;
			}
			else
				return false;
		}
		catch(Exception e){
			LOGGER.error(e.getMessage());
		}  

		return true;
	}
	/**
	 * Method to register user
	 * @param username
	 * @param useremail
	 * @param password
	 * @throws SQLException
	 */
	public void registerUser(String username, String useremail, String password) throws SQLException
	{
		try{
			/**
			 * Store user details
			 */
			int idUsers = countUsers() + 1;
			//String sql1 = "INSERT INTO users (idUsers,username,useremail) values '"+ idUsers + "''" + username +"''"+ useremail +"'";
			String sql1 = String.format("INSERT INTO users (idUsers,username,useremail) values(%d, \"%s\", \"%s\")", idUsers, username, useremail);
			DataService.runQuery(sql1);

			/**
			 * Encrypt the password and store in database
			 */
			String encryptedPassword = PasswordEncryption.generateStrongPasswordHash(password);
			int locked = 0;
			//String sql2 = "INSERT INTO password (iduserpassword,userpassword,locked) "+ idUsers + "'" + encryptedPassword +"'"+ locked +"";
			String sql2 = String.format("INSERT INTO password (iduserpassword,userpassword,locked) VALUES(%d, \"%s\", \"%s\")", idUsers, encryptedPassword, locked);
			DataService.runQuery(sql2);

			LOGGER.info("User registered successfully.");
		}
		catch(Exception e){
			LOGGER.error(e.getMessage());
		}
	}
	/**
	 * Method to count number of rows in users table
	 * @return count of users
	 * @throws SQLException
	 */
	public int countUsers() throws SQLException
	{
		try{
			String sql = "SELECT idUsers FROM users";
			int count = DataService.getRowCount(sql);

			return count;
		}
		catch(Exception e){
			LOGGER.error(e.getMessage());
		}

		return 0;
	}
	/**
	 * Method to check if user account is locked or not - in case of 3 failed login attempts
	 * @param username
	 * @return
	 */
	public boolean userLockStatus(String username)
	{
		try{
			boolean userlocked = false;
			int numberoftries = 0;

			String sql = "SELECT numberoftries FROM users WHERE username = '"+ username +"'";
			ResultSet rs = DataService.getResultSet(sql);
			if(rs.next())
				numberoftries = rs.getInt("numberoftries");

			if(numberoftries == 3)
				userlocked = true;

			return userlocked;
		}
		catch(Exception e){
			LOGGER.error(e.getMessage());
		}

		return false;
	}
}
