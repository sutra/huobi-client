package com.redv.huobi.service.polling;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import com.xeiam.xchange.ExchangeSpecification;
import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.service.BaseExchangeService;
import com.xeiam.xchange.service.polling.BasePollingService;

public class HUOBIBasePollingService extends BaseExchangeService implements
		BasePollingService {

	private static final Collection<CurrencyPair> EXCHANGE_SYMBOLS = Arrays
			.asList(CurrencyPair.BTC_CNY, CurrencyPair.LTC_CNY);

	/**
	 * @param exchangeSpecification
	 */
	protected HUOBIBasePollingService(
			ExchangeSpecification exchangeSpecification) {
		super(exchangeSpecification);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<CurrencyPair> getExchangeSymbols() throws IOException {
		return EXCHANGE_SYMBOLS;
	}

}
