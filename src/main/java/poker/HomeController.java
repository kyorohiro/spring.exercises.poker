package poker;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	
	@RequestMapping(path="/home", method=RequestMethod.GET)
	Item getItem() {
		return new Item("name", "content");
	}

	static class Item {
		private String name;
		private String content;
		public Item(String name, String content) {
			this.name = name;
			this.content = content;
		}
		public String getName() {
			return name;
		}
		public String getContent() {
			return content;
		}
	}
}
