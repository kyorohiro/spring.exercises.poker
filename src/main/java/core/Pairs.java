package core;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Pairs {
	List<Card> pairs = new LinkedList<>();
	Card threeCard = null;
	Card fourCard = null;
	List<Card> noPairs = new LinkedList<>();

	public Pairs(List<Card> cards) {
		Map<Integer, List<Card>> ps =  Pairs.getPairs(cards);
		for(Integer k : ps.keySet()) {
			if(4 == ps.get(k).size()) {
				fourCard = ps.get(k).get(0);
			}
			else if(3 == ps.get(k).size()) {
				threeCard = ps.get(k).get(0);
			}
			else if(2 == ps.get(k).size()) {
				pairs.add(ps.get(k).get(0));
			}
			else {
				noPairs.add(ps.get(k).get(0));
			}
		}
		pairs.sort(Card.newPokaComparator());
		noPairs.sort(Card.newPokaComparator());
	}

	public static Map<Integer, List<Card>> getPairs(List<Card> cards) {
		Map<Integer, List<Card>> ret = new LinkedHashMap<>();
		cards.sort(Card.newPokaComparator());
		for(int i=0;i<cards.size();i++) {
			Card card = cards.get(i);
			if(!ret.containsKey(card.getNumber())) {
				ret.put(card.getNumber(), new ArrayList<Card>());	
			}
			ret.get(card.getNumber()).add(card);
		}
		return ret;
	}
	
}
