package org.oxerr.huobi.websocket.dto.response.message;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;
import org.oxerr.huobi.websocket.dto.response.AbstractResponseTest;
import org.oxerr.huobi.websocket.dto.response.marketdata.MarketDetail;
import org.oxerr.huobi.websocket.dto.response.marketdata.payload.MarketDetailPayload;

public class MarketDetailTest extends AbstractResponseTest {

	@Test
	public void test() throws IOException {
		MarketDetail r = fromJson("marketDetail.json", MarketDetail.class);
		assertEquals(1, r.getVersion());
		assertEquals(1414079069L, r.get_id());
		assertEquals("marketDetail", r.getMsgType());
		assertEquals("btccny", r.getSymbolId());
		assertEquals(1414079069L, r.getIdCur());
		assertEquals(0, r.getIdPrev());

		MarketDetailPayload p = r.getPayload();
		assertEquals("btccny", p.getSymbolId());
		assertEquals(new BigDecimal("2214"), p.getPriceNew());
		assertEquals(new BigDecimal("2338.44"), p.getPriceOpen());
		assertEquals(new BigDecimal("2341.97"), p.getPriceHigh());
		assertEquals(new BigDecimal("2191.24"), p.getPriceLow());
		assertEquals(new BigDecimal("2215"), p.getPriceLast());
		assertEquals(2214, p.getLevel());
		assertEquals(new BigDecimal("0"), p.getAmount());
		assertEquals(new BigDecimal("93652.9105000004"), p.getTotalAmount());
		assertNull(p.getAmp());

		assertEquals(new BigDecimal("2214"), p.getTrades().getPrice()[0]);
		assertEquals(new BigDecimal("2214"), p.getTrades().getPrice()[1]);
		assertEquals(1414079069L, p.getTrades().getTime()[0]);
		assertEquals(1414079067L, p.getTrades().getTime()[1]);
		assertEquals(new BigDecimal("0.05"), p.getTrades().getAmount()[0]);
		assertEquals(new BigDecimal("0.02"), p.getTrades().getAmount()[1]);
		assertEquals(1, p.getTrades().getDirection()[0]);
		assertEquals(1, p.getTrades().getDirection()[1]);
		assertEquals(1, p.getTrades().getDirection()[2]);

		assertEquals(new BigDecimal("2213.64"), p.getBids().getPrice()[0]);
		assertEquals(new BigDecimal("2213"), p.getBids().getPrice()[1]);
		assertEquals(0, p.getBids().getLevel()[0]);
		assertEquals(0, p.getBids().getLevel()[1]);
		assertEquals(new BigDecimal("0.01"), p.getBids().getAmount()[0]);
		assertEquals(new BigDecimal("1.7402"), p.getBids().getAmount()[1]);
		assertEquals(new BigDecimal("0.01"), p.getBids().getAccuAmount()[0]);
		assertEquals(new BigDecimal("1.7502"), p.getBids().getAccuAmount()[1]);

		assertEquals(new BigDecimal("2213.95"), p.getAsks().getPrice()[0]);
		assertEquals(new BigDecimal("2214"), p.getAsks().getPrice()[1]);
		assertEquals(0, p.getAsks().getLevel()[0]);
		assertEquals(0, p.getAsks().getLevel()[1]);
		assertEquals(new BigDecimal("1.0208"), p.getAsks().getAmount()[0]);
		assertEquals(new BigDecimal("4.0423"), p.getAsks().getAmount()[1]);
		assertEquals(new BigDecimal("1.0208"), p.getAsks().getAccuAmount()[0]);
		assertEquals(new BigDecimal("5.0631"), p.getAsks().getAccuAmount()[1]);

		assertEquals(new BigDecimal("0"), p.getCommissionRatio());
		assertEquals(new BigDecimal("0"), p.getPoor());
		assertEquals(new BigDecimal("0"), p.getUpdownVolume());
		assertEquals(new BigDecimal("0"), p.getUpdownRatio());
		assertEquals(new BigDecimal("0"), p.getPriceAverage());
		assertEquals(new BigDecimal("0"), p.getVolumeRatio());
		assertEquals(new BigDecimal("0"), p.getTurnVolume());
		assertEquals(new BigDecimal("0"), p.getTurnoverRate());
		assertEquals(new BigDecimal("0"), p.getOuterDisc());
		assertEquals(new BigDecimal("0"), p.getInnerDisc());
		assertEquals(new BigDecimal("212459736.82506347"), p.getTotalVolume());
	}
}
