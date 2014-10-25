package org.oxerr.huobi.websocket.dto.request.historydata;

import org.oxerr.huobi.websocket.dto.request.AbstractSymbolIdRequest;

/**
 * Request of market details.
 */
public class ReqMarketDetailRequest extends AbstractSymbolIdRequest {

	public ReqMarketDetailRequest(int version, String symbolId) {
		super(version, "reqMarketDetail", symbolId);
	}

}
