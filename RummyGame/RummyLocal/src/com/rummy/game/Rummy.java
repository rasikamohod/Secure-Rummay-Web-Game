package com.rummy.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rummy.cards.Card;
import com.rummy.cards.CardRank;
import com.rummy.cards.CardSuit;
import com.rummy.dao.Player;

public class Rummy {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Rummy.class);
	private int MAX_PLAYERS_ALLOWED = 2;
	private long gameID;
	private int round;
	private List<Player> players = new ArrayList<Player>();
	private Stack<Card> cardDeck = new Stack<Card>();
	private Stack<Card> discardPile = new Stack<Card>();
	
	
	/**
	 * Returns the game's ID
	 * @return Game ID
	 */
	public long getGameID() {
		return gameID;
	}
	
	public int getRound() {
		return round;
	}
	
	public void setRound(int round) {
		this.round = round;
	}
	
	/**
	 * Set the game's ID
	 * @param gameID Current game's ID
	 */
	public void setGameID(long gameID) {
		LOGGER.debug(String.format("Game ID set to %s", gameID));
		this.gameID = gameID;
	}
	
	/**
	 * Return list of players
	 * @return List of players
	 */
	public List<Player> getPlayers() {
		return players;
	}
	
	/**
	 * Add player to player's list if not at maximum capacity
	 * @param player Player to add
	 */
	public void addPlayer(Player player) {
		final int TOTAL_PLAYERS = players.size();
		if(TOTAL_PLAYERS <= MAX_PLAYERS_ALLOWED) {
			LOGGER.info(String.format("Adding player %s to game %s", player.getUsername(), gameID));
			players.add(player);
		}
		else {
			LOGGER.error(String.format("Game %s at MAX capacity", gameID));
		}
	}
	
	/**
	 * Returns the deck of cards
	 * @return card deck
	 */
	public Stack<Card> getCardDeck() {
		int deckSize = cardDeck.size();
		if(deckSize == 52) 
			LOGGER.debug("Cards have not be dealt");
		else if(deckSize <= 32)
			LOGGER.debug("Cards have been dealt");
		return cardDeck;
	}
	
	/**
	 * Initializes a new deck of 52 cards
	 * 13 of each suit (CLUB, DIAMOND, HEART, SPADE)
	 */
	public void initializeDeck() {
		LOGGER.debug("Initializing new deck");
		for(CardSuit suit: CardSuit.values())
			for(CardRank rank: CardRank.values())
				cardDeck.add(new Card(rank, suit));
	}
	
	
	
	/**
	 * Returns discard pile
	 * @return Discard pile
	 */
	public Stack<Card> getDiscardPile() {
		return discardPile;
	}
	
	/**
	 * Initializes the discard pile
	 */
	public void initalizeDiscardPile() {
		LOGGER.debug("Discard pile initalized");
		discardPile = new Stack<Card>();
	}
	
	/**
	 * Deal cards to all players
	 */
	public void dealCards() {
		int playerCount = players.size();
		if(playerCount == MAX_PLAYERS_ALLOWED) {
			int deckSize = cardDeck.size();
			if(deckSize == 52) {
				LOGGER.error("Deck must have 52 cards to deal to players");
			}
			else {
				int cardCount = 0;
				switch(playerCount) {
				case 2:
					cardCount = 10;
					break;
				case 3:
				case 4:
					cardCount = 7;
					break;
				case 5:
				case 6:
					cardCount = 6;
					break;
				}
				LOGGER.info(String.format("Dealing %s cards to each of the %s players", cardCount, playerCount));
				
				// Deal to each player
				for(Player player: players) {
					List<Card> playerHand = new ArrayList<Card>();
					for(int i=0; i<cardCount; i++)
						playerHand.add(cardDeck.remove(i));
					player.setCards(playerHand);
				}
			}
		}
		else
			LOGGER.error(String.format("Game has not reached capacity of %s, cannot deal cards", MAX_PLAYERS_ALLOWED)); 
	}
	
	/**
	 * Shuffles the card deck
	 */
	public void shuffleCards() {
		Collections.shuffle(cardDeck);
	}

}
