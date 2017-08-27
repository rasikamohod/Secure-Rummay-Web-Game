package com.rummy.cards;

/**
 * CardRank ENUM provides the rankings of a deck of cards and their
 * total values.
 * @author Jimmie Hardaway III
 */
public enum CardRank {
	
	ACE("A"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"), JACK("J"), QUEEN("Q"), KING("K");
	
	private String id;
	
	CardRank(String id) {
		this.id = id;
	}
	
	/**
	 * Return the value of the card rank
	 * @return card value
	 */
	public String getName() {
		return this.name();
	}
	
	@Override
	public String toString() {
		return id;
	}
}

