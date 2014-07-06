package com.redv.huobi;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.ExchangeFactory;
import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.dto.marketdata.OrderBook;
import com.xeiam.xchange.dto.marketdata.Ticker;
import com.xeiam.xchange.dto.marketdata.Trades;
import com.xeiam.xchange.service.polling.PollingMarketDataService;

public class Main {

	private static final Logger log = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws IOException {
		Exchange exchange = ExchangeFactory.INSTANCE.createExchange(HUOBIExchange.class.getName());
		PollingMarketDataService marketDataService = exchange.getPollingMarketDataService();

		// Ticker
		Ticker ticker = marketDataService.getTicker(CurrencyPair.BTC_CNY);
		log.info("BTC ticker: {}", ticker);

		ticker = marketDataService.getTicker(CurrencyPair.LTC_CNY);
		log.info("LTC ticker: {}", ticker);

		// Depth
		OrderBook depth = marketDataService.getOrderBook(CurrencyPair.BTC_CNY);
		log.info("BTC depth: {}", depth);

		depth = marketDataService.getOrderBook(CurrencyPair.LTC_CNY);
		log.info("LTC depth: {}", depth);

		// Trades
		Trades trades = marketDataService.getTrades(CurrencyPair.BTC_CNY);
		log.info("BTC trades: {}", trades);

		// trades = marketDataService.getTrades(CurrencyPair.LTC_CNY);
		// log.info("LTC trades: {}", trades);
	}

}
