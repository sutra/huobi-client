package org.oxerr.huobi.websocket.dto.response;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;
import org.oxerr.huobi.websocket.dto.response.historydata.ReqTradeDetailTopResponse;
import org.oxerr.huobi.websocket.dto.response.payload.ReqTradeDetailTopPayload;

public class ReqTradeDetailTopResponseTest extends AbstractResponseTest {

	@Test
	public void test() throws IOException {
		ReqTradeDetailTopResponse r = fromJson("reqTradeDetailTop.json",
				ReqTradeDetailTopResponse.class);
		assertEquals(1, r.getVersion());
		assertEquals("reqTradeDetailTop", r.getMsgType());
		assertEquals(0, r.getRequestIndex());
		assertEquals(200, r.getRetCode());
		assertEquals("", r.getRetMsg());

		ReqTradeDetailTopPayload p = r.getPayload();
		assertEquals("btccny", p.getSymbolId());
		assertEquals(23857357L, p.getTradeId()[0]);
		assertEquals(23857358L, p.getTradeId()[1]);
		assertEquals(new BigDecimal("2208.05"), p.getPrice()[0]);
		assertEquals(new BigDecimal("2207.05"), p.getPrice()[1]);
		assertEquals(1414083362L, p.getTime()[0]);
		assertEquals(1414083371L, p.getTime()[1]);
		assertEquals(new BigDecimal("0.3117"), p.getAmount()[0]);
		assertEquals(new BigDecimal("0.7"), p.getAmount()[1]);
		assertEquals(1, p.getDirection()[0]);
		assertEquals(2, p.getDirection()[1]);

		assertEquals(new BigDecimal("2208.05"), p.getTopAsks()[0].getPrice()[0]);
		assertEquals(new BigDecimal("2208.8"), p.getTopAsks()[0].getPrice()[1]);
		assertEquals(0, p.getTopAsks()[0].getLevel()[0]);
		assertEquals(0, p.getTopAsks()[0].getLevel()[1]);
		assertEquals(new BigDecimal("0.1883"), p.getTopAsks()[0].getAmount()[0]);
		assertEquals(new BigDecimal("0.0167"), p.getTopAsks()[0].getAmount()[1]);
		assertEquals(new BigDecimal("0.1883"),
				p.getTopAsks()[0].getAccuAmount()[0]);
		assertEquals(new BigDecimal("0.205"),
				p.getTopAsks()[0].getAccuAmount()[1]);

		assertEquals(new BigDecimal("2208.05"), p.getTopAsks()[1].getPrice()[0]);
		assertEquals(new BigDecimal("2208.8"), p.getTopAsks()[1].getPrice()[1]);
		assertEquals(0, p.getTopAsks()[1].getLevel()[0]);
		assertEquals(0, p.getTopAsks()[1].getLevel()[1]);
		assertEquals(new BigDecimal("0.1883"), p.getTopAsks()[0].getAmount()[0]);
		assertEquals(new BigDecimal("0.0167"), p.getTopAsks()[0].getAmount()[1]);
		assertEquals(new BigDecimal("0.1883"),
				p.getTopAsks()[1].getAccuAmount()[0]);
		assertEquals(new BigDecimal("0.205"),
				p.getTopAsks()[1].getAccuAmount()[1]);

		assertEquals(new BigDecimal("2207.02"), p.getTopBids()[0].getPrice()[0]);
		assertEquals(new BigDecimal("2207.01"), p.getTopBids()[0].getPrice()[1]);
		assertEquals(0, p.getTopBids()[0].getLevel()[0]);
		assertEquals(0, p.getTopBids()[0].getLevel()[1]);
		assertEquals(new BigDecimal("0.1839"), p.getTopBids()[0].getAmount()[0]);
		assertEquals(new BigDecimal("1.4201"), p.getTopBids()[0].getAmount()[1]);
		assertEquals(new BigDecimal("0.1839"),
				p.getTopBids()[0].getAccuAmount()[0]);
		assertEquals(new BigDecimal("1.6039999999999999"),
				p.getTopBids()[0].getAccuAmount()[1]);

		assertEquals(new BigDecimal("2207.05"), p.getTopBids()[1].getPrice()[0]);
		assertEquals(new BigDecimal("2207.01"), p.getTopBids()[1].getPrice()[1]);
		assertEquals(0, p.getTopBids()[1].getLevel()[0]);
		assertEquals(0, p.getTopBids()[1].getLevel()[1]);
		assertEquals(new BigDecimal("0.171"), p.getTopBids()[1].getAmount()[0]);
		assertEquals(new BigDecimal("1.4201"), p.getTopBids()[1].getAmount()[1]);
		assertEquals(new BigDecimal("0.171"),
				p.getTopBids()[1].getAccuAmount()[0]);
		assertEquals(new BigDecimal("1.5911"),
				p.getTopBids()[1].getAccuAmount()[1]);
	}

}
