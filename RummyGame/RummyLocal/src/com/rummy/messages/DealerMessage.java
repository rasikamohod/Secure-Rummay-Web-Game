package com.rummy.messages;

/**
 * Object provides log messages for the dealer
 * @author Jimmie Hardaway III
 *
 */
public class DealerMessage {
	
	public static final String dealCards(int cardCount, int playerCount) {
		return String.format("%d cards will be dealt to each of the %d players", cardCount, playerCount);
	}
	
	public static final String invalidDeal() {
		return "Card have already been dealt";
	}
	
	public static final String emptyStockPile() {
		return "Stock pile empty, cards have not been dealt";
	}
	
	public static final String emptyDiscardPile() {
		return "Discard pile empty, cards have not been discard";
	}

}
