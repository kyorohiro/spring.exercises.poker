package core;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import core.Hand;
import core.HandType;

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
		assertEquals("", HandType.STRAIGHT_FLUSH, straighFlush.getType());
		//
		Hand four = Hand.createUnsafe("S1 D1 C1 H1 S5");
		assertEquals("", HandType.FOUR, four.getType());

		Hand fullHouse = Hand.createUnsafe("S1 D1 C1 H5 S5");
		assertEquals("", HandType.FULL_HOUSE, fullHouse.getType());

		Hand flush = Hand.createUnsafe("S1 S2 S3 S4 S9");
		assertEquals("", HandType.FLUSH, flush.getType());

		Hand straight = Hand.createUnsafe("S1 S2 D3 S4 S5");
		assertEquals("", HandType.STRAIGHT, straight.getType());

		Hand three = Hand.createUnsafe("S1 D1 C1 S3 S5");
		assertEquals("", HandType.THREE, three.getType());

		Hand twoPairs = Hand.createUnsafe("S1 D1 C3 S3 S5");
		assertEquals("", HandType.TWO, twoPairs.getType());

		Hand pairs = Hand.createUnsafe("S1 D1 S2 S3 S5");
		assertEquals("", HandType.ONE, pairs.getType());

		Hand noPairs = Hand.createUnsafe("S1 D9 S2 S3 S5");
		assertEquals("", HandType.NO_PAIRS, noPairs.getType());

		assertTrue("", straighFlush.getScore() > four.getScore());
		assertTrue("", four.getScore() > fullHouse.getScore());
		assertTrue("", fullHouse.getScore() > flush.getScore());
		assertTrue("", flush.getScore() > straight.getScore());
		assertTrue("", straight.getScore() > three.getScore());
		assertTrue("", twoPairs.getScore() > pairs.getScore());
		assertTrue("", pairs.getScore() > noPairs.getScore());
	}

	/*
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
	*/

}