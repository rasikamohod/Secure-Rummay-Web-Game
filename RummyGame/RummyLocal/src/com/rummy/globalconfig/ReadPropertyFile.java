package com.rummy.globalconfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadPropertyFile {
	protected final Logger LOGGER = LoggerFactory.getLogger(ReadPropertyFile.class);
	
	/**
	 * Method to get the values from property file by passing the property value
	 * @param propertyValue
	 * @return String.
	 */
	public String getCaption(String propertyValue){
		try{
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config.properties");  	  
    		Properties properties = new Properties();

    		properties.load(inputStream);
    		propertyValue = properties.getProperty(propertyValue);
    		
		}catch(IOException e){
			LOGGER.error("Error on getting value of property:"+propertyValue);
		}
		return propertyValue;
	}

}
