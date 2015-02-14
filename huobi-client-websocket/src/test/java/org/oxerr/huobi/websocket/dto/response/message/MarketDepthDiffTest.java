package org.oxerr.huobi.websocket.dto.response.message;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;
import org.oxerr.huobi.websocket.dto.Percent;
import org.oxerr.huobi.websocket.dto.response.AbstractResponseTest;
import org.oxerr.huobi.websocket.dto.response.marketdata.MarketDepthDiff;
import org.oxerr.huobi.websocket.dto.response.marketdata.payload.MarketDepthDiffPayload;

public class MarketDepthDiffTest extends AbstractResponseTest {

	@Test
	public void test() throws IOException {
		MarketDepthDiff r = fromJson("marketDepthDiff.json",
				MarketDepthDiff.class);
		assertEquals(1, r.getVersion());
		assertEquals("marketDepthDiff", r.getMsgType());
		assertEquals("btccny", r.getSymbolId());

		MarketDepthDiffPayload p = r.getPayload();

		assertEquals("btccny", p.getSymbolId());
		assertEquals(Percent.PERCENT10, p.getPercent());
		assertEquals(1414079070000L, p.getVersion());
		assertEquals(1414079069000L, p.getVersionOld());

		assertEquals(0, p.getBidInsert().getPrice().length);
		assertEquals(0, p.getBidInsert().getAmount().length);
		assertEquals(0, p.getBidInsert().getRow().length);

		assertEquals(0, p.getBidDelete().length);

		assertEquals(new BigDecimal("2212.514093959732"), p.getBidUpdate()
				.getPrice()[0]);
		assertEquals(new BigDecimal("2.771"), p.getBidUpdate().getAmount()[0]);
		assertEquals(1, p.getBidUpdate().getRow()[0]);

		assertEquals(new BigDecimal("2436.368"), p.getAskInsert().getPrice()[0]);
		assertEquals(new BigDecimal("2434.881503355705"), p.getAskInsert()
				.getPrice()[1]);
		assertEquals(new BigDecimal("1.2024"), p.getAskInsert().getAmount()[0]);
		assertEquals(new BigDecimal("0"), p.getAskInsert().getAmount()[1]);
		assertEquals(149, p.getAskInsert().getRow()[0]);
		assertEquals(148, p.getAskInsert().getRow()[1]);

		assertEquals(149, p.getAskDelete()[0]);
		assertEquals(148, p.getAskDelete()[1]);

		assertEquals(0, p.getAskUpdate().getPrice().length);
		assertEquals(0, p.getAskUpdate().getAmount().length);
		assertEquals(0, p.getAskUpdate().getRow().length);
	}

}
