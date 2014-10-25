package org.oxerr.huobi.websocket.dto.response.marketdata;

import org.oxerr.huobi.websocket.dto.response.marketdata.payload.MarketDepthDiffPayload;

/**
 * Push of market-depth difference.
 */
public class MarketDepthDiff extends Message<MarketDepthDiffPayload> {

	public MarketDepthDiff(int version, String msgType, String symbolId,
			MarketDepthDiffPayload payload) {
		super(version, msgType, symbolId, payload);
	}

}
