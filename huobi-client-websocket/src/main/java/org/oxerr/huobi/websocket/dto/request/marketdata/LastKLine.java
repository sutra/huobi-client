package org.oxerr.huobi.websocket.dto.request.marketdata;

import org.oxerr.huobi.websocket.dto.Period;

/**
 * Message for subscribing to push the last data details from candlestick chart.
 */
public class LastKLine extends AbstractPush {

	private final Period period;

	public LastKLine(String symbolId, PushType pushType, Period period) {
		super(symbolId, pushType);
		this.period = period;
	}

	public Period getPeriod() {
		return period;
	}

}
