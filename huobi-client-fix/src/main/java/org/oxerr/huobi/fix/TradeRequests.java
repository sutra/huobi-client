package org.oxerr.huobi.fix;

import java.util.UUID;

import org.oxerr.huobi.fix.field.AccReqID;
import org.oxerr.huobi.fix.fix44.AccountInfoRequest;

import quickfix.field.Account;
import quickfix.field.Symbol;

/**
 * Various methods for constructing requests for trading interface.
 */
public final class TradeRequests {

	private TradeRequests() {
	}

	public static AccountInfoRequest buildAccountInfoRequest(String accessKey,
			String symbol) {
		AccountInfoRequest message = new AccountInfoRequest();
		message.set(new Account(accessKey));
		message.set(new AccReqID(UUID.randomUUID().toString()));
		message.set(new Symbol(symbol));
		return message;
	}

}
