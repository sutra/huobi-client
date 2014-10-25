package org.oxerr.huobi.websocket.dto.response;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;
import org.oxerr.huobi.websocket.dto.response.historydata.ReqMarketDepthTopResponse;
import org.oxerr.huobi.websocket.dto.response.payload.ReqMarketDepthTopPayload;

public class ReqMarketDepthTopResponseTest extends AbstractResponseTest {

	@Test
	public void test() throws IOException {
		ReqMarketDepthTopResponse r = fromJson("reqMarketDepthTop.json",
				ReqMarketDepthTopResponse.class);
		assertEquals(1, r.getVersion());
		assertEquals("reqMarketDepthTop", r.getMsgType());
		assertEquals(0, r.getRequestIndex());
		assertEquals(200, r.getRetCode());
		assertEquals("", r.getRetMsg());

		ReqMarketDepthTopPayload p = r.getPayload();
		assertEquals("btccny", p.getSymbolId());
		assertEquals(1414083399339L, p.getTime());
		assertEquals(1414083399339L, p.getVersion());

		assertEquals("累计买单", p.getBidName());
		assertEquals(new BigDecimal("2207.13"), p.getBidPrice()[0]);
		assertEquals(new BigDecimal("2207.01"), p.getBidPrice()[1]);
		assertEquals(new BigDecimal("0.9"), p.getBidTotal()[0]);
		assertEquals(new BigDecimal("1.644"), p.getBidTotal()[1]);
		assertEquals(new BigDecimal("0.9"), p.getBidAmount()[0]);
		assertEquals(new BigDecimal("0.744"), p.getBidAmount()[1]);

		assertEquals("累计卖单", p.getAskName());
		assertEquals(new BigDecimal("2207.9"), p.getAskPrice()[0]);
		assertEquals(new BigDecimal("2208.02"), p.getAskPrice()[1]);
		assertEquals(new BigDecimal("0.0167"), p.getAskTotal()[0]);
		assertEquals(new BigDecimal("0.7167"), p.getAskTotal()[1]);
		assertEquals(new BigDecimal("0.0167"), p.getAskAmount()[0]);
		assertEquals(new BigDecimal("0.7"), p.getAskAmount()[1]);
	}

}
