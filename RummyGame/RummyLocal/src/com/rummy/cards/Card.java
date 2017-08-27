package com.rummy.cards;

/**
 * Card objects creates an card providing the suit and 
 * rank of a card.
 */
public class Card implements Comparable<Card>{

	private final CardRank rank;
	private final CardSuit suit;
	
	/**
	 * Initializes this with card rank and suit
	 * @param rank
	 * @param suit
	 */
	public Card(CardRank rank, CardSuit suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	/**
	 * Return the card's suit
	 * @return card's suit
	 */
	public String getSuit() {
		return suit.getName();
	}
	
	/**
	 * Return the card's ranking
	 * @return card's rank
	 */
	public String getRank() {
		return rank.getName();
	}
	
	/**
	 * Return the card's total points
	 * @return card's points
	 */
	public int getPoints() {
		int points;
		switch(rank.toString()) {
		case "A":
			points = 1;
		case "J":
		case "Q":
		case "K":
			points = 10;
			break;
		default:
			points = Integer.parseInt(rank.toString());
			break;
		}
		return points;
	}
	
	/**
	 * Returns the positional value with each suit
	 * @return Indexed value
	 */
	public int getIndex() {
		int index;
		switch (rank.toString()) {
		case "A":
			index = 1;
			break;
		case "J":
			index = 11;
			break;
		case "Q":
			index = 12;
			break;
		case "K":
			index = 13;
			break;
		default:
			index = Integer.parseInt(rank.toString());
			break;
		}
		return index;
	}
	
	/**
	 * Returns the name of the card.
	 * e.g. ACE of SPADES
	 * 		KING of DIAMONDS
	 * 		TWO of CLUBS
	 * @return Rank and Suit of card
	 */
	public String getName() {
		return String.format("%s of %s", getRank(), getSuit());
	}
	
	/**
	 * Returns the card ID
	 * e.g. Ace of Spade = AS
	 *      Two of Hearts = 2H
	 *      Seven of Clubs = 7C
	 *      King of Diamonds = KD 
	 * @return card ID
	 */
	@Override
	public String toString() {
		return String.format("%s%s", rank.toString(), suit.toString());
	}

	/**
	 * Method allows for the user of Collections.sort based
	 * card index
	 */
	@Override
	public int compareTo(Card card) {
		int compareIndex = ((Card)card).getIndex();
		return this.getIndex() - compareIndex;
	}
}
