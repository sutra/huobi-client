package org.oxerr.huobi.rest.service.polling;

import org.oxerr.huobi.rest.Huobi;
import org.oxerr.huobi.rest.service.HuobiDigest;

import si.mazi.rescu.RestProxyFactory;

import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.ExchangeSpecification;

public class HuobiBaseTradeService extends HuobiBasePollingService {

	protected final Huobi huobi;
	protected final String accessKey;
	protected final HuobiDigest digest;

	protected HuobiBaseTradeService(Exchange exchange) {
		super(exchange);
		ExchangeSpecification spec = exchange.getExchangeSpecification();
		final String baseUrl = spec.getSslUri();
		huobi = RestProxyFactory.createProxy(Huobi.class, baseUrl);
		accessKey = spec.getApiKey();
		digest = new HuobiDigest(spec.getSecretKey());
	}

	protected long nextCreated() {
		return System.currentTimeMillis() / 1000;
	}

}
