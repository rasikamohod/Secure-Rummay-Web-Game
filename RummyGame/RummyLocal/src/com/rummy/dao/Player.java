package com.rummy.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rummy.cards.Card;
import com.rummy.messages.PlayerMessage;

public class Player
{	
	private static final Logger LOGGER = LoggerFactory.getLogger(Player.class);
	
	// Instance variables
	private long id;
	private String username;
	private String email;
	private int score;
	private int bank;
	private int wager;
	private List<Card> cards;
	
	public Player() {
		super();
	}
	
	/**
	 * Return player's id	
	 * @return player's id
	 */
	public long getID() {
		return id;
	}
	
	/**
	 * Set player's id
	 * @param id ID to set
	 */
	public void setID(long id) {
		LOGGER.debug(String.format("Setting ID to %s", id));
		this.id = id;
	}
	
	/**
	 * Return player's username
	 * @return username Player's username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Set player's username
	 * @param username Username to set
	 */
	public void setUsername(String username) {
		LOGGER.debug(String.format("Setting username to %s", username));
		this.username = username;
	}
	
	/**
	 * Returns the player's email address
	 * @return Email address
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Set the player's email address
	 * @param email
	 */
	public void setEmail(String email) {
		LOGGER.debug(String.format("Setting email to %s", email));
		this.email = email;
	}
	
	/**
	 * Returns the player's score
	 * @return Player's score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Sets the player's score
	 * @param score Player's score
	 */
	public void setScore(int score) {
		LOGGER.debug(String.format("Setting score to %s", score));
		this.score = score;
	}
	
	/**
	 * Return player's bank
	 * @return bank Player's bank
	 */
	public int getBank() {
		return bank;
	}
	
	/**
	 * Set the player's bank 
	 * @param bank Player's bank
	 */
	public void setBank(int bank) {
		LOGGER.debug(String.format("Setting bank to %s", bank));
		this.bank = bank;
	}
	
	/**
	 * Returns the cards in the playery's hand
	 * @return Player's cads
	 */
	public List<Card> getCards() {
		return cards;
	}
	
	/**
	 * Set the player's cards
	 * @param cards Player's cards
	 */
	public void setCards(List<Card> cards) {
		LOGGER.debug(String.format("Setting player's hand to %s", cards));
		this.cards = cards;
	}
	
	/**
	 * Returns the player's wager
	 * @return Player's wager
	 */
	public int getWager() {
		return wager;
	}
	
	/**
	 * Set the players bet if amount <= bankTotal, bankTotal
	 * remains the same otherwise.
	 * @param wager Betting amount
	 */
	public void setWager(int wager) {
		LOGGER.debug(String.format("Setting wager to %s", wager));
		this.wager = wager;
		System.out.println(PlayerMessage.setWager(username, wager));
		if(bank >= wager) { 
			bank -= wager;
			System.out.println(PlayerMessage.newBankTotal(username, bank));
		}
		else
			LOGGER.error(PlayerMessage.invalidWager(username, bank, wager));
	}	
}
