package org.oxerr.huobi.xchange;

import java.util.List;

import org.oxerr.huobi.xchange.service.streaming.HuobiSocketIOService;
import org.oxerr.huobi.xchange.service.streaming.HuobiStreamingConfiguration;

import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.ExchangeSpecification;
import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.service.streaming.ExchangeStreamingConfiguration;
import com.xeiam.xchange.service.streaming.StreamingExchangeService;

/**
 * Entry point to the XChange APIs.
 */
public class HuobiExchange extends org.oxerr.huobi.rest.HuobiExchange implements
		Exchange {

	public static final String WEBSOCKET_URI_KEY = "websocket.uri";

	@Override
	public void applySpecification(ExchangeSpecification exchangeSpecification) {
		super.applySpecification(exchangeSpecification);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ExchangeSpecification getDefaultExchangeSpecification() {
		ExchangeSpecification spec = super.getDefaultExchangeSpecification();
		spec.setExchangeSpecificParametersItem(WEBSOCKET_URI_KEY,
				"http://hq.huobi.com:80");
		return spec;
	}

	@Override
	public StreamingExchangeService getStreamingExchangeService(
			ExchangeStreamingConfiguration configuration) {
		final HuobiStreamingConfiguration huobiStreamingConfiguration;

		if (configuration == null) {
			@SuppressWarnings("unchecked")
			List<CurrencyPair> currencyPairs = (List<CurrencyPair>) getExchangeSpecification()
					.getExchangeSpecificParametersItem(SYMBOLS_PARAMETER);
			huobiStreamingConfiguration = new HuobiStreamingConfiguration(
					currencyPairs);
		} else if (configuration instanceof HuobiStreamingConfiguration) {
			huobiStreamingConfiguration = (HuobiStreamingConfiguration) configuration;
		} else {
			throw new IllegalArgumentException(
					"Huobi only supports HuobiStreamingConfiguration");
		}

		return new HuobiSocketIOService(getExchangeSpecification(),
				huobiStreamingConfiguration);
	}

}
