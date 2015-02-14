package org.oxerr.huobi.websocket.dto.response;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;
import org.oxerr.huobi.websocket.dto.Period;
import org.oxerr.huobi.websocket.dto.response.historydata.ReqKLineResponse;
import org.oxerr.huobi.websocket.dto.response.payload.ReqKLinePayload;

public class ReqKLineResponseTest extends AbstractResponseTest {

	@Test
	public void test() throws IOException {
		ReqKLineResponse r = fromJson("reqKLine.json", ReqKLineResponse.class);
		assertEquals(1, r.getVersion());
		assertEquals("reqKLine", r.getMsgType());
		assertEquals(0, r.getRequestIndex());
		assertEquals(200, r.getRetCode());
		assertEquals("", r.getRetMsg());

		ReqKLinePayload p = r.getPayload();
		assertEquals("btccny", p.getSymbolId());
		assertEquals(Period.KLINE_1MIN, p.getPeriod());
		assertEquals(1378035000L, p.getTime()[0]);
		assertEquals(1378035060L, p.getTime()[1]);
		assertEquals(new BigDecimal("806.37"), p.getPriceOpen()[0]);
		assertEquals(new BigDecimal("805"), p.getPriceOpen()[1]);
		assertEquals(new BigDecimal("810"), p.getPriceHigh()[0]);
		assertEquals(new BigDecimal("805"), p.getPriceHigh()[1]);
		assertEquals(new BigDecimal("803.2"), p.getPriceLow()[0]);
		assertEquals(new BigDecimal("805"), p.getPriceLow()[1]);
		assertEquals(new BigDecimal("805"), p.getPriceLast()[0]);
		assertEquals(new BigDecimal("805"), p.getPriceLast()[1]);
		assertEquals(new BigDecimal("8.748000000000001"), p.getAmount()[0]);
		assertEquals(new BigDecimal("0"), p.getAmount()[1]);
		assertEquals(new BigDecimal("7049.681800000001"), p.getVolume()[0]);
		assertEquals(new BigDecimal("0"), p.getVolume()[1]);
		assertEquals(6, p.getCount()[0]);
		assertEquals(0, p.getCount()[1]);
	}

}
