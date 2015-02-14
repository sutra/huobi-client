package org.oxerr.huobi.rest.service.polling;

import java.io.IOException;
import java.math.BigDecimal;

import org.oxerr.huobi.rest.HuobiAdapters;

import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.dto.account.AccountInfo;
import com.xeiam.xchange.exceptions.NotAvailableFromExchangeException;
import com.xeiam.xchange.service.polling.account.PollingAccountService;

public class HuobiAccountService extends HuobiAccountServiceRaw implements
		PollingAccountService {

	public HuobiAccountService(Exchange exchange) {
		super(exchange);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AccountInfo getAccountInfo() throws IOException {
		return HuobiAdapters.adaptAccountInfo(getHUOBIAccountInfo());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String withdrawFunds(String currency, BigDecimal amount,
			String address) {
		throw new NotAvailableFromExchangeException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String requestDepositAddress(String currency, String... args) {
		throw new NotAvailableFromExchangeException();
	}

}
