package org.oxerr.huobi.rest.service.polling;

import java.io.IOException;

import org.oxerr.huobi.rest.HuobiAdapters;

import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.dto.marketdata.OrderBook;
import com.xeiam.xchange.dto.marketdata.Ticker;
import com.xeiam.xchange.dto.marketdata.Trades;
import com.xeiam.xchange.service.polling.marketdata.PollingMarketDataService;

public class HuobiMarketDataService extends HuobiMarketDataServiceRaw implements
		PollingMarketDataService {

	public HuobiMarketDataService(Exchange exchange) {
		super(exchange);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Ticker getTicker(CurrencyPair currencyPair, Object... args)
			throws IOException {
		return HuobiAdapters.adaptTicker(
			getHUOBITicker(currencyPair.baseSymbol.toLowerCase()),
			currencyPair);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args)
			throws IOException {
		return HuobiAdapters.adaptOrderBook(
			getHUOBIDepth(currencyPair.baseSymbol.toLowerCase()),
			currencyPair);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Trades getTrades(CurrencyPair currencyPair, Object... args)
			throws IOException {
		return HuobiAdapters.adaptTrades(
				getHUOBIDetail(currencyPair.baseSymbol.toLowerCase()),
				currencyPair);
	}

}
