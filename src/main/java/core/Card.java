package core;

import java.util.Comparator;

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

	public static Card create(String data) throws Exception {
		data = data.trim();
		CardType cardType = CardType.create(data.charAt(0));

		int cardNumber = Integer.parseInt(data.substring(1));

		if( 1<=cardNumber  && cardNumber <=13) {
			Card card = new Card();
			card.type = cardType;
			card.number = cardNumber;
			return card;
		} else {
			throw new Exception("Wrong data number :" + data);
		}
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

