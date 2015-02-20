package org.oxerr.huobi.rest.service.polling;

import static org.oxerr.huobi.rest.HuobiExchange.SYMBOLS_PARAMETER;

import java.util.List;

import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.ExchangeSpecification;
import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.service.BaseExchangeService;
import com.xeiam.xchange.service.polling.BasePollingService;

public class HuobiBasePollingService extends BaseExchangeService implements
		BasePollingService {

	private final List<CurrencyPair> symbols;

	@SuppressWarnings("unchecked")
	protected HuobiBasePollingService(Exchange exchange) {
		super(exchange);
		ExchangeSpecification spec = exchange.getExchangeSpecification();
		symbols = (List<CurrencyPair>) spec
				.getExchangeSpecificParametersItem(SYMBOLS_PARAMETER);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CurrencyPair> getExchangeSymbols() {
		return symbols;
	}

}
