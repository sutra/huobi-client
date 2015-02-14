package org.oxerr.huobi.websocket.dto.response;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;
import org.oxerr.huobi.websocket.dto.response.payload.ReqSymbolDetailPayload;
import org.oxerr.huobi.websocket.dto.response.service.ReqSymbolDetailResponse;

public class ReqSymbolDetailResponseTest extends AbstractResponseTest {

	@Test
	public void testReqSymbolDetailResponseBtccny() throws IOException {
		ReqSymbolDetailResponse r = fromJson("reqSymbolDetail-btccny.json",
				ReqSymbolDetailResponse.class);
		assertEquals(1, r.getVersion());
		assertEquals("reqSymbolDetail", r.getMsgType());
		assertEquals(0, r.getRequestIndex());
		assertEquals(200, r.getRetCode());
		assertEquals("", r.getRetMsg());

		ReqSymbolDetailPayload p = r.getPayload();
		assertArrayEquals(new String[] { "btccny", }, p.getSymbolId());
		assertArrayEquals(new String[] { "比特币人民币", }, p.getSymbolName());
		assertArrayEquals(new String[] { "btc", }, p.getCryptoId());
		assertArrayEquals(new String[] { "bitcoin", }, p.getCryptoName());
		assertArrayEquals(new String[] { "huobi", }, p.getExchangeId());
		assertArrayEquals(new String[] { "火币", }, p.getExchangeName());
		assertArrayEquals(new String[] { "cny", }, p.getCurrencyId());
		assertArrayEquals(new String[] { "人民币", }, p.getCurrencyName());
		assertArrayEquals(new BigDecimal[] { new BigDecimal("21000000"), },
				p.getTotal());
		assertArrayEquals(new BigDecimal[] { new BigDecimal("12000000"), },
				p.getSuply());
		assertArrayEquals(new String[] { "火币上的比特币人民币交易", }, p.getIntroduction());
	}

	@Test
	public void testReqSymbolDetailResponseBtccnyLtccny() throws IOException {
		ReqSymbolDetailResponse r = fromJson(
				"reqSymbolDetail-btccny,ltccny.json",
				ReqSymbolDetailResponse.class);
		assertEquals(1, r.getVersion());
		assertEquals("reqSymbolDetail", r.getMsgType());
		assertEquals(0, r.getRequestIndex());
		assertEquals(200, r.getRetCode());
		assertEquals("", r.getRetMsg());

		ReqSymbolDetailPayload p = r.getPayload();
		assertArrayEquals(new String[] { "btccny", "ltccny", }, p.getSymbolId());
		assertArrayEquals(new String[] { "比特币人民币", "莱特币人民币", },
				p.getSymbolName());
		assertArrayEquals(new String[] { "btc", "ltc", }, p.getCryptoId());
		assertArrayEquals(new String[] { "bitcoin", "litecoin", },
				p.getCryptoName());
		assertArrayEquals(new String[] { "huobi", "huobi", }, p.getExchangeId());
		assertArrayEquals(new String[] { "火币", "火币", }, p.getExchangeName());
		assertArrayEquals(new String[] { "cny", "cny", }, p.getCurrencyId());
		assertArrayEquals(new String[] { "人民币", "人民币", }, p.getCurrencyName());
		assertArrayEquals(new BigDecimal[] { new BigDecimal("21000000"),
				new BigDecimal("84000000"), }, p.getTotal());
		assertArrayEquals(new BigDecimal[] { new BigDecimal("12000000"),
				new BigDecimal("40000000"), }, p.getSuply());
		assertArrayEquals(new String[] { "火币上的比特币人民币交易", "火币上的莱特币人民币交易", },
				p.getIntroduction());
	}

}
