package core;

import javassist.NotFoundException;

/**
 * The {@code CardType} class represents card type .
 * Which express SPADE and HEART and DIAMOND and CLUB for poker card game. 
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
	 * convert code. 
	 * SPADE is 4, HEART is 3, DIAMOND is 2, CLUB is 1
	 * @return
	 */
	public int toCode() {
        return code;
    }

	/**
	 * convert short name. 
	 * SPADE is 'S', HEART is 'H', DIAMOND is 'D', CLUB is 'C'
	 * @return
	 */
	public String toShortName() {
		return shortName;
	}

	/**
	 * Create CardType Object from shortName.
	 * @param shortName
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
