package org.oxerr.huobi.websocket.dto.response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;
import org.oxerr.huobi.websocket.dto.response.historydata.ReqMarketDetailResponse;
import org.oxerr.huobi.websocket.dto.response.payload.ReqMarketDetailPayload;

public class ReqMarketDetailResponseTest extends AbstractResponseTest {

	@Test
	public void test() throws IOException {
		ReqMarketDetailResponse r = fromJson("reqMarketDetail.json",
				ReqMarketDetailResponse.class);
		assertEquals(1, r.getVersion());
		assertEquals("reqMarketDetail", r.getMsgType());
		assertEquals(0, r.getRequestIndex());
		assertEquals(200, r.getRetCode());
		assertEquals("", r.getRetMsg());

		ReqMarketDetailPayload p = r.getPayload();
		assertEquals("btccny", p.getSymbolId());
		assertEquals(new BigDecimal("2207.92"), p.getPriceNew());
		assertEquals(new BigDecimal("2205.05"), p.getPriceOpen());
		assertEquals(new BigDecimal("2214"), p.getPriceHigh());
		assertEquals(new BigDecimal("2205"), p.getPriceLow());
		assertEquals(new BigDecimal("2208.05"), p.getPriceLast());
		assertEquals(2208, p.getLevel());
		assertEquals(new BigDecimal("0"), p.getAmount());
		assertEquals(new BigDecimal("95586.0705000004"), p.getTotalAmount());
		assertNull(p.getAmp());

		assertEquals(new BigDecimal("2207.92"), p.getTrades().getPrice()[0]);
		assertEquals(new BigDecimal("2208"), p.getTrades().getPrice()[1]);
		assertEquals(1414083528L, p.getTrades().getTime()[0]);
		assertEquals(1414083528L, p.getTrades().getTime()[1]);
		assertEquals(new BigDecimal("0.4671"), p.getTrades().getAmount()[0]);
		assertEquals(new BigDecimal("0.3909"), p.getTrades().getAmount()[1]);
		assertEquals(2, p.getTrades().getDirection()[0]);
		assertEquals(2, p.getTrades().getDirection()[1]);
		assertEquals(1, p.getTrades().getDirection()[2]);

		assertEquals(new BigDecimal("2208.73"), p.getBids().getPrice()[0]);
		assertEquals(new BigDecimal("2207.92"), p.getBids().getPrice()[1]);
		assertEquals(1, p.getBids().getLevel()[0]);
		assertEquals(1, p.getBids().getLevel()[1]);
		assertEquals(new BigDecimal("0.881"), p.getBids().getAmount()[0]);
		assertEquals(new BigDecimal("2.0029"), p.getBids().getAmount()[1]);
		assertEquals(new BigDecimal("0.881"), p.getBids().getAccuAmount()[0]);
		assertEquals(new BigDecimal("2.8838999999999997"), p.getBids()
				.getAccuAmount()[1]);

		assertEquals(new BigDecimal("2209.88"), p.getAsks().getPrice()[0]);
		assertEquals(new BigDecimal("2209.89"), p.getAsks().getPrice()[1]);
		assertEquals(1, p.getAsks().getLevel()[0]);
		assertEquals(1, p.getAsks().getLevel()[1]);
		assertEquals(new BigDecimal("0.184"), p.getAsks().getAmount()[0]);
		assertEquals(new BigDecimal("0.6762"), p.getAsks().getAmount()[1]);
		assertEquals(new BigDecimal("0.184"), p.getAsks().getAccuAmount()[0]);
		assertEquals(new BigDecimal("0.8602000000000001"), p.getAsks()
				.getAccuAmount()[1]);

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
		assertEquals(new BigDecimal("216462007.07223046"), p.getTotalVolume());
	}

}
