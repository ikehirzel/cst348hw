package com.ikehirzel.wk1hw2;

import android.content.Intent;

import junit.framework.TestCase;

import static org.junit.Assert.assertNotEquals;

public class MainActivityTest extends TestCase {

	public void testIsPassword() {
		int id = 1;
		MainActivity activity = new MainActivity();
		boolean res = activity.isPassword(id, "baby_yoda_ftw");
		assertEquals(res, true);
		res = activity.isPassword(id, "beskar_4_ever");
		assertEquals(res, false);
	}

	public void testGetUserId() {
		MainActivity activity = new MainActivity();
		int id = activity.getUserId("din_djarin");
		assertEquals(id, 1);
		id = activity.getUserId("thisisnotaname");
		assertEquals(id, 0);
		id = activity.getUserId("ike");
		assertEquals(id, 2);
	}

	public void testGetLandingPageIntent() {
		MainActivity activity = new MainActivity();
		Intent intent = activity.getLandingPageIntent("din_djarin", "baby_yoda_ftw");
		assertNotEquals(intent, null);
		intent = activity.getLandingPageIntent("din_djarin", "beskar_4_ever");
		assertEquals(intent, null);
	}
}