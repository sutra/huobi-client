package org.oxerr.huobi.websocket.dto.response.message;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;
import org.oxerr.huobi.websocket.dto.response.AbstractResponseTest;
import org.oxerr.huobi.websocket.dto.response.marketdata.TradeDetail;
import org.oxerr.huobi.websocket.dto.response.marketdata.payload.TradeDetailPayload;

public class TradeDetailTest extends AbstractResponseTest {

	@Test
	public void test() throws IOException {
		TradeDetail r = fromJson("tradeDetail.json", TradeDetail.class);
		assertEquals(1, r.getVersion());
		assertEquals(23932618L, r.get_id());
		assertEquals("tradeDetail", r.getMsgType());
		assertEquals("btccny", r.getSymbolId());
		assertEquals(23932618L, r.getIdCur());
		assertEquals(23932617L, r.getIdPrev());
		assertEquals(1414178400L, r.getTimeMax());
		assertEquals(1414178400L, r.getTimeMin());

		TradeDetailPayload p = r.getPayload();

		assertEquals("btccny", p.getSymbolId());
		assertEquals(23932618L, p.getTradeId()[0]);
		assertEquals(new BigDecimal("2186.21"), p.getPrice()[0]);
		assertEquals(1414178400L, p.getTime()[0]);
		assertEquals(new BigDecimal("0.009"), p.getAmount()[0]);
		assertEquals(2, p.getDirection()[0]);

		assertEquals(new BigDecimal("2186.21"), p.getTopBids()[0].getPrice()[0]);
		assertEquals(new BigDecimal("2186.19"), p.getTopBids()[0].getPrice()[1]);
		assertEquals(0, p.getTopBids()[0].getLevel()[0]);
		assertEquals(0, p.getTopBids()[0].getLevel()[1]);
		assertEquals(new BigDecimal("0.7122"), p.getTopBids()[0].getAmount()[0]);
		assertEquals(new BigDecimal("0.916"), p.getTopBids()[0].getAmount()[1]);
		assertEquals(new BigDecimal("0.7122"),
				p.getTopBids()[0].getAccuAmount()[0]);
		assertEquals(new BigDecimal("1.6282"),
				p.getTopBids()[0].getAccuAmount()[1]);

		assertEquals(new BigDecimal("2187.49"), p.getTopAsks()[0].getPrice()[0]);
		assertEquals(new BigDecimal("2187.53"), p.getTopAsks()[0].getPrice()[1]);
		assertEquals(0, p.getTopAsks()[0].getLevel()[0]);
		assertEquals(0, p.getTopAsks()[0].getLevel()[1]);
		assertEquals(new BigDecimal("0.303"), p.getTopAsks()[0].getAmount()[0]);
		assertEquals(new BigDecimal("0.046"), p.getTopAsks()[0].getAmount()[1]);
		assertEquals(new BigDecimal("0.303"),
				p.getTopAsks()[0].getAccuAmount()[0]);
		assertEquals(new BigDecimal("0.349"),
				p.getTopAsks()[0].getAccuAmount()[1]);
	}

}
