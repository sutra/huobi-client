package org.oxerr.huobi.rest;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.oxerr.huobi.rest.HuobiAdapters;
import org.oxerr.huobi.rest.dto.marketdata.Depth;
import org.oxerr.huobi.rest.dto.marketdata.DepthTest;
import org.oxerr.huobi.rest.dto.marketdata.UnmarshalTest;

import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.dto.marketdata.OrderBook;
import com.xeiam.xchange.dto.trade.LimitOrder;

public class HuobiAdaptersTest extends UnmarshalTest {

	@Test
	public void testAdaptOrderBook() throws IOException {
		Depth huobiDepth = readValue(DepthTest.class, "depth.json", Depth.class);
		OrderBook orderBook = HuobiAdapters.adaptOrderBook(huobiDepth, CurrencyPair.BTC_CNY);

		List<LimitOrder> asks = orderBook.getAsks();
		List<LimitOrder> bids = orderBook.getBids();

		// asks should be sorted ascending
		// ask 0.01@90.7
		assertEquals(new BigDecimal("90.7"), asks.get(0).getLimitPrice());
		assertEquals(new BigDecimal("0.01"), asks.get(0).getTradableAmount());

		// ask 0.5@90.8
		assertEquals(new BigDecimal("90.8"), asks.get(1).getLimitPrice());
		assertEquals(new BigDecimal("0.5"), asks.get(1).getTradableAmount());

		// bids should be sorted deascending
		// bid 79.243@86.06
		assertEquals(new BigDecimal("86.06"), bids.get(0).getLimitPrice());
		assertEquals(new BigDecimal("79.243"), bids.get(0).getTradableAmount());

		// bid 0.02@86.05
		assertEquals(new BigDecimal("86.05"), bids.get(1).getLimitPrice());
		assertEquals(new BigDecimal("0.02"), bids.get(1).getTradableAmount());
	}

}
