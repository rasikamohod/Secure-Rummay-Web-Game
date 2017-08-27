package com.rummy.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Stats {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Stats.class);
	
	private int statid;
	private int userid;
	private String username;
	private int wins;
	private int losses;
	
	public Stats() {
		super();
	}
	
	public int getStatID() {
		return statid;
	}
	
	public void setStatid(int statid) {
		LOGGER.debug(String.format("Setting stat ID: %s", statid));
		this.statid = statid;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getUserid() {
		return userid;
	}
	
	public void setUserid(int userid) {
		LOGGER.debug(String.format("Setting player ID: %s", userid));
		this.userid = userid;
	}
	
	public int getWins() {
		return wins;
	}
	
	public void setWins(int wins) {
		LOGGER.debug(String.format("Setting wins: %s", wins));
		this.wins = wins;
	}
	
	public int getLosses() {
		return losses;
	}
	
	public void setLosses(int losses) {
		LOGGER.debug(String.format("Setting losses: %s", losses));
		this.losses = losses;
	}
}
