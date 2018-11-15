package core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import core.PokerCoreException.Type;


/**
 * {@code Hand} class represented Hand for Poker Game.
 *
 */

public class Hand {

	private List<Card> cards;
	private HandType name;
	private int score;
	private HandFeatureInfo ps;

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
	public int getScore() {
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
	public static Hand create(String data) throws PokerCoreException {
		String[] cardbases = data.split("[ ]+");
		if(cardbases.length != 5) {
			throw new PokerCoreException(Type.WRONG_CARD_NAME, "Wrong card num : "+data);
		}
		Hand hand = new Hand();
		hand.cards = new ArrayList<>();
		for(String cardbase : cardbases) {
			hand.cards.add(Card.create(cardbase));
		}
		hand.ps = HandFeatureInfo.analyzeAndCreate(hand.cards);
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
				
				return (c1.score == c2.score? 0: (c1.score < c2.score?1:-1));
			}
		};
	}

	private void calcScoreAndName() {
		this.cards.sort(Card.newPokaComparator());
		if(isFlush() && isStraight()) {
			this.name = HandType.STRAIGHT_FLUSH;
			this.score = 90*13*13*13*13*13;
			this.score += ps.pairInfo.noPairs.get(2).getScore();
		}
		else if(isFourCard()) {
			this.name = HandType.FOUR;
			this.score = 80*13*13*13*13*13;
			this.score += ps.pairInfo.fourCard.getNumber()*13;
			this.score += ps.pairInfo.noPairs.get(0).getScore();
		}
		else if(isThreeCard() && isPairs()) {
			this.name = HandType.FULL_HOUSE;
			this.score = 70*13*13*13*13*13;
			this.score += ps.pairInfo.threeCard.getScore()*13;
			this.score += ps.pairInfo.pairs.get(0).getScore();
		}
		else if(isFlush()) {
			this.name = HandType.FLUSH;
			this.score = 60*13*13*13*13*13;
			this.score += ps.pairInfo.noPairs.get(4).getScore() * 13*13*13*13;
			this.score += ps.pairInfo.noPairs.get(3).getScore() * 13*13*13;
			this.score += ps.pairInfo.noPairs.get(2).getScore() * 13*13;
			this.score += ps.pairInfo.noPairs.get(1).getScore() * 13;
			this.score += ps.pairInfo.noPairs.get(0).getScore();
		}
		else if(isStraight()) {
			this.name = HandType.STRAIGHT;
			this.score = 50*13*13*13*13*13;
			this.score += ps.pairInfo.noPairs.get(2).getScore();
		}
		else if(isThreeCard()) {
			this.name = HandType.THREE;
			this.score = 40*13*13*13*13*13;
			this.score += ps.pairInfo.threeCard.getScore()*13;
			this.score += ps.pairInfo.noPairs.get(1).getScore() * 13;
			this.score += ps.pairInfo.noPairs.get(0).getScore();
		}		
		else if(isTwoPairs()) {
			this.name = HandType.TWO;
			this.score = 30*13*13*13*13*13;			
			this.score += ps.pairInfo.pairs.get(1).getScore() * 13*13;
			this.score += ps.pairInfo.pairs.get(0).getScore() * 13;
			this.score += ps.pairInfo.noPairs.get(0).getScore();
		}
		else if(isPairs()) {
			this.name = HandType.ONE;
			this.score = 20*13*13*13*13*13;
			this.score += ps.pairInfo.pairs.get(0).getScore() * 13*13*13;
			this.score += ps.pairInfo.noPairs.get(2).getScore() * 13*13;
			this.score += ps.pairInfo.noPairs.get(1).getScore() * 13;
			this.score += ps.pairInfo.noPairs.get(0).getScore();
		}
		else {
			this.name = HandType.NO_PAIRS;
			this.score = 10*13*13*13*13*13;
			this.score += ps.pairInfo.noPairs.get(4).getScore() * 13*13*13*13;
			this.score += ps.pairInfo.noPairs.get(3).getScore() * 13*13*13;
			this.score += ps.pairInfo.noPairs.get(2).getScore() * 13*13;
			this.score += ps.pairInfo.noPairs.get(1).getScore() * 13;
			this.score += ps.pairInfo.noPairs.get(0).getScore();
		}
	}

	private boolean isStraight() {
		return ps.isStraight;
	}
	
	private boolean isFlush() {
		return ps.isFlush;
	}
	
	private boolean isTwoPairs() {
		return 2 == ps.pairInfo.pairs.size();
	}

	private boolean isPairs() {
		return 1 == ps.pairInfo.pairs.size();
	}

	private boolean isThreeCard() {
		return null != ps.pairInfo.threeCard;
	}

	private boolean isFourCard() {
		return null != ps.pairInfo.fourCard;
	}


}
