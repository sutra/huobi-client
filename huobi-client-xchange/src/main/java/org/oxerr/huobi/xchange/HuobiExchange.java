package org.oxerr.huobi.xchange;

import java.util.Arrays;
import java.util.List;

import org.oxerr.huobi.xchange.service.streaming.HuobiSocketIOService;
import org.oxerr.huobi.xchange.service.streaming.HuobiStreamingConfiguration;

import com.redv.huobi.HUOBIExchange;
import com.redv.huobi.service.polling.HUOBIAccountService;
import com.redv.huobi.service.polling.HUOBIMarketDataService;
import com.redv.huobi.service.polling.HUOBITradeService;
import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.ExchangeSpecification;
import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.service.streaming.ExchangeStreamingConfiguration;
import com.xeiam.xchange.service.streaming.StreamingExchangeService;

/**
 * Entry point to the XChange APIs.
 */
public class HuobiExchange extends HUOBIExchange implements Exchange {

	/**
	 * The parameter name of the symbols that will focus on.
	 */
	public static final String SYMBOLS_PARAMETER = "symbols";

	public static final String TRADE_PASSWORD_PARAMETER = "trade_password";

	public static final String WEBSOCKET_URI_KEY = "websocket.uri";

	private static final List<CurrencyPair> SYMBOLS = Arrays.asList(
			CurrencyPair.BTC_CNY,
			CurrencyPair.LTC_CNY);

	@Override
	public void applySpecification(ExchangeSpecification exchangeSpecification) {
		super.applySpecification(exchangeSpecification);
		pollingMarketDataService = new HUOBIMarketDataService(exchangeSpecification);
		if (exchangeSpecification.getApiKey() != null) {
			pollingAccountService = new HUOBIAccountService(exchangeSpecification);
			pollingTradeService = new HUOBITradeService(exchangeSpecification);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ExchangeSpecification getDefaultExchangeSpecification() {
		ExchangeSpecification spec = new ExchangeSpecification(getClass());
		spec.setExchangeName("Huobi");
		spec.setExchangeDescription(
			"Huobi.com is the leading platform in cryptocurrency transactions, "
				+ "which committed in providing professional, secure, "
				+ "trustworthy services for investors around the world.");
		spec.setPlainTextUri("http://market.huobi.com/staticmarket");
		spec.setSslUri("https://api.huobi.com");
		spec.setExchangeSpecificParametersItem(SYMBOLS_PARAMETER, SYMBOLS);
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
