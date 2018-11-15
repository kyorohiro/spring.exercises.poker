package core;

import java.util.Comparator;

/**
 * {@code Card} class reprsented Card for Poker Game.
 * @author kyorohiro
 *
 */
public class Card {
	
	private Card() {}
	private CardType type;
	private int number;

	@NoTest
	public static Card createUnsafe(String data) {
		try {
			return create(data);
		} catch(Exception e) {
			return null;
		}
	}

	/**
	 * Create Card Object from short card data.
	 * ex "S1 S2 S3 S4 S5"
	 * format is
	 *   CARDS := {@code CardType#toShortName()} [1-9][0-3]?
	 * 
	 * @param data
	 *   short cards data
	 * @return
	 *   {@code Card} object
	 * @throws PokerCoreException
	 */
	public static Card create(String data) throws PokerCoreException {
		if(data == null || data.length() <=1) {
			throw new PokerCoreException(PokerCoreException.Type.WRONG_CARD_NAME);			
		}

		data = data.trim();
		CardType cardType = CardType.create(data.charAt(0));
		
		try {
			int cardNumber = Integer.parseInt(data.substring(1));
			if( 1<=cardNumber  && cardNumber <=13) {
				Card card = new Card();
				card.type = cardType;
				card.number = cardNumber;
				return card;
			}
		} catch(NumberFormatException e) {}
		throw new PokerCoreException(PokerCoreException.Type.WRONG_CARD_NAME);
	}

	public CardType getType() {
		return this.type;
	}
	
	public int getNumber() {
		return this.number;
	}

	public int getScore() {
		if(this.number == 1) {
			return 13;
		} else {
			return this.number-1;
		}
	}

	@Override
	public String toString() {
		return this.type.toShortName() + this.number;
	}

	public static Comparator<Card> newPokaComparator() {
		return new Comparator<Card>() {
			public int compare(Card c1, Card c2) {
				if(c1.number == c2.number) {
					return c1.type.toCode() - c2.type.toCode();
				} else {
					return c1.number - c2.number;
				}
			}
		};
	}
}

