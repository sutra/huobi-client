package org.oxerr.huobi.websocket.dto.response.message;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;
import org.oxerr.huobi.websocket.dto.Period;
import org.oxerr.huobi.websocket.dto.response.AbstractResponseTest;
import org.oxerr.huobi.websocket.dto.response.marketdata.LastKLine;
import org.oxerr.huobi.websocket.dto.response.marketdata.payload.LastKLinePayload;

public class LastKLineTest extends AbstractResponseTest {

	@Test
	public void test() throws IOException {
		LastKLine r = fromJson("lastKLine.json", LastKLine.class);
		assertEquals(1, r.getVersion());
		assertEquals("lastKLine", r.getMsgType());
		assertEquals("btccny", r.getSymbolId());

		LastKLinePayload p = r.getPayload();
		assertEquals(1414079040L, p.get_id());
		assertEquals("btccny", p.getSymbolId());
		assertEquals(1414079040L, p.getTime());
		assertEquals(Period.KLINE_1MIN, p.getPeriod());
		assertEquals(new BigDecimal("2214.98"), p.getPriceOpen());
		assertEquals(new BigDecimal("2215"), p.getPriceHigh());
		assertEquals(new BigDecimal("2213.95"), p.getPriceLow());
		assertEquals(new BigDecimal("2213.95"), p.getPriceLast());
		assertEquals(new BigDecimal("4.6168"), p.getAmount());
		assertEquals(new BigDecimal("10223.067363000002"), p.getVolume());
		assertEquals(13, p.getCount());
	}

}
