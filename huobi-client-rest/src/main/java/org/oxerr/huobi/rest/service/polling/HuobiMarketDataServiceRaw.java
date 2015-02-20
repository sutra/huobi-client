package org.oxerr.huobi.rest.service.polling;

import java.io.IOException;

import org.oxerr.huobi.rest.Huobi;
import org.oxerr.huobi.rest.dto.marketdata.Depth;
import org.oxerr.huobi.rest.dto.marketdata.OrderBookTAS;
import org.oxerr.huobi.rest.dto.marketdata.Ticker;

import si.mazi.rescu.RestProxyFactory;

import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.ExchangeSpecification;

public class HuobiMarketDataServiceRaw extends HuobiBasePollingService {

	private final Huobi huobi;

	protected HuobiMarketDataServiceRaw(Exchange exchange) {
		super(exchange);
		ExchangeSpecification spec = exchange.getExchangeSpecification();
		final String baseUrl = spec.getPlainTextUri();
		huobi = RestProxyFactory.createProxy(Huobi.class, baseUrl);
	}

	public Ticker getHUOBITicker(String symbol) throws IOException {
		return huobi.getTicker(symbol);
	}

	public Depth getHUOBIDepth(String symbol) throws IOException {
		return huobi.getDepth(symbol);
	}

	public String[][] getHUOBIKline(String symbol, String period)
			throws IOException {
		return huobi.getKline(symbol, period);
	}

	public OrderBookTAS getHUOBIDetail(String symbol)
			throws IOException {
		return huobi.getDetail(symbol);
	}

}
