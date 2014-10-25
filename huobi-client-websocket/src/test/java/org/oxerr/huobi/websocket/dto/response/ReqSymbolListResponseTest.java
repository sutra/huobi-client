package org.oxerr.huobi.websocket.dto.response;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.oxerr.huobi.websocket.dto.response.payload.ReqSymbolListPayload;
import org.oxerr.huobi.websocket.dto.response.service.ReqSymbolListResponse;

public class ReqSymbolListResponseTest extends AbstractResponseTest {

	@Test
	public void testReqSymbolListResponse() throws IOException {
		ReqSymbolListResponse r = fromJson("reqSymbolList.json",
				ReqSymbolListResponse.class);
		assertEquals(1, r.getVersion());
		assertEquals("reqSymbolList", r.getMsgType());
		assertEquals(0, r.getRequestIndex());
		assertEquals(200, r.getRetCode());
		assertEquals("", r.getRetMsg());

		ReqSymbolListPayload p = r.getPayload();
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
	}

}
