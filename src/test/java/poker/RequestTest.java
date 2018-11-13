package poker;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RequestTest {

	@Autowired
    private MockMvc mockMvc;


	@Test
	public void helloRequest() throws Exception {
		MvcResult result = this.mockMvc.perform(
				post("/poka/score")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"cards\":\"S1 S2 S3 S4 S5\"}")
		).andReturn();
		String content = result.getResponse().getContentAsString();
		JsonParser parser = JsonParserFactory.getJsonParser();
		Map<String,Object> ret =parser.parseMap(content);
		assertEquals("", "Straight Flush",ret.get("scoreName"));
	}

}
