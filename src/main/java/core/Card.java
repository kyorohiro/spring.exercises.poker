package core;

public class Card {
	
	private Card() {}
	private CardType type;
	private int number;

	public static Card create(String data) throws Exception {
		data = data.trim();
		CardType cardType = CardType.create(data.charAt(0));

		int cardNumber = Integer.parseInt(data.substring(1));

		if( 1<=cardNumber  && cardNumber <=12) {
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
}

