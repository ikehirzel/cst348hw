package com.ikehirzel.wk1hw2;

import android.content.Intent;

import junit.framework.TestCase;

import static org.junit.Assert.assertNotEquals;

public class MainActivityTest extends TestCase {

	public void testGetLandingPageIntent() {
		MainActivity activity = new MainActivity();
		Intent intent = activity.getLandingPageIntent("din_djarin", "baby_yoda_ftw");
		assertNotEquals(intent, null);
		intent = activity.getLandingPageIntent("din_djarin", "beskar_4_ever");
		assertEquals(intent, null);
	}
}