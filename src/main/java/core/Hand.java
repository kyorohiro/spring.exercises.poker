package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Hand {

	private List<Card> cards;
	private HandName name;
	private long score;

	private Hand() {
	}

	public  HandName getName() {
		return this.name;
	}

	public long getScore() {
		return score;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<cards.size();i++) {
			if(i>0) {
				builder.append(" ");
			}
			builder.append(cards.get(i).toString());
		}
		return builder.toString();
	}

	private void calcScoreAndName() {
		Pairs ps = new Pairs(this.cards);
		if(isFlush() && isStraight()) {
			this.name = HandName.STRAIGHT_FLUSH;
			this.score = 90*13*13*13*13*13;
			this.score += ps.noPairs.get(2).getScore();
		}
		else if(isFourCard()) {
			this.name = HandName.FOUR;
			this.score = 80*13*13*13*13*13;
			this.score += ps.fourCard.getNumber()*13;
			this.score += ps.noPairs.get(0).getScore();
		}
		else if(isThreeCard() && isPairs()) {
			this.name = HandName.FULL_HOUSE;
			this.score = 70*13*13*13*13*13;
			this.score += ps.threeCard.getScore()*13;
			this.score += ps.pairs.get(0).getScore();
		}
		else if(isFlush()) {
			this.name = HandName.FLUSH;
			this.score = 60*13*13*13*13*13;
			this.score += ps.noPairs.get(4).getScore() * 13*13*13*13;
			this.score += ps.noPairs.get(3).getScore() * 13*13*13;
			this.score += ps.noPairs.get(2).getScore() * 13*13;
			this.score += ps.noPairs.get(1).getScore() * 13;
			this.score += ps.noPairs.get(0).getScore();
		}
		else if(isStraight()) {
			this.name = HandName.STRAIGHT;
			this.score = 50*13*13*13*13*13;
			this.score += ps.noPairs.get(2).getScore();
		}
		else if(isThreeCard()) {
			this.name = HandName.THREE;
			this.score = 40*13*13*13*13*13;
			this.score += ps.threeCard.getScore()*13;
			this.score += ps.noPairs.get(1).getScore() * 13;
			this.score += ps.noPairs.get(0).getScore();
		}		
		else if(isTwoPairs()) {
			this.name = HandName.TWO;
			this.score = 30*13*13*13*13*13;			
			this.score += ps.pairs.get(1).getScore() * 13*13;
			this.score += ps.pairs.get(0).getScore() * 13;
			this.score += ps.noPairs.get(0).getScore();
		}
		else if(isPairs()) {
			this.name = HandName.ONE;
			this.score = 20*13*13*13*13*13;
			this.score += ps.pairs.get(0).getScore() * 13*13*13;
			this.score += ps.noPairs.get(2).getScore() * 13*13;
			this.score += ps.noPairs.get(1).getScore() * 13;
			this.score += ps.noPairs.get(0).getScore();
		}
		else {
			this.name = HandName.NO_PAIRS;
			this.score = 10*13*13*13*13*13;
			this.score += ps.noPairs.get(4).getScore() * 13*13*13*13;
			this.score += ps.noPairs.get(3).getScore() * 13*13*13;
			this.score += ps.noPairs.get(2).getScore() * 13*13;
			this.score += ps.noPairs.get(1).getScore() * 13;
			this.score += ps.noPairs.get(0).getScore();
		}
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
	
	public boolean isTwoPairs() {
		Map<Integer,List<Card>> pairs = getPairs();
		int num = 0;
		for(Integer k : pairs.keySet()) {
			if(2 == pairs.get(k).size()) {
				num++;
			}
		}
		return num==2;	
	}

	public boolean isPairs() {
		Map<Integer,List<Card>> pairs = getPairs();
		int num = 0;
		for(Integer k : pairs.keySet()) {
			if(2 == pairs.get(k).size()) {
				num++;
			}
		}
		return num==1;	
	}

	public boolean isThreeCard() {
		Map<Integer,List<Card>> pairs = getPairs();
		for(Integer k : pairs.keySet()) {
			if(3 == pairs.get(k).size()) {
				return true;
			}
		}
		return false;
	}

	public boolean isFourCard() {
		Map<Integer,List<Card>> pairs = getPairs();
		for(Integer k : pairs.keySet()) {
			if(4 == pairs.get(k).size()) {
				return true;
			}
		}
		return false;
	}

	public Map<Integer, List<Card>> getPairs() {
		Map<Integer, List<Card>> ret = new HashMap<>();
		this.cards.sort(Card.newPokaComparator());
		for(int i=0;i<this.cards.size();i++) {
			Card card = cards.get(i);
			if(!ret.containsKey(card.getNumber())) {
				ret.put(card.getNumber(), new ArrayList<Card>());	
			}
			ret.get(card.getNumber()).add(card);
		}
		return ret;
	}
	
	@NoTest
	public static Hand createUnsafe(String data) {
		try {
			return Hand.create(data);
		} catch (Exception e) {
			e.printStackTrace();
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
		hand.calcScoreAndName();
		return hand;
	}
}
