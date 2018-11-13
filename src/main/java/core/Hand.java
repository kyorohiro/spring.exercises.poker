package core;

import java.util.ArrayList;
import java.util.List;

public class Hand {

	private List<Card> cards;
	public String name;
	int score;

	private Hand() {}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<cards.size();i++) {
			if(i>0) {
				builder.append(" ");
			}
			builder.append(cards.get(i).toString());
		}
		//System.out.println("XX+>>>"+builder.toString());
		return builder.toString();
	}

	public boolean isStraight() {
		this.cards.sort(Card.newPokaComparator());
		if(cards.get(0).getNumber() == 1 && cards.get(1).getNumber() == 10 
			&& cards.get(2).getNumber() == 11 && cards.get(3).getNumber() == 12
			&& cards.get(4).getNumber() == 13) {
			return true;
		}
		for(int i=1;i<this.cards.size();i++) {
			if(cards.get(i-1).getNumber()+1 != cards.get(i).getNumber()) { 
				return false;
			}
		}
		return true;		
	}
	
	public boolean isFlush() {
		this.cards.sort(Card.newPokaComparator());
		CardType type = cards.get(0).getType();
		for(int i=1;i<this.cards.size();i++) {
			if(cards.get(i).getType() != type) {
				return false;
			}
		}
		return true;		
	}
	
	@NoTest
	public static Hand createUnsafe(String data) {
		try {
			return Hand.create(data);
		} catch (Exception e) {
			return null;
		}
	}

	public static Hand create(String data) throws Exception {
		String[] cardbases = data.split("[ ]+");
		if(cardbases.length != 5) {
			throw new Exception("Wrong card num : "+data);
		}
		Hand hand = new Hand();
		hand.cards = new ArrayList<>();
		for(String cardbase : cardbases) {
			hand.cards.add(Card.create(cardbase));
		}
		hand.cards.sort(Card.newPokaComparator());
		return hand;
	}
}
