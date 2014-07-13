package com.redv.huobi.service.polling;

import java.io.IOException;

import com.redv.huobi.dto.account.HUOBIAccountInfo;
import com.xeiam.xchange.ExchangeSpecification;

public class HUOBIAccountServiceRaw extends HUOBIBaseTradeService {

	protected HUOBIAccountServiceRaw(ExchangeSpecification exchangeSpecification) {
		super(exchangeSpecification);
	}

	public HUOBIAccountInfo getHUOBIAccountInfo() throws IOException {
		return huobi.getAccountInfo("get_account_info",
				accessKey, nextCreated(), digest);
	}

}
