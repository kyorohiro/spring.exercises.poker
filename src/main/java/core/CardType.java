package core;

import javassist.NotFoundException;

/**
 * The {@code CardType} class represents card type .
 * Which has SPADE and HEART and DIAMOND and CLUB
 * 
 * @author kyorohiro
 */
public enum CardType {
	SPADE(4, "S"),
	HEART(3, "H"),
	DIAMOND(2, "D"),
	CLUB(1, "C");
	
	private final int code;
	private final String shortName;
	CardType(int code, String shortName) {
		this.code = code;
		this.shortName = shortName;
	}

	/**
	 * 
	 * @return
	 */
	public int toCode() {
        return code;
    }
 
	public String toShortName() {
		return shortName;
	}
	
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

	/**
	 * Ccreate CardType Object from shortName.
	 * @param c
	 * @return
	 * @throws NotFoundException
	 */
	static public CardType create(char shortName) throws NotFoundException{
		switch(shortName) {
			case 'S': return CardType.SPADE;
			case 'H': return CardType.HEART;
			case 'D': return CardType.DIAMOND;
			case 'C': return CardType.CLUB;
		}
		throw new NotFoundException("");
	}
}
