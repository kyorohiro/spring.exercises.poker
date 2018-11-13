import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import core.Card;
import core.CardType;

public class CoreTest {

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
	
	@Test
	public void testCard() {
		Card card = null;
		try {
			card = Card.create("S1");
		} catch (Exception e) {
		}
		assertEquals("S1 card type", CardType.SPADE, card.getType());
		assertEquals("S1 card number", 1, card.getNumber());

		//
		try {
			card = Card.create("D12");
		} catch (Exception e) {
		}
		assertEquals("D12 card type", CardType.DIAMOND, card.getType());
		assertEquals("D12 card number", 12, card.getNumber());
	

		try {
			card = Card.create("C11");
		} catch (Exception e) {
		}
		assertEquals("C11 card type", CardType.CLUB, card.getType());
		assertEquals("C11 card number", 11, card.getNumber());

		try {
			card = Card.create("H2");
		} catch (Exception e) {
		}
		assertEquals("H2 card type", CardType.HEART, card.getType());
		assertEquals("H2 card number", 2, card.getNumber());
		
		try {
			card = Card.create("A1");
			fail("A1");
		} catch (Exception e) {
			assertTrue("A1 passed", true);
		}
		try {
			card = Card.create("S0");
			fail("S0");
		} catch (Exception e) {
			assertTrue("S0 passed", true);
		}
		try {
			card = Card.create("S14");
			fail("S14");
		} catch (Exception e) {
			assertTrue("S14 passed", true);
		}

		try {
			card = Card.create("");
			fail("");
		} catch (Exception e) {
			assertTrue(" passed", true);
		}

		try {
			card = Card.create(null);
			fail(null);
		} catch (Exception e) {
			assertTrue(" passed", true);
		}
	}

	@Test
	public void testCardSort() {
		Arrays.asList(10,20,30,40,50,60,71,80,90,91);
		List<Card> cards = Arrays.asList(
				Card.createUnsafe("S1"),Card.createUnsafe("S5"),Card.createUnsafe("S3"),
				Card.createUnsafe("D3"),Card.createUnsafe("S4")
		);
		//cards.forEach(card->{System.out.println(">>s>>"+card.getNumber());});		
		cards.sort(Card.newPokaComparator());
		//cards.forEach(card->{System.out.println(">>>>"+card.getNumber());});
		
		assertEquals("sort 0", 1, cards.get(0).getNumber());	
		assertEquals("sort 0", 3, cards.get(1).getNumber());	
		assertEquals("sort 0", 3, cards.get(2).getNumber());	
		assertEquals("sort 0", 4, cards.get(3).getNumber());	
		assertEquals("sort 0", 5, cards.get(4).getNumber());
	}
	
}
