package org.oxerr.huobi.rest.service.polling;

import java.io.IOException;

import org.oxerr.huobi.rest.dto.account.AccountInfo;

import com.xeiam.xchange.Exchange;

public class HuobiAccountServiceRaw extends HuobiBaseTradeService {

	protected HuobiAccountServiceRaw(Exchange exchange) {
		super(exchange);
	}

	public AccountInfo getHUOBIAccountInfo() throws IOException {
		return huobi.getAccountInfo("get_account_info",
				accessKey, nextCreated(), digest);
	}

}
