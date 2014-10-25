package org.oxerr.huobi.websocket.dto.response.message;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;
import org.oxerr.huobi.websocket.dto.response.AbstractResponseTest;
import org.oxerr.huobi.websocket.dto.response.marketdata.LastTimeLine;
import org.oxerr.huobi.websocket.dto.response.marketdata.payload.LastTimeLinePayload;

public class LastTimeLineTest extends AbstractResponseTest {

	@Test
	public void test() throws IOException {
		LastTimeLine r = fromJson("lastTimeLine.json", LastTimeLine.class);
		assertEquals(1, r.getVersion());
		assertEquals("lastTimeLine", r.getMsgType());
		assertEquals("btccny", r.getSymbolId());

		LastTimeLinePayload p = r.getPayload();
		assertEquals(1414078140L, p.get_id());
		assertEquals("btccny", p.getSymbolId());
		assertEquals(1414078140, p.getTime());
		assertEquals(new BigDecimal("2219.28"), p.getPriceLast());
		assertEquals(new BigDecimal("8.3297"), p.getAmount());
		assertEquals(new BigDecimal("18488.978877"), p.getVolume());
		assertEquals(7, p.getCount());
	}

}
