package com.rummy.controller;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import com.rummy.cards.Card;
import com.rummy.cards.CardRank;
import com.rummy.cards.CardSuit;
import com.rummy.dao.Player;

public class TestData {
	
	public static Player getPlayerOne() {
		Player player = new Player();
		player.setUsername("player1");
		player.setEmail("player1@example.com");
		player.setID(1);
		List<Card> hand = Arrays.asList(new Card(CardRank.JACK, CardSuit.SPADE), 
				new Card(CardRank.QUEEN, CardSuit.SPADE), new Card(CardRank.THREE, CardSuit.SPADE), 
				new Card(CardRank.FOUR, CardSuit.SPADE), new Card(CardRank.SEVEN, CardSuit.SPADE),
				new Card(CardRank.NINE, CardSuit.SPADE));
		player.setCards(hand);
		return player;
	}
	
	public static Player getPlayerTwo() {
		Player player = new Player();
		player.setUsername("player2");
		player.setEmail("player2@example.com");
		player.setID(2);
		return player;
	}
	
	public static Stack<Card> getStockPile() {
		Stack<Card> stockPile = new Stack<Card>();
		stockPile.push(new Card(CardRank.EIGHT, CardSuit.SPADE));
		stockPile.push(new Card(CardRank.ACE, CardSuit.SPADE));
		stockPile.push(new Card(CardRank.TWO, CardSuit.SPADE));
		stockPile.push(new Card(CardRank.FIVE, CardSuit.SPADE));
		stockPile.push(new Card(CardRank.SIX, CardSuit.SPADE));
		stockPile.push(new Card(CardRank.TEN, CardSuit.SPADE));
		return stockPile;
	}
	
	public static Stack<Card> getDiscarePile() {
		Stack<Card> discardPile = new Stack<Card>();
		discardPile.push(new Card(CardRank.KING, CardSuit.SPADE));
		return discardPile;
	}
	
	public static int getRound() {
		return 2;
	}
	
	public static List<List<Card>> getMeld() {
		List<Card> meldOne = Arrays.asList(new Card(CardRank.QUEEN, CardSuit.HEART), new Card(CardRank.QUEEN, CardSuit.CLUB), new Card(CardRank.QUEEN, CardSuit.DIAMOND));
		List<Card> meldTwo = Arrays.asList(new Card(CardRank.JACK, CardSuit.HEART), new Card(CardRank.JACK, CardSuit.CLUB), new Card(CardRank.JACK, CardSuit.DIAMOND));
		return Arrays.asList(meldOne, meldTwo);
	}

}
