package org.oxerr.huobi.rest.dto.trade;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.oxerr.huobi.rest.dto.marketdata.UnmarshalTest;
import org.oxerr.huobi.rest.dto.trade.PlaceOrderResult;

public class HuobiPlaceOrderResultTest extends UnmarshalTest {

	@Test
	public void test() throws IOException {
		PlaceOrderResult result = readValue(getClass(), "place-order-result.json",
				PlaceOrderResult.class);
		assertEquals("success", result.getResult());
		assertEquals(25841172L, result.getId());
	}

}
