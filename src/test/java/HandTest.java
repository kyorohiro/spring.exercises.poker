import static org.junit.Assert.assertEquals;

import org.junit.Test;

import core.Hand;

public class HandTest {

	@Test
	public void createHand() {
		Hand hand = Hand.createUnsafe("S1 S2 S3 S4 S5");
		//System.out.println(">>>>"+hand.toString());
		assertEquals("","S1 S2 S3 S4 S5",hand.toString());
	}

	@Test
	public void isFlush() {
		Hand hand = Hand.createUnsafe("S1 S2 S3 S4 S5");
		assertEquals("S1 S2 S3 S4 S5", true, hand.isFlush());

		hand = Hand.createUnsafe("S2 S3 S4 S5 D6");
		assertEquals("S2 S3 S4 S5 D6", true, hand.isFlush());

		hand = Hand.createUnsafe("S1 S10 S11 S12 D13");
		assertEquals("S1 S10 S11 S12 D13", true, hand.isFlush());
	}
}