package core;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Utilities And Package private class.
 */
class HandFeatureInfo {
	PairInfo pairInfo;
	boolean isStraight;
	boolean isFlush;

	public static HandFeatureInfo analyzeAndCreate(List<Card> cards) {
		HandFeatureInfo target = new HandFeatureInfo();
		target.pairInfo = createPairInfo(cards);
		target.isFlush = isFlush(cards);
		target.isStraight = isStraight(cards);
		return target;
	}

	static class PairInfo {
		List<Card> noPairs = new LinkedList<>();
		List<Card> pairs = new LinkedList<>();
		Card threeCard = null;
		Card fourCard = null;
	}
	
	public static PairInfo createPairInfo(List<Card> cards) {
		PairInfo info = new PairInfo();
		Map<Integer, List<Card>> memoAboutPairs =  HandFeatureInfo.createMemoAboutPairs(cards);
		memoAboutPairs.keySet().stream().forEach(k->{
			if(4 == memoAboutPairs.get(k).size()) {
				info.fourCard = memoAboutPairs.get(k).get(0);
			}
			else if(3 == memoAboutPairs.get(k).size()) {
				info.threeCard = memoAboutPairs.get(k).get(0);
			}
			else if(2 == memoAboutPairs.get(k).size()) {
				info.pairs.add(memoAboutPairs.get(k).get(0));
			}
			else {
				info.noPairs.add(memoAboutPairs.get(k).get(0));
			}
		});

		info.pairs.sort(Card.newPokaComparator());
		info.noPairs.sort(Card.newPokaComparator());
		return info;
	}

	public static boolean isFlush(List<Card> cards) {
		CardType type = cards.get(0).getType();
		return  cards.size() == cards.stream()
				.filter(card->card.getType() == type)
				.count();
	}


	public static boolean isStraight(List<Card> cards) {
		cards.sort(Card.newPokaComparator());
		if(cards.get(0).getNumber() == 1 
			&& cards.get(1).getNumber() == 10 && cards.get(2).getNumber() == 11
			&& cards.get(3).getNumber() == 12 && cards.get(4).getNumber() == 13) {
			return true;
		}
		return cards.size()-1 == IntStream.range(1, cards.size())
				.filter(i -> (cards.get(i).getNumber()) == (cards.get(i-1).getNumber()+1))
				.count();
	}

	public static Map<Integer, List<Card>> createMemoAboutPairs(List<Card> cards) {
		Map<Integer, List<Card>> memoAboutPairs = new LinkedHashMap<>();
		cards.stream().forEach(card ->{
			if(!memoAboutPairs.containsKey(card.getNumber())) {
				memoAboutPairs.put(card.getNumber(), new ArrayList<Card>());	
			}
			memoAboutPairs.get(card.getNumber()).add(card);
		});
		return memoAboutPairs;
	}
}
