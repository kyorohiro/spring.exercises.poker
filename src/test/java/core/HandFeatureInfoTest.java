
package core;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class HandFeatureInfoTest {
	
	@Test
	public void analyzeAboutFlush_normal() {
		List<Card> cards = Arrays.asList(
				Card.createUnsafe("S1"),Card.createUnsafe("S2"),
				Card.createUnsafe("S3"),Card.createUnsafe("S4"),
				Card.createUnsafe("S5"));
		assertEquals("", true, HandFeatureInfo.isFlush(cards));		
	}

	@Test
	public void analyzeAboutFlush_normal_false() {
		List<Card> cards = Arrays.asList(
				Card.createUnsafe("D1"),Card.createUnsafe("S2"),
				Card.createUnsafe("S3"),Card.createUnsafe("S4"),
				Card.createUnsafe("S5"));
		assertEquals("", false, HandFeatureInfo.isFlush(cards));		
	}

	@Test
	public void analyzeAboutStraight_normal() {
		List<Card> cards = Arrays.asList(
				Card.createUnsafe("D1"),Card.createUnsafe("S2"),
				Card.createUnsafe("S3"),Card.createUnsafe("S4"),
				Card.createUnsafe("S5"));
		assertEquals("", true, HandFeatureInfo.isStraight(cards));
	}
}