package poker;


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


@RestController
public class PokerController {

	@RequestMapping(path="/poka/score", method=RequestMethod.POST)
	public PokerResponseDao getPokerRole(@RequestBody PokerRequestDao request) throws Exception {
		Hand hand = Hand.create(request.cards);
		return new PokerResponseDao(hand.getName().getName());
	}

	@RequestMapping(path="/poka/scores", method=RequestMethod.POST)
	public PokerResponseDao getPokerRole(@RequestBody PokerListRequestDao request) throws Exception {
		Hand hand = Hand.create(request.cards.get(0));
		return new PokerResponseDao(hand.getName().getName());
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