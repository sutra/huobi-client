package com.redv.huobi.dto.trade;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.redv.huobi.dto.marketdata.UnmarshalTest;

public class HUOBIPlaceOrderResultTest extends UnmarshalTest {

	@Test
	public void test() throws IOException {
		HUOBIPlaceOrderResult result = readValue("place-order-result.json",
				HUOBIPlaceOrderResult.class);
		assertEquals("success", result.getResult());
		assertEquals(25841172L, result.getId());
	}

}
