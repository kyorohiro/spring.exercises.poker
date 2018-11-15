package core;


/**
 * The {@code CardType} class represents card type .
 * Which express SPADE and HEART and DIAMOND and CLUB for poker card game. 
 * 
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
	 * convert to integer code. 
	 * @return
	 *   SPADE is 4, HEART is 3, DIAMOND is 2, CLUB is 1
	 */
	public int toCode() {
        return code;
    }

	/**
	 * convert short name. 
	 * @return
	 * 	 SPADE is 'S', HEART is 'H', DIAMOND is 'D', CLUB is 'C'
	 */
	public String toShortName() {
		return shortName;
	}

	/**
	 * Create  {@code CardType} Object from shortName.
	 * @param shortName
	 *   CardType's shortName
	 * @return 
	 *   return  {@code CardType}
	 * @throws PokerCoreException
	 *   throw this error, unless 'S' or 'H' or 'D' or 'C' 's parameter as shortName.
	 */
	static public CardType create(char shortName) throws PokerCoreException {
		switch(shortName) {
			case 'S': return CardType.SPADE;
			case 'H': return CardType.HEART;
			case 'D': return CardType.DIAMOND;
			case 'C': return CardType.CLUB;
		}
		throw new PokerCoreException(PokerCoreException.Type.WRONG_CARD_NAME);
	}
}
