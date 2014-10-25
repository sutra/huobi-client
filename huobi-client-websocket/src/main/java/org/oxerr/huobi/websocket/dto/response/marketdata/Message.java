package org.oxerr.huobi.websocket.dto.response.marketdata;

import org.oxerr.huobi.websocket.dto.response.Response;
import org.oxerr.huobi.websocket.dto.response.payload.Payload;

/**
 * Push message.
 */
public abstract class Message<T extends Payload> extends Response<T> {

	private final String symbolId;

	public Message(int version, String msgType, String symbolId, T payload) {
		super(version, msgType);
		this.symbolId = symbolId;
		setPayload(payload);
	}

	public String getSymbolId() {
		return symbolId;
	}

}
