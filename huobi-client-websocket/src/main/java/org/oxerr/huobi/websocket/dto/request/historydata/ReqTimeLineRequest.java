package org.oxerr.huobi.websocket.dto.request.historydata;

import org.oxerr.huobi.websocket.dto.request.AbstractSymbolIdRequest;

/**
 * Request of history time-interval.
 */
public class ReqTimeLineRequest extends AbstractSymbolIdRequest {

	private long from, to;

	public ReqTimeLineRequest(int version, String symbolId) {
		super(version, "reqTimeLine", symbolId);
	}

	/**
	 * @return 开始时间，默认最近300条的时间区间。
	 */
	public long getFrom() {
		return from;
	}

	public void setFrom(long from) {
		this.from = from;
	}

	/**
	 * @return 结束时间，默认到最新。
	 */
	public long getTo() {
		return to;
	}

	public void setTo(long to) {
		this.to = to;
	}

}
