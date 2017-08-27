package com.rummy.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rummy.cards.Card;
import com.rummy.cards.CardRank;
import com.rummy.cards.CardSuit;

/**
 * Object provides the necessary player actions to implement a game of rummy
 * @author Jimmie
 *
 */
public class RummyAction {
	private static final Logger LOGGER = LoggerFactory.getLogger(RummyAction.class);

	/**
	 * Draw a card from the dealer's pile
	 * @param playerHand Player's hand to store card
	 * @param dealerPile Stock or Discard pile
	 */
	public static void drawCard(List<Card> playerHand, Stack<Card> dealerPile) {
		if(!dealerPile.isEmpty()) {
			Card pileCard = dealerPile.pop();
			playerHand.add(dealerPile.pop());
			LOGGER.debug(String.format("Card %s added to player's hand %s", pileCard, playerHand));
		}
	}

	/**
	 * Discard card from player's 
	 * @param playerHand
	 * @param cardID
	 * @param discardPile
	 */
	public static void discardCard(List<Card> playerHand, String cardID, Stack<Card> discardPile) {
		if(cardsExist(playerHand, cardID)) {
			Iterator<Card> iter = playerHand.iterator();
			while(iter.hasNext()) {
				Card currentCard = iter.next();
				if(currentCard.toString().equals(cardID)) {
					discardPile.push(currentCard);
					iter.remove();
					LOGGER.debug(String.format("%s discarded from player's hand %s", currentCard, playerHand));
				}
			}
		}
	}
	
	/**
	 * Lay cards from user into existing meld
	 * @param meld Meld to add cards
	 * @param input User input of card ID(s)
	 */
	public static void layCards(List<Card> meld, String input) {
		if(isValidLay(meld, input)) {
			String[] cardIDs = input.split(",");
			for(String cardID: cardIDs)
				meld.add(getCard(cardID));
			Collections.sort(meld);
		}
	}

	/**
	 * Test user cardID input is a valid sequence or set to add to the 
	 * meld to alter
	 * @param meld Meld to alter
	 * @param input User input 
	 * @return true if input is valid sequence or set, false otherwise
	 */
	public static boolean isValidLay(List<Card> meld, String input) {
		if(checkInput(input)) {
			String[] cardIDs = input.split(",");
			// Test is lay is adding to set
			if(isSet(meld) && meld.size() == 3 && cardIDs.length ==1) {
				LOGGER.debug("Testing if player is laying a card in a set");
				// Only check first
				Card inputCard = getCard(cardIDs[0]);
				if(!meld.contains(inputCard)) {
					List<Card> tempMeld = new ArrayList<Card>();
					tempMeld.addAll(meld);
					tempMeld.add(inputCard);
					return isSet(tempMeld);
				}
			}
			else if(isSequence(meld) && (meld.size() + cardIDs.length) <= 13) {
				List<Card> tempMeld = new ArrayList<Card>();
				tempMeld.addAll(meld);
				for(String cardID: cardIDs) {
					Card inputCard = getCard(cardID);
					if(!meld.contains(inputCard))
						tempMeld.add(inputCard);
					else {
						LOGGER.debug(String.format("Invalid Input. Cannot alter meld %s based on %s input",  meld, input));
						return false;
					}
				}
				Collections.sort(tempMeld);
				return isSequence(tempMeld);
			}
			else {
				LOGGER.debug(String.format("Invalid Input. Cannot alter meld %s based on %s input",  meld, input));
			}
			return false;
		}
		else {
			LOGGER.debug("User input is invalid, cannot test the player's lay");
			return false;
		}
	}

	/**
	 * Test if cards exist in player's hand
	 * @param playerHand Player's hand to test
	 * @param input Comma delimited list of card IDs
	 * @return true if player's card has IDs that match those provided in input, false otherwise
	 */
	public static boolean cardsExist(List<Card> playerHand, String input) {
		if(checkInput(input)) {
			for(String cardID: input.split(",")) {
				if(!playerHand.toString().contains(cardID)) {
					LOGGER.debug(String.format("Invalid input. All cards from input \"%s\" does not exist in the player's hand %s,",
							input, playerHand));
					return false;
				}
			}
			LOGGER.debug(String.format("All cards from input \"%s\" exist in the player's hand %s", 
					input, playerHand));
			return true;
		}
		else {
			LOGGER.debug("Cannot check if card exist due to invalid input from user");
			return false;
		}

	}

	/**
	 * Creates new meld out of the player's hand using provided input
	 * @param playerHand Hand to retrieve cards
	 * @param input Comma delimited list of card IDs
	 * @return List of cards
	 */
	public static List<Card> createMeld(List<Card> playerHand, String input) {
		List<Card> meld = new ArrayList<Card>();
		if(checkInput(input)) {
			for(String cardID: input.split(",")) {
				Iterator<Card> iter = playerHand.iterator();
				while(iter.hasNext()) {
					Card currentCard = iter.next();
					String id = currentCard.toString();
					if(id.equals(cardID)) {
						meld.add(currentCard);
						iter.remove();
						break;
					}
				}
			}
			LOGGER.debug(String.format("Cards removed to create new meld %s",  meld));
		}
		else {
			LOGGER.debug("Cannot return valid meld due to invalid input from user");
			meld = null;
		}
		return meld;
	}

	/**
	 * Put invalid meld created by player, back into their hand
	 * @param playerHand Hand to store cards
	 * @param invalidMeld Meld to return
	 */
	public static void returnInvalidMeld(List<Card> playerHand, List<Card> invalidMeld) {
		LOGGER.debug(String.format("Returning invalid meld %s to player's hand %s", invalidMeld, playerHand));
		playerHand.addAll(invalidMeld);
	}

	/**
	 * Test if cards are a complete set
	 * Cards total must equal 3 or 4 to be considered a set
	 * @param cards Cards to test
	 * @return true if all cards have the same rank and different suits, false otherwise
	 */
	public static boolean isSet(List<Card> cards) {
		// Test total cards
		int cardsTotal = cards.size();
		if(cardsTotal < 3 || cardsTotal > 4) {
			LOGGER.debug(String.format("Invalid Meld Set in %s. Valid set should contain 3 to 4 cards", cards));
			return false;
		}

		// Test card set
		Iterator<Card> iter = cards.iterator();
		Card currentCard = null;
		if(iter.hasNext())
			currentCard = iter.next();

		Set<String> suitSet = new HashSet<String>();
		int suitCount = 1;
		while(iter.hasNext()) {
			String currentRank = currentCard.getRank();
			String currentSuit = currentCard.getSuit();
			suitSet.add(currentSuit);

			Card nextCard = iter.next();
			String nextRank = nextCard.getRank();
			String nextSuit = nextCard.getSuit();
			suitSet.add(nextSuit);
			suitCount++;

			boolean sameRank = currentRank == nextRank;
			boolean differentSuit = suitCount == suitSet.size();
			if(!sameRank || !differentSuit) {
				LOGGER.debug(String.format("Incorrect set between %s and %s in %s", currentCard, nextCard, cards));
				return false;
			}
			currentCard = nextCard;
		}
		LOGGER.debug(String.format("Valid set in %s", cards));
		return true;
	}

	/**
	 * Test if cards are a complete sequence
	 * Cards total must equal 3 to 13 to be considered a sequence
	 * @param cards Cards to test
	 * @return true if next index is current index + 1 and same suit, false otherwise
	 */
	public static boolean isSequence(List<Card> cards) {
		// Test total cards
		int cardsTotal = cards.size();
		if(cardsTotal < 3 || cardsTotal > 13) {
			LOGGER.debug(String.format("Invalid Meld Sequence in %s. Valid sequence should contain 3 to 13 cards", cards));
		}

		// Test cards sequence
		Iterator<Card> iter = cards.iterator();
		Card currentCard = null;
		if(iter.hasNext())
			currentCard = iter.next();

		while(iter.hasNext()) {			
			int currentIndex = currentCard.getIndex();
			String currentSuit = currentCard.getSuit();

			Card nextCard = iter.next();
			int nextIndex = nextCard.getIndex();
			String nextSuit = nextCard.getSuit();

			boolean inSequence = (currentIndex + 1) == nextIndex;
			boolean sameSuit = currentSuit == nextSuit;
			if(!inSequence || !sameSuit) {
				LOGGER.debug(String.format("Incorrect sequence between %s and %s in %s",  currentCard, nextCard, cards));
				return false;
			}
			currentCard = nextCard;			
		}
		LOGGER.debug(String.format("Valid sequence in %s", cards));
		return true;
	}

	/**
	 * Test if user input is authorized cardID comma delimited pattern
	 * and doesn't end with comma
	 * Matching a cards rank of A,2-10,J,Q,K and suit of C,D,H,S
	 * e.g. Accepted=2C,3c,4H,10S  Not Accepted=2C,4H, 
	 * @param input User cardID input
	 * @return true is accepted format, false otherwise
	 */
	public static boolean checkInput(String input) {
		// Generate regular expression to check
		String cardIDPattern = "(?:[2-9]|10)(?:[CDHScdhs])";
		String acceptedPattern = String.format("^(?:%s,)*(?:%s)", cardIDPattern, cardIDPattern);
		// Compile regex
		Pattern pattern = Pattern.compile(acceptedPattern);
		// Test if input matches pattern
		Matcher matcher = pattern.matcher(input);
		if(matcher.matches())
			return true;
		return false;
	}

	/**
	 * Return valid card based on card ID
	 * @param cardID ID of card to return
	 * @return Card
	 */
	private static Card getCard(String cardID) {
		for(CardSuit suit: CardSuit.values()) 
			for(CardRank rank: CardRank.values()) {
				Card currentCard = new Card(rank, suit);
				if(currentCard.toString().equals(cardID))
					return currentCard;
			}
		return null;
	}

}
