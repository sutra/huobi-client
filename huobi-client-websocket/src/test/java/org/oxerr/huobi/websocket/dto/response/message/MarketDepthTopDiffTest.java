package org.oxerr.huobi.websocket.dto.response.message;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;
import org.oxerr.huobi.websocket.dto.response.AbstractResponseTest;
import org.oxerr.huobi.websocket.dto.response.marketdata.MarketDepthTopDiff;
import org.oxerr.huobi.websocket.dto.response.marketdata.payload.MarketDepthTopDiffPayload;

public class MarketDepthTopDiffTest extends AbstractResponseTest {

	@Test
	public void test() throws IOException {
		MarketDepthTopDiff r = fromJson("marketDepthTopDiff.json",
				MarketDepthTopDiff.class);
		assertEquals(1, r.getVersion());
		assertEquals("marketDepthTopDiff", r.getMsgType());
		assertEquals("btccny", r.getSymbolId());

		MarketDepthTopDiffPayload p = r.getPayload();

		assertEquals(new BigDecimal("2213.01"), p.getBidInsert().getPrice()[0]);
		assertEquals(new BigDecimal("5.6636"), p.getBidInsert().getAmount()[0]);
		assertEquals(1, p.getBidInsert().getRow()[0]);

		assertEquals(149, p.getBidDelete()[0]);

		assertEquals(0, p.getBidUpdate().getPrice().length);
		assertEquals(0, p.getBidUpdate().getAmount().length);
		assertEquals(0, p.getBidUpdate().getRow().length);

		assertEquals(new BigDecimal("2215.98"), p.getAskInsert().getPrice()[0]);
		assertEquals(new BigDecimal("1.5296"), p.getAskInsert().getAmount()[0]);
		assertEquals(9, p.getAskInsert().getRow()[0]);

		assertEquals(149, p.getAskDelete()[0]);

		assertEquals(new BigDecimal("2213.95"), p.getAskUpdate().getPrice()[0]);
		assertEquals(new BigDecimal("0.7708"), p.getAskUpdate().getAmount()[0]);
		assertEquals(0, p.getAskUpdate().getRow()[0]);
	}

}
