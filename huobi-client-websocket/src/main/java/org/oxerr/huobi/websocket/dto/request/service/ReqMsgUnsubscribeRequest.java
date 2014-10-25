package org.oxerr.huobi.websocket.dto.request.service;

import org.oxerr.huobi.websocket.dto.request.AbstractSymbolIdListRequest;
import org.oxerr.huobi.websocket.dto.request.marketdata.Message;

/**
 * Request of canceling push message subscription.
 */
public class ReqMsgUnsubscribeRequest extends AbstractSymbolIdListRequest {

	private final Message symbolList;

	public ReqMsgUnsubscribeRequest(int version, Message symbolList) {
		super(version, "reqMsgUnsubscribe");
		this.symbolList = symbolList;
	}

	public Message getSymbolList() {
		return symbolList;
	}

}
