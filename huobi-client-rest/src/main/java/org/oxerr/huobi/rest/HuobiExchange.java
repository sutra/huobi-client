package org.oxerr.huobi.rest;

import java.util.Arrays;
import java.util.List;

import org.oxerr.huobi.rest.service.polling.HuobiAccountService;
import org.oxerr.huobi.rest.service.polling.HuobiMarketDataService;
import org.oxerr.huobi.rest.service.polling.HuobiTradeService;

import si.mazi.rescu.SynchronizedValueFactory;

import com.xeiam.xchange.BaseExchange;
import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.ExchangeSpecification;
import com.xeiam.xchange.currency.CurrencyPair;

/**
 * Entry point to the XChange APIs.
 */
public class HuobiExchange extends BaseExchange implements Exchange {

	/**
	 * The parameter name of the symbols that will focus on.
	 */
	public static final String SYMBOLS_PARAMETER = "symbols";

	public static final String TRADE_PASSWORD_PARAMETER = "trade_password";

	private static final List<CurrencyPair> SYMBOLS = Arrays.asList(
			CurrencyPair.BTC_CNY,
			CurrencyPair.LTC_CNY);

	@Override
	public void applySpecification(ExchangeSpecification exchangeSpecification) {
		super.applySpecification(exchangeSpecification);
		pollingMarketDataService = new HuobiMarketDataService(this);
		if (exchangeSpecification.getApiKey() != null) {
			pollingAccountService = new HuobiAccountService(this);
			pollingTradeService = new HuobiTradeService(this);
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
		return spec;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SynchronizedValueFactory<Long> getNonceFactory() {
		return null;
	}

}
