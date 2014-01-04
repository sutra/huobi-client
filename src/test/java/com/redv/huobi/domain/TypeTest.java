package com.redv.huobi.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TypeTest {

	@Test
	public void testToType() {
		assertTrue(Type.toType("do_sell") == Type.SELL);
	}

	@Test
	public void testType() {
		assertEquals("do_sell", Type.SELL.toString());
	}

}
