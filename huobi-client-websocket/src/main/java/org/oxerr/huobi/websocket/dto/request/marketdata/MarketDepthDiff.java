package org.oxerr.huobi.websocket.dto.request.marketdata;

import org.oxerr.huobi.websocket.dto.Percent;

/**
 * Message for subscribing to push market-depth difference.
 */
public class MarketDepthDiff extends AbstractPush {

	private final Percent percent;

	public MarketDepthDiff(String symbolId, PushType pushType, Percent percent) {
		super(symbolId, pushType);
		this.percent = percent;
	}

	public Percent getPercent() {
		return percent;
	}

}
