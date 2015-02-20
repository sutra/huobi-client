package org.oxerr.huobi.websocket.dto.request.historydata;

import java.time.Instant;

import org.oxerr.huobi.websocket.dto.Period;
import org.oxerr.huobi.websocket.dto.request.AbstractSymbolIdRequest;

/**
 * Request of history candlestick data.
 */
public class ReqKLineRequest extends AbstractSymbolIdRequest {

	private final Period period;

	private Instant from, to;

	/**
	 * @param version 终端版本
	 * @param symbolId 交易代码
	 * @param period k线类型
	 */
	public ReqKLineRequest(int version, String symbolId, Period period) {
		super(version, "reqKLine", symbolId);
		this.period = period;
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

	public Period getPeriod() {
		return period;
	}

}
