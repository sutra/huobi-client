package com.redv.huobi;

import com.redv.huobi.service.polling.HUOBIAccountService;
import com.redv.huobi.service.polling.HUOBIMarketDataService;
import com.xeiam.xchange.BaseExchange;
import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.ExchangeSpecification;

public class HUOBIExchange extends BaseExchange implements Exchange {

	@Override
	public void applySpecification(ExchangeSpecification exchangeSpecification) {
		super.applySpecification(exchangeSpecification);
		pollingMarketDataService = new HUOBIMarketDataService(exchangeSpecification);
		if (exchangeSpecification.getApiKey() != null) {
			pollingAccountService = new HUOBIAccountService(exchangeSpecification);
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
		return spec;
	}

}
