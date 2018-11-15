package poker;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import core.Hand;
import core.PokerCoreException;


@SpringBootApplication
public class App extends SpringApplication{
    public static void main(String[] args) {
    	SpringApplication.run(App.class, args);
    }
}

@Service
class PokerServiceImpl implements PokerService {

	@Override
	public Hand getHand(String cardsSrc) throws PokerCoreException{
		return  Hand.create(cardsSrc);
	}

	@Override
	public List<Hand> getHands(List<Hand> hands) {
		hands.sort(Hand.newPokaComparator());
		return hands;
	}
	
}