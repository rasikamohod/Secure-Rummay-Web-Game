package com.rummy.messages;

/**
 * Object provides messages for the players
 * @author Jimmie Hardaway III
 *
 */
public class PlayerMessage
{
	
	public static String setWager(String username, int wager) {
		return String.format("Setting $%d wager for player %s.", wager, username);
	}
	
	public static String invalidWager(String username, int bankTotal, int wager) {
		return String.format("Insufficient funds [$%d] for player %s to wager $%d." , bankTotal, username, wager);
	}
	
	public static String newBankTotal(String username, int bankTotal) {
		return String.format("Player %s bank total changed to $%s.", username, bankTotal);
	}
	
	public static String emptyBank(String username) {
		return String.format("Player %s has no more funds remaining.", username);
	}
}
