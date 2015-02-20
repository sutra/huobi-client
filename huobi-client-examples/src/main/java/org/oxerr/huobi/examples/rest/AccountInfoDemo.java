package org.oxerr.huobi.examples.rest;

import java.io.IOException;

import org.oxerr.huobi.xchange.HuobiExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.ExchangeFactory;
import com.xeiam.xchange.ExchangeSpecification;
import com.xeiam.xchange.dto.account.AccountInfo;
import com.xeiam.xchange.service.polling.account.PollingAccountService;

public class AccountInfoDemo {

	private static final Logger log = LoggerFactory.getLogger(AccountInfoDemo.class);

	public static void main(String[] args) throws IOException {
		final String accessKey = args[0], secretKey = args[1];

		ExchangeSpecification spec = new ExchangeSpecification(HuobiExchange.class);
		spec.setApiKey(accessKey);
		spec.setSecretKey(secretKey);

		Exchange tradeExchange = ExchangeFactory.INSTANCE.createExchange(spec);
		PollingAccountService accountService = tradeExchange.getPollingAccountService();

		// Account info
		AccountInfo accountInfo = accountService.getAccountInfo();
		log.info("Account info: {}", accountInfo);
	}

}
