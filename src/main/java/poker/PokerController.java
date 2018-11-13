package poker;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import core.Hand;
import lombok.Data;


/*
 * curl http://localhost:8080/poka/scores -X POST -H "Content-Type: application/json" -d '{"cards": ["S1 S2 S3 S4 S5"]}'
 */
@RestController
public class PokerController {

	@RequestMapping(path="/poka/score", method=RequestMethod.POST)
	public PokerResponseDao getPokerRole(@RequestBody PokerRequestDao request) throws Exception {
		Hand hand = Hand.create(request.cards);
		return new PokerResponseDao(hand.getName().getName());
	}

	@RequestMapping(path="/poka/scores", method=RequestMethod.POST)
	public PokerListResponseDao getPokerRole(@RequestBody PokerListRequestDao request) throws Exception {
		PokerListResponseDao response = new PokerListResponseDao();
		
		List<Hand> handList = new ArrayList<>();
		for(String handSrc : request.cards) {
			handList.add(Hand.create(handSrc));
		}
		handList.sort(Hand.newPokaComparator());
		response.result = new ArrayList<>();
		for(Hand h : handList) {
			response.result.add(new CardDao(h.getName().getName(), false));			
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

@Data
class PokerRequestDao {
	String cards;
	public PokerRequestDao() {
		
	}
}

@Data
class PokerListRequestDao {
	List<String> cards;
	public PokerListRequestDao() {
		
	}
}

@Data 
class PokerResponseDao {
	String scoreName;

	public PokerResponseDao() {
	}

	public PokerResponseDao(String scoreName) {
		this.scoreName = scoreName;
	}
}


@Data
class CardDao {
	String card;
	boolean best;
	public CardDao() {;}
	public CardDao(String card, boolean best) {
		this.card = card;
		this.best = best;
	}
}

@Data 
class PokerListResponseDao {

	List<CardDao> result; 

	public PokerListResponseDao() {
	}
	
}

@Data
class ErrorResponseDao {
	int code = 3;
	String description = "xxx";
	public ErrorResponseDao() {}
}

@ControllerAdvice
class EmployeeNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	ErrorResponseDao employeeNotFoundHandler(Exception ex) {
		return new ErrorResponseDao();
	}
}