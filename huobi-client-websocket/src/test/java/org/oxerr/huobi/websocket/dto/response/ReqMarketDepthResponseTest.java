package org.oxerr.huobi.websocket.dto.response;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;
import org.oxerr.huobi.websocket.dto.Percent;
import org.oxerr.huobi.websocket.dto.response.historydata.ReqMarketDepthResponse;
import org.oxerr.huobi.websocket.dto.response.payload.ReqMarketDepthPayload;

public class ReqMarketDepthResponseTest extends AbstractResponseTest {

	@Test
	public void test() throws IOException {
		ReqMarketDepthResponse r = fromJson("reqMarketDepth.json",
				ReqMarketDepthResponse.class);
		assertEquals(1, r.getVersion());
		assertEquals("reqMarketDepth", r.getMsgType());
		assertEquals(0, r.getRequestIndex());
		assertEquals(200, r.getRetCode());
		assertEquals("", r.getRetMsg());

		ReqMarketDepthPayload p = r.getPayload();
		assertEquals("btccny", p.getSymbolId());
		assertEquals(Percent.PERCENT10, p.getPercent());
		assertEquals(1414083454000L, p.getTime());
		assertEquals(1414083454000L, p.getVersion());

		assertEquals("累计买单", p.getBidName());
		assertEquals(new BigDecimal("2207.92"), p.getBidPrice()[0]);
		assertEquals(new BigDecimal("2206.438174496644"), p.getBidPrice()[1]);
		assertEquals(new BigDecimal("2.47"), p.getBidTotal()[0]);
		assertEquals(new BigDecimal("27.9097"), p.getBidTotal()[1]);
		assertEquals(new BigDecimal("2.47"), p.getBidAmount()[0]);
		assertEquals(new BigDecimal("25.4397"), p.getBidAmount()[1]);

		assertEquals("累计卖单", p.getAskName());
		assertEquals(new BigDecimal("2208.1"), p.getAskPrice()[0]);
		assertEquals(new BigDecimal("2209.581946308725"), p.getAskPrice()[1]);
		assertEquals(new BigDecimal("0.4909"), p.getAskTotal()[0]);
		assertEquals(new BigDecimal("1.3719"), p.getAskTotal()[1]);
		assertEquals(new BigDecimal("0.4909"), p.getAskAmount()[0]);
		assertEquals(new BigDecimal("0.881"), p.getAskAmount()[1]);
	}

}
