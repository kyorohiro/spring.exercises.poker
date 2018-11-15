package poker;

import java.util.List;
import core.HandType;
import core.PokerCoreException;

interface PokerService {
	PokerHand getHand(String cardsSrc) throws PokerCoreException;
	List<PokerHand> getHands(List<PokerHand> hands);
}

interface PokerHand {
	String getCardsAsString();
	HandType getType();
	int getScore();
}
