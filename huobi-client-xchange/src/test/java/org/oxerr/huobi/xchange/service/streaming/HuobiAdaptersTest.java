package org.oxerr.huobi.xchange.service.streaming;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.xeiam.xchange.currency.CurrencyPair;

public class HuobiAdaptersTest {

	@Test
	public void testAdaptCurrencyPair() {
		assertEquals(CurrencyPair.BTC_CNY, HuobiSocketIOAdapters.adaptCurrencyPair("btccny"));
	}

}
