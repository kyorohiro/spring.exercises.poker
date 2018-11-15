package poker;

import java.util.List;

import core.Hand;

public interface PokerService {
	Hand getHand(String cardsSrc);
	List<Hand> getHands(List<Hand> hands);
}
