package poker;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import core.PokerCoreException;
import lombok.Data;
import poker.PokerHand;


@RestController
public class PokerController {

	@Autowired
	PokerService pokerService;

	@RequestMapping(path="/poker/hand", method=RequestMethod.POST)
	public PokerResponse getPokerRole(@RequestBody PokerRequest request) throws PokerCoreException {
		PokerHand hand = pokerService.getHand(request.cards);
		return new PokerResponse(hand.getCardsAsString(), hand.getType().getName());
	}

	@RequestMapping(path="/poker/hands", method=RequestMethod.POST)
	public PokerListResponse getPokerRole(@RequestBody PokerListRequest request) throws Exception {
		PokerListResponse response = new PokerListResponse();
		
		List<PokerHand> handList = new ArrayList<>();
		for( PokerRequest handSrc : request.hands) {
			handList.add(pokerService.getHand(handSrc.cards));
		}
		
		handList = pokerService.getHands(handList);

		response.result = new ArrayList<>();
		for(PokerHand h : handList) {
			response.result.add(new PokerListResponse.Card(h.getCardsAsString(), h.getType().getName(), false));			
		}
		if(handList.size() == 1) {
			response.result.get(0).best = true;
		}
		else if(handList.size() > 1) {
			if(handList.get(0).getScore() != handList.get(1).getScore()) {
				response.result.get(0).best = true;
			}
		}
		return response;
	}
}

@ControllerAdvice
class EmployeeNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(PokerCoreException.class)
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	ErrorResponse employeeNotFoundHandler(PokerCoreException ex) {
		return new ErrorResponse(ex.getType().toCode(), ex.getType().name() + ":" + ex.getDescription());
	}
}

@Data
class PokerRequest {
	String cards;
	public PokerRequest() {}
}

@Data
class PokerListRequest {
	List<PokerRequest> hands;
	public PokerListRequest() {}
}

@Data 
class PokerResponse {
	String cards;
	String hand;

	public PokerResponse() {}

	public PokerResponse(String cards, String hand) {
		this.hand = hand;
		this.cards = cards;
	}
}

@Data 
class PokerListResponse {

	List<Card> result; 

	public PokerListResponse() {}

	@Data
	static class Card {
		String card;
		String hand;
		boolean best;
		public Card() {;}
		public Card(String card, String hand, boolean best) {
			this.card = card;
			this.best = best;
			this.hand = hand;
		}
	}
}

@Data
class ErrorResponse {
	int code = 3;
	String description = "xxx";
	public ErrorResponse(int code, String description) {
		this.code = code;
		this.description = description;
	}
}

