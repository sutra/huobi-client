package org.oxerr.huobi.websocket.dto.response.message;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;
import org.oxerr.huobi.websocket.dto.response.AbstractResponseTest;
import org.oxerr.huobi.websocket.dto.response.marketdata.MarketOverview;
import org.oxerr.huobi.websocket.dto.response.marketdata.payload.MarketOverviewPayload;

public class MarketOverviewTest extends AbstractResponseTest {

	@Test
	public void test() throws IOException {
		MarketOverview r = fromJson("marketOverview.json", MarketOverview.class);
		assertEquals(1, r.getVersion());
		assertEquals("marketOverview", r.getMsgType());
		assertEquals("btccny", r.getSymbolId());

		MarketOverviewPayload p = r.getPayload();
		assertEquals("btccny", p.getSymbolId());
		assertEquals(new BigDecimal("2214"), p.getPriceNew());
		assertEquals(new BigDecimal("2338.44"), p.getPriceOpen());
		assertEquals(new BigDecimal("2341.97"), p.getPriceHigh());
		assertEquals(new BigDecimal("2191.24"), p.getPriceLow());
		assertEquals(new BigDecimal("2213.95"), p.getPriceAsk());
		assertEquals(new BigDecimal("2213.64"), p.getPriceBid());
		assertEquals(new BigDecimal("212459736.82506347"), p.getTotalVolume());
		assertEquals(new BigDecimal("93652.9105000004"), p.getTotalAmount());
	}

}
