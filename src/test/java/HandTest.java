import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import core.Hand;
import core.HandName;

public class HandTest {

	/*
	STRAIGHT_FLUSH("Straight Flush"),
	FOUR("Four of a Kind"),
	FULL_HOUSE("Full House"),
	FLUSH("Flush"),
	STRAIGHT("Straight"),
	THREE("Three of a Kind"),
	TWO("Two Pairs"),
	ONE("One Pairs"),
	NO_PAIRS("No Pairs");
	 */
	@Test
	public void createHand() {
		Hand straighFlush = Hand.createUnsafe("S1 S2 S3 S4 S5");
		assertEquals("", HandName.STRAIGHT_FLUSH, straighFlush.getName());
		//
		Hand four = Hand.createUnsafe("S1 D1 C1 H1 S5");
		assertEquals("", HandName.FOUR, four.getName());

		Hand fullHouse = Hand.createUnsafe("S1 D1 C1 H5 S5");
		assertEquals("", HandName.FULL_HOUSE, fullHouse.getName());

		Hand flush = Hand.createUnsafe("S1 S2 S3 S4 S9");
		assertEquals("", HandName.FLUSH, flush.getName());

		Hand straight = Hand.createUnsafe("S1 S2 D3 S4 S5");
		assertEquals("", HandName.STRAIGHT, straight.getName());

		Hand three = Hand.createUnsafe("S1 D1 C1 S3 S5");
		assertEquals("", HandName.THREE, three.getName());

		Hand twoPairs = Hand.createUnsafe("S1 D1 C3 S3 S5");
		assertEquals("", HandName.TWO, twoPairs.getName());

		Hand pairs = Hand.createUnsafe("S1 D1 S2 S3 S5");
		assertEquals("", HandName.ONE, pairs.getName());

		Hand noPairs = Hand.createUnsafe("S1 D9 S2 S3 S5");
		assertEquals("", HandName.NO_PAIRS, noPairs.getName());

		assertTrue("", straighFlush.getScore() > four.getScore());
		assertTrue("", four.getScore() > fullHouse.getScore());
		assertTrue("", fullHouse.getScore() > flush.getScore());
		assertTrue("", flush.getScore() > straight.getScore());
		assertTrue("", straight.getScore() > three.getScore());
		assertTrue("", twoPairs.getScore() > pairs.getScore());
		assertTrue("", pairs.getScore() > noPairs.getScore());
	}

	@Test
	public void isStraight() {
		Hand hand = Hand.createUnsafe("S1 S2 S3 S4 S5");
		assertEquals("S1 S2 S3 S4 S5", true, hand.isStraight());

		hand = Hand.createUnsafe("S2 S3 S4 S5 D6");
		assertEquals("S2 S3 S4 S5 D6", true, hand.isStraight());

		hand = Hand.createUnsafe("S1 S10 S11 S12 D13");
		assertEquals("S1 S10 S11 S12 D13", true, hand.isStraight());
	}
	
	@Test
	public void isFlush() {
		Hand hand = Hand.createUnsafe("S1 S2 S3 S4 S5");
		assertEquals("S1 S2 S3 S4 S5", true, hand.isFlush());

		hand = Hand.createUnsafe("D2 D3 D4 D5 D6");
		assertEquals("D2 D3 D4 D5 D6", true, hand.isFlush());

		hand = Hand.createUnsafe("S1 S10 S11 S12 D13");
		assertEquals("S1 S10 S11 S12 D13", false, hand.isFlush());
	}

}