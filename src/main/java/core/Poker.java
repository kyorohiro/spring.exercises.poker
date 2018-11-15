package core;

/**
 * This package 's Helper class 
 */
public class Poker {
	public static Hand createHand(String handData) throws PokerCoreException {
		return Hand.create(handData);
	}
}
