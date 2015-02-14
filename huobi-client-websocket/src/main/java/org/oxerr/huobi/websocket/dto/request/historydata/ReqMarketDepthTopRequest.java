package org.oxerr.huobi.websocket.dto.request.historydata;

import org.oxerr.huobi.websocket.dto.request.AbstractSymbolIdRequest;

/**
 * Request of top market-depth.
 */
public class ReqMarketDepthTopRequest extends AbstractSymbolIdRequest {

	public ReqMarketDepthTopRequest(int version, String symbolId) {
		super(version, "reqMarketDepthTop", symbolId);
	}

}
