package com.redv.huobi.service.polling;

import java.io.IOException;

import com.redv.huobi.HUOBIAdapters;
import com.xeiam.xchange.ExchangeSpecification;
import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.dto.marketdata.OrderBook;
import com.xeiam.xchange.dto.marketdata.Ticker;
import com.xeiam.xchange.dto.marketdata.Trades;
import com.xeiam.xchange.service.polling.marketdata.PollingMarketDataService;

public class HUOBIMarketDataService extends HUOBIMarketDataServiceRaw implements
		PollingMarketDataService {

	public HUOBIMarketDataService(ExchangeSpecification exchangeSpecification) {
		super(exchangeSpecification);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Ticker getTicker(CurrencyPair currencyPair, Object... args)
			throws IOException {
		return HUOBIAdapters.adaptTicker(
			getHUOBITicker(currencyPair.baseSymbol.toLowerCase()),
			currencyPair);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args)
			throws IOException {
		return HUOBIAdapters.adaptOrderBook(
			getHUOBIDepth(currencyPair.baseSymbol.toLowerCase()),
			currencyPair);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Trades getTrades(CurrencyPair currencyPair, Object... args)
			throws IOException {
		return HUOBIAdapters.adaptTrades(
				getHUOBIDetail(currencyPair.baseSymbol.toLowerCase()),
				currencyPair);
	}

}
