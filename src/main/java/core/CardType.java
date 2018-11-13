package core;

import javassist.NotFoundException;

public enum CardType {
	SPADE,
	HEART,
	DIAMOND,
	CLUB;
	
	@NoTest
	static public boolean valid(String data) {
		if(data.length() <= 0) {
			return false;
		}
		try {
			CardType.create(data.charAt(0));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	static public CardType create(char c) throws NotFoundException{
		switch(c) {
			case 'S': return CardType.SPADE;
			case 'H': return CardType.HEART;
			case 'D': return CardType.DIAMOND;
			case 'C': return CardType.CLUB;
		}
		throw new NotFoundException("");
	}
}
