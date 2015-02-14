package org.oxerr.huobi.websocket.dto.response;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;
import org.oxerr.huobi.websocket.dto.response.historydata.ReqTimeLineResponse;
import org.oxerr.huobi.websocket.dto.response.payload.ReqTimeLinePayload;

public class ReqTimeLineResponseTest extends AbstractResponseTest {

	@Test
	public void test() throws IOException {
		ReqTimeLineResponse r = fromJson("reqTimeLine.json", ReqTimeLineResponse.class);
		assertEquals(1, r.getVersion());
		assertEquals("reqTimeLine", r.getMsgType());
		assertEquals(0, r.getRequestIndex());
		assertEquals(200, r.getRetCode());
		assertEquals("", r.getRetMsg());

		ReqTimeLinePayload p = r.getPayload();
		assertEquals("btccny", p.getSymbolId());
		assertEquals(1378035000L, p.getTime()[0]);
		assertEquals(1378035060L, p.getTime()[1]);
		assertEquals(new BigDecimal("805"), p.getPriceLast()[0]);
		assertEquals(new BigDecimal("805"), p.getPriceLast()[1]);
		assertEquals(new BigDecimal("8.748000000000001"), p.getAmount()[0]);
		assertEquals(new BigDecimal("0"), p.getAmount()[1]);
		assertEquals(new BigDecimal("7049.681800000001"), p.getVolume()[0]);
		assertEquals(6, p.getCount()[0]);
	}

}
