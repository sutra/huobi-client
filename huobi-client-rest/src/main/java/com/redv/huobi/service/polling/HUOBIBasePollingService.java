package com.redv.huobi.service.polling;

import static com.redv.huobi.HUOBIExchange.SYMBOLS_PARAMETER;

import java.util.Collection;

import com.xeiam.xchange.ExchangeSpecification;
import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.service.BaseExchangeService;
import com.xeiam.xchange.service.polling.BasePollingService;

public class HUOBIBasePollingService extends BaseExchangeService implements
		BasePollingService {

	private final Collection<CurrencyPair> symbols;

	/**
	 * @param exchangeSpecification
	 */
	@SuppressWarnings("unchecked")
	protected HUOBIBasePollingService(
			ExchangeSpecification exchangeSpecification) {
		super(exchangeSpecification);
		symbols = (Collection<CurrencyPair>) exchangeSpecification
				.getExchangeSpecificParametersItem(SYMBOLS_PARAMETER);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<CurrencyPair> getExchangeSymbols() {
		return symbols;
	}

}
