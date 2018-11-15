package core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;


/**
 * {@code Hand} class reprsented Hand for Poker Game.
 *
 */
public class Hand {

	private List<Card> cards;
	private HandType name;
	private long score;

	private Hand() {
	}

	/**
	 * @return
	 * return {@code HandType}
	 */
	public  HandType getType() {
		return this.name;
	}

	/**
	 * @return
	 *  return Hand 's score for sort.
	 */
	public long getScore() {
		return score;
	}
	
	/**
	 * @return
	 *  ex "S1 S2 S3 S4 S5"
	 */
	public String getCardsAsString() {
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<cards.size();i++) {
			if(i>0) {
				builder.append(" ");
			}
			builder.append(cards.get(i).toString());
		}
		return builder.toString();
	}

	/**
	 * @return
	 *  ex "S1 S2 S3 S4 S5"
	 */
	@Override
	public String toString() {
		return this.getCardsAsString();
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

	/**
	 * create {@code Hand} object from string data
	 *  ex "S1 S2 S3 S4 S5"
	 *
	 * @return
	 *   {@code Hand} object 
	 */
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

	/**
	 * @return
	 *  {@code Hand} object 's {@code Comparator} for sort.
	 */
	public static Comparator<Hand> newPokaComparator() {
		return new Comparator<Hand>() {
			public int compare(Hand c1, Hand c2) {
				
				return (c1.score == c2.score? 0: (c1.score > c2.score?1:-1));
			}
		};
	}

	private void calcScoreAndName() {
		Pairs ps = new Pairs(this.cards);
		if(isFlush() && isStraight()) {
			this.name = HandType.STRAIGHT_FLUSH;
			this.score = 90*13*13*13*13*13;
			this.score += ps.noPairs.get(2).getScore();
		}
		else if(isFourCard()) {
			this.name = HandType.FOUR;
			this.score = 80*13*13*13*13*13;
			this.score += ps.fourCard.getNumber()*13;
			this.score += ps.noPairs.get(0).getScore();
		}
		else if(isThreeCard() && isPairs()) {
			this.name = HandType.FULL_HOUSE;
			this.score = 70*13*13*13*13*13;
			this.score += ps.threeCard.getScore()*13;
			this.score += ps.pairs.get(0).getScore();
		}
		else if(isFlush()) {
			this.name = HandType.FLUSH;
			this.score = 60*13*13*13*13*13;
			this.score += ps.noPairs.get(4).getScore() * 13*13*13*13;
			this.score += ps.noPairs.get(3).getScore() * 13*13*13;
			this.score += ps.noPairs.get(2).getScore() * 13*13;
			this.score += ps.noPairs.get(1).getScore() * 13;
			this.score += ps.noPairs.get(0).getScore();
		}
		else if(isStraight()) {
			this.name = HandType.STRAIGHT;
			this.score = 50*13*13*13*13*13;
			this.score += ps.noPairs.get(2).getScore();
		}
		else if(isThreeCard()) {
			this.name = HandType.THREE;
			this.score = 40*13*13*13*13*13;
			this.score += ps.threeCard.getScore()*13;
			this.score += ps.noPairs.get(1).getScore() * 13;
			this.score += ps.noPairs.get(0).getScore();
		}		
		else if(isTwoPairs()) {
			this.name = HandType.TWO;
			this.score = 30*13*13*13*13*13;			
			this.score += ps.pairs.get(1).getScore() * 13*13;
			this.score += ps.pairs.get(0).getScore() * 13;
			this.score += ps.noPairs.get(0).getScore();
		}
		else if(isPairs()) {
			this.name = HandType.ONE;
			this.score = 20*13*13*13*13*13;
			this.score += ps.pairs.get(0).getScore() * 13*13*13;
			this.score += ps.noPairs.get(2).getScore() * 13*13;
			this.score += ps.noPairs.get(1).getScore() * 13;
			this.score += ps.noPairs.get(0).getScore();
		}
		else {
			this.name = HandType.NO_PAIRS;
			this.score = 10*13*13*13*13*13;
			this.score += ps.noPairs.get(4).getScore() * 13*13*13*13;
			this.score += ps.noPairs.get(3).getScore() * 13*13*13;
			this.score += ps.noPairs.get(2).getScore() * 13*13;
			this.score += ps.noPairs.get(1).getScore() * 13;
			this.score += ps.noPairs.get(0).getScore();
		}
	}

	private boolean isStraight() {
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
	
	private boolean isFlush() {
		this.cards.sort(Card.newPokaComparator());
		CardType type = cards.get(0).getType();
		for(int i=1;i<this.cards.size();i++) {
			if(cards.get(i).getType() != type) {
				return false;
			}
		}
		return true;		
	}
	
	private boolean isTwoPairs() {
		Map<Integer,List<Card>> pairs = Pairs.getPairs(this.cards);
		int num = 0;
		for(Integer k : pairs.keySet()) {
			if(2 == pairs.get(k).size()) {
				num++;
			}
		}
		return num==2;	
	}

	private boolean isPairs() {
		Map<Integer,List<Card>> pairs = Pairs.getPairs(this.cards);
		int num = 0;
		for(Integer k : pairs.keySet()) {
			if(2 == pairs.get(k).size()) {
				num++;
			}
		}
		return num==1;	
	}

	private boolean isThreeCard() {
		Map<Integer,List<Card>> pairs = Pairs.getPairs(this.cards);
		for(Integer k : pairs.keySet()) {
			if(3 == pairs.get(k).size()) {
				return true;
			}
		}
		return false;
	}

	private boolean isFourCard() {
		Map<Integer,List<Card>> pairs = Pairs.getPairs(this.cards);
		for(Integer k : pairs.keySet()) {
			if(4 == pairs.get(k).size()) {
				return true;
			}
		}
		return false;
	}


}
