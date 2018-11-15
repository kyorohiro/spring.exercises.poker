package core;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CardTypeTest {

	@Test
	public void testCardType() {
		CardType cardType = null;
		try { 
			cardType = CardType.create('S');
		} catch (Exception e) {}
		assertEquals("card type", CardType.SPADE, cardType);
		
		try { 
			cardType = CardType.create('D');
		} catch (Exception e) {}
		assertEquals("card type", CardType.DIAMOND, cardType);	

		try { 
			cardType = CardType.create('C');
		} catch (Exception e) {}
		assertEquals("card type", CardType.CLUB, cardType);	

		try { 
			cardType = CardType.create('H');
		} catch (Exception e) {}
		assertEquals("card type", CardType.HEART, cardType);	
	}
	
}
