package com.redv.huobi.service.polling;

import si.mazi.rescu.RestProxyFactory;

import com.redv.huobi.HUOBI;
import com.redv.huobi.dto.account.HUOBIAccountInfo;
import com.redv.huobi.service.HUOBIDigest;
import com.xeiam.xchange.ExchangeSpecification;

public class HUOBIAccountServiceRaw extends HUOBIBasePollingService {

	private final HUOBI huobi;
	private final String accessKey;
	private final HUOBIDigest digest;

	protected HUOBIAccountServiceRaw(ExchangeSpecification exchangeSpecification) {
		super(exchangeSpecification);
		final String baseUrl = exchangeSpecification.getSslUri();
		huobi = RestProxyFactory.createProxy(HUOBI.class, baseUrl);
		accessKey = exchangeSpecification.getApiKey();
		digest = new HUOBIDigest(exchangeSpecification.getSecretKey());
	}

	public HUOBIAccountInfo getHUOBIAccountInfo() {
		return huobi.getAccountInfo("get_account_info",
				accessKey, nextCreated(), digest);
	}

	private long nextCreated() {
		return System.currentTimeMillis() / 1000;
	}

}
