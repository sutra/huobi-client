package org.oxerr.huobi.rest.dto.marketdata;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;
import org.oxerr.huobi.rest.dto.marketdata.Ticker;
import org.oxerr.huobi.rest.dto.marketdata.TickerObject;

public class TickerTest extends UnmarshalTest {

	@Test
	public void test() throws IOException {
		TickerObject ticker = readValue(getClass(), "ticker.json", Ticker.class).getTicker();
		assertEquals(new BigDecimal("86.48"), ticker.getHigh());
		assertEquals(new BigDecimal("79.75"), ticker.getLow());
		assertEquals(new BigDecimal("83.9"), ticker.getLast());
		assertEquals(new BigDecimal("2239560.1752883"), ticker.getVol());
		assertEquals(new BigDecimal("83.88"), ticker.getBuy());
		assertEquals(new BigDecimal("83.9"), ticker.getSell());
	}

}
