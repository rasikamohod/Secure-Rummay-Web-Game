package com.rummy.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Audit {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Audit.class);
	
	// Instance variables
	private int auditid;
	private long gameid;
	private int userid;
	private String username;
	private String raction;
	private String rcards;
	private String rstatus;
	private String rmessage;
	
	public Audit() {
		super();
	}
	
	public int getAuditid() {
		return auditid;
	}
	
	public void setAuditid(int auditid) {
		LOGGER.debug(String.format("Setting audit id %s", auditid));
		this.auditid = auditid;
	}
	
	public long getGameid() {
		return gameid;
	}
	
	public void setGameid(long gameid) {
		LOGGER.debug(String.format("Setting game id: %s", gameid));
		this.gameid = gameid;
	}
	
	public int getUserid() {
		return userid;
	}
	
	public void setUserid(int userid) {
		LOGGER.debug(String.format("Setting player id: %s", userid));
		this.userid = userid;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getRaction() {
		return raction;
	}
	
	public void setRaction(String action) {
		LOGGER.debug(String.format("Setting action: %s", action));
		this.raction = action;
	}
	
	public String getRcards() {
		return rcards;
	}
	
	public void setRcards(String cards) {
		LOGGER.debug(String.format("Setting cards: %s", cards));
		this.rcards = cards;
	}
	
	public String getRstatus() {
		return rstatus;
	}
	
	public void setRstatus(String status) {
		LOGGER.debug(String.format("Setting status: %s", status));
		this.rstatus = status;
	}
	
	public String getRmessage() {
		return rmessage;
	}
	
	public void setRmessage(String message) {
		LOGGER.debug(String.format("Setting message: %s", message));
		this.rmessage = message;
	}

}
