package org.oxerr.huobi.rest.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.oxerr.huobi.rest.domain.Type;

public class TypeTest {

	@Test
	public void testToType() {
		assertTrue(Type.toType("do_sell") == Type.SELL);
		assertTrue(Type.delegationToType("卖出") == Type.SELL);
	}

	@Test
	public void testType() {
		assertEquals("do_sell", Type.SELL.toString());
		assertEquals("卖出", Type.SELL.getDelegationType());
	}

}
