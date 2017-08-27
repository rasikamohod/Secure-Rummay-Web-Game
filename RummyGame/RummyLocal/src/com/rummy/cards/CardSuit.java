package com.rummy.cards;

/**
 * CardSuit provides the suits of a deck of cards
 */
public enum CardSuit {
	CLUB("C"), DIAMOND("D"), HEART("H"), SPADE("S");
	
	private String id;
	
	CardSuit(String id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name();
	}
	
	@Override
	public String toString() {
		return id;
	}
}

