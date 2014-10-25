package org.oxerr.huobi.websocket.dto.request.marketdata;

/**
 * Message for subscribing to push trade details.
 */
public class TradeDetail extends AbstractPush {

	public TradeDetail(String symbolId, PushType pushType) {
		super(symbolId, pushType);
	}

}
