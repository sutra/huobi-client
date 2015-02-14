package org.oxerr.huobi.websocket.dto.request.service;

import org.oxerr.huobi.websocket.dto.request.Request;
import org.oxerr.huobi.websocket.dto.request.marketdata.Message;

/**
 * Request of subscribing push message.
 */
public class ReqMsgSubscribeRequest extends Request {

	private final Message symbolList;

	public ReqMsgSubscribeRequest(int version, Message symbolList) {
		super(version, "reqMsgSubscribe");
		this.symbolList = symbolList;
	}

	public Message getSymbolList() {
		return symbolList;
	}

}
