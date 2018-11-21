package poker;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Service;

import core.Hand;
import core.HandType;
import core.PokerCoreException;


//
@SpringBootApplication
public class App extends SpringBootServletInitializer { // extends SpringApplication
    public static void main(String[] args) {
    	SpringApplication.run(App.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(App.class);
    }
}

@Service
class PokerServiceImpl implements PokerService {

	@Override
	public PokerHand getHand(String cardsSrc) throws PokerCoreException{
		return  new PokerHandImpl(Hand.create(cardsSrc));
	}

	@Override
	public List<PokerHand> getHands(List<PokerHand> hands) {
		hands.sort((x,y)->x.getScore()-y.getScore());
		return hands;
	}
	
}

class PokerHandImpl implements PokerHand {
	private Hand origin;
	
	PokerHandImpl(Hand origin) {
		this.origin = origin;
	}
	
	public String getCardsAsString() {
		return this.origin.getCardsAsString();
	}
	
	public HandType getType() {
		return this.origin.getType();
	}

	public int getScore() {
		return this.origin.getScore();
	}
}