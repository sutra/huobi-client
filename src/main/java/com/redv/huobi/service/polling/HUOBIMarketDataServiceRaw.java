package com.redv.huobi.service.polling;

import java.io.IOException;

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

	public HUOBITicker getHUOBITicker(String symbol) throws IOException {
		return huobi.getTicker(symbol);
	}

	public HUOBIDepth getHUOBIDepth(String symbol) throws IOException {
		return huobi.getDepth(symbol);
	}

	public String[][] getHUOBIKline(String symbol, String period)
			throws IOException {
		return huobi.getKline(symbol, period);
	}

	public HUOBIOrderBookTAS getHUOBIDetail(String symbol)
			throws IOException {
		return huobi.getDetail(symbol);
	}

}
