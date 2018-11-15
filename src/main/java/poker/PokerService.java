package poker;

import java.util.List;

import core.Hand;
import core.PokerCoreException;

public interface PokerService {
	Hand getHand(String cardsSrc) throws PokerCoreException;
	List<Hand> getHands(List<Hand> hands);
}
