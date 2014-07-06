package com.redv.huobi.service.polling;

import si.mazi.rescu.RestProxyFactory;

import com.redv.huobi.HUOBI;
import com.redv.huobi.dto.marketdata.HUOBIDepth;
import com.redv.huobi.dto.marketdata.HUOBIOrderBookTAS;
import com.redv.huobi.dto.marketdata.HUOBITicker;
import com.xeiam.xchange.ExchangeSpecification;

public class HUOBIMarketDataServiceRaw extends HUOBIBasePollingService {

	private final HUOBI huobi;

	protected HUOBIMarketDataServiceRaw(
			ExchangeSpecification exchangeSpecification) {
		super(exchangeSpecification);
		final String baseUrl = exchangeSpecification.getPlainTextUri();
		huobi = RestProxyFactory.createProxy(HUOBI.class, baseUrl);
	}

	public HUOBITicker getHUOBITicker(String symbol) {
		return huobi.getTicker(symbol);
	}

	public HUOBIDepth getHUOBIDepth(String symbol) {
		return huobi.getDepth(symbol);
	}

	public String[][] getHUOBIKline(String symbol, String period) {
		return huobi.getKline(symbol, period);
	}

	public HUOBIOrderBookTAS getHUOBIDetail(String symbol) {
		return huobi.getDetail(symbol);
	}

}
