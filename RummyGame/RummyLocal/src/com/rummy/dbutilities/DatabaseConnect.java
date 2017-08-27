package com.rummy.dbutilities;

import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.Connection;
import com.rummy.authentication.RegisterController;
import com.rummy.globalconfig.ReadPropertyFile;
/**
 * A singleton class to establish database connection 
 * @author Rasika
 *
 */
public class DatabaseConnect {

	public Connection con;
	public static DatabaseConnect db;
	static ReadPropertyFile RP = new ReadPropertyFile();
	protected final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);
	
	private DatabaseConnect() {
		
		String url= RP.getCaption("url");
		String dbName = RP.getCaption("databasename");
		String driver = RP.getCaption("databaseDriver");
		String userName = RP.getCaption("username");
		String password = RP.getCaption("password");
		
		try {
			Class.forName(driver).newInstance();
			this.con = (Connection)DriverManager.getConnection(url+dbName,userName,password);
			if(con!=null)
				LOGGER.info("Database connection successful.");
			else
				LOGGER.info("Database connection not successful.");
		}
		catch (Exception sqle) {
			sqle.printStackTrace();
			LOGGER.error("Database connection exception");
		}
	}
	
	public static synchronized DatabaseConnect getDbCon() {
		if ( db == null ) {
			db = new DatabaseConnect();
		}
		return db;
	}

}
