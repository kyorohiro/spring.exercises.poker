package core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * {@code CardType} 's test
 * @author kyorohiro
 *
 */
public class CardTypeTest {

	@Test
	public void create_basic() {
		CardType cardType = null;
		try { 
			cardType = CardType.create('S');
		} catch (PokerCoreException e) {}
		assertEquals("card type", CardType.SPADE, cardType);
		
		try { 
			cardType = CardType.create('D');
		} catch (PokerCoreException e) {}
		assertEquals("card type", CardType.DIAMOND, cardType);	

		try { 
			cardType = CardType.create('C');
		} catch (PokerCoreException e) {}
		assertEquals("card type", CardType.CLUB, cardType);	

		try { 
			cardType = CardType.create('H');
		} catch (PokerCoreException e) {}
		assertEquals("card type", CardType.HEART, cardType);	
	}

	@Test
	public void create_error() {
		try { 
			CardType.create('A');
			fail();
		} catch (PokerCoreException e) {
			assertEquals("xx",PokerCoreException.Type.WRONG_CARD_NAME, e.getType());
		}
	}
}
