package org.oxerr.huobi.websocket.dto.request.historydata;

import java.time.Instant;

import org.oxerr.huobi.websocket.dto.request.AbstractSymbolIdRequest;

/**
 * Request of history time-interval.
 */
public class ReqTimeLineRequest extends AbstractSymbolIdRequest {

	private Instant from, to;

	public ReqTimeLineRequest(int version, String symbolId) {
		super(version, "reqTimeLine", symbolId);
	}

	/**
	 * @return 开始时间，默认最近300条的时间区间。
	 */
	public Instant getFrom() {
		return from;
	}

	public void setFrom(Instant from) {
		this.from = from;
	}

	/**
	 * @return 结束时间，默认到最新。
	 */
	public Instant getTo() {
		return to;
	}

	public void setTo(Instant to) {
		this.to = to;
	}

}
