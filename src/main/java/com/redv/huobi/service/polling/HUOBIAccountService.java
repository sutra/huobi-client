package com.redv.huobi.service.polling;

import java.math.BigDecimal;

import com.redv.huobi.HUOBIAdapters;
import com.xeiam.xchange.ExchangeSpecification;
import com.xeiam.xchange.NotAvailableFromExchangeException;
import com.xeiam.xchange.dto.account.AccountInfo;
import com.xeiam.xchange.service.polling.PollingAccountService;

public class HUOBIAccountService extends HUOBIAccountServiceRaw implements
		PollingAccountService {

	public HUOBIAccountService(ExchangeSpecification exchangeSpecification) {
		super(exchangeSpecification);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AccountInfo getAccountInfo() {
		return HUOBIAdapters.adaptAccountInfo(getHUOBIAccountInfo());
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
