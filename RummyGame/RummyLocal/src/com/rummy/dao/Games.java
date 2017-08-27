package com.rummy.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Games {
	
	private static Logger LOGGER = LoggerFactory.getLogger(Games.class);
	private int id;
	private int gameid;
	private int playerid;
	private String win;
	
	public Games() {
		super();
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		LOGGER.debug(String.format("Setting id: %s", id));
		this.id = id;
	}
	
	public int getGameid() {
		return gameid;
	}
	
	public void setGameid(int gameid) {
		LOGGER.debug(String.format("Setting game ID: %s", gameid));
		this.gameid = gameid;
	}
	
	public int getPlayerID() {
		return playerid;
	}
	
	public void setPlayerID(int playerid) {
		LOGGER.debug(String.format("Setting player ID: %s", playerid));
		this.playerid = playerid;
	}
	
	public String win() {
		return win;
	}
	
	public void setWin(String win) {
		LOGGER.debug(String.format("Setting win status: %s", win));
		this.win = win;
	}
}
