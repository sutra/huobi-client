package org.oxerr.huobi.examples.rest;

import java.io.IOException;
import java.util.Arrays;

import org.oxerr.huobi.xchange.HuobiExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.ExchangeFactory;
import com.xeiam.xchange.ExchangeSpecification;
import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.dto.trade.OpenOrders;
import com.xeiam.xchange.exceptions.ExchangeException;
import com.xeiam.xchange.exceptions.NotAvailableFromExchangeException;
import com.xeiam.xchange.exceptions.NotYetImplementedForExchangeException;
import com.xeiam.xchange.service.polling.trade.PollingTradeService;

public class OpenOrderDemo {

	private static final Logger log = LoggerFactory
			.getLogger(OpenOrderDemo.class);

	public static void main(String[] args) throws ExchangeException,
			NotAvailableFromExchangeException,
			NotYetImplementedForExchangeException, IOException {
		String accessKey = args[0], secretKey = args[1];

		ExchangeSpecification spec = new ExchangeSpecification(
				HuobiExchange.class);
		spec.setApiKey(accessKey);
		spec.setSecretKey(secretKey);
		spec.setExchangeSpecificParametersItem(HuobiExchange.SYMBOLS_PARAMETER,
				Arrays.asList(CurrencyPair.BTC_CNY));

		Exchange exchange = ExchangeFactory.INSTANCE.createExchange(spec);
		PollingTradeService tradeService = exchange.getPollingTradeService();

		OpenOrders openOrders = tradeService.getOpenOrders();
		log.info("open orders: {}", openOrders);
	}

}
