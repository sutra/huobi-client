package com.redv.huobi;

import java.util.Arrays;
import java.util.List;

import com.redv.huobi.service.polling.HUOBIAccountService;
import com.redv.huobi.service.polling.HUOBIMarketDataService;
import com.redv.huobi.service.polling.HUOBITradeService;
import com.xeiam.xchange.BaseExchange;
import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.ExchangeSpecification;
import com.xeiam.xchange.currency.CurrencyPair;

/**
 * @deprecated Use org.oxerr.huobi.xchange.HuobiExchange instead.
 */
@Deprecated
public class HUOBIExchange extends BaseExchange implements Exchange {

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
		spec.setExchangeName("HUOBI");
		spec.setExchangeDescription(
			"Huobi.com is the leading platform in cryptocurrency transactions, "
				+ "which committed in providing professional, secure, "
				+ "trustworthy services for investors around the world.");
		spec.setPlainTextUri("http://market.huobi.com/staticmarket");
		spec.setSslUri("https://api.huobi.com");
		spec.setExchangeSpecificParametersItem(SYMBOLS_PARAMETER, SYMBOLS);
		return spec;
	}

}
