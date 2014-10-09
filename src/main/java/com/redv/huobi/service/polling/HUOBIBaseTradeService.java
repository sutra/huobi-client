package com.redv.huobi.service.polling;

import si.mazi.rescu.RestProxyFactory;

import com.redv.huobi.HUOBI;
import com.redv.huobi.service.HUOBIDigest;
import com.xeiam.xchange.ExchangeSpecification;

public class HUOBIBaseTradeService extends HUOBIBasePollingService {

	protected final HUOBI huobi;
	protected final String accessKey;
	protected final HUOBIDigest digest;

	protected HUOBIBaseTradeService(ExchangeSpecification exchangeSpecification) {
		super(exchangeSpecification);
		final String baseUrl = exchangeSpecification.getSslUri();
		huobi = RestProxyFactory.createProxy(HUOBI.class, baseUrl);
		accessKey = exchangeSpecification.getApiKey();
		digest = new HUOBIDigest(exchangeSpecification.getSecretKey());
	}

	protected long nextCreated() {
		return System.currentTimeMillis() / 1000;
	}

}
