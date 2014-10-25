package org.oxerr.huobi.websocket.dto.request.historydata;

import org.oxerr.huobi.websocket.dto.request.AbstractSymbolIdRequest;

/**
 * Request of top trade details.
 */
public class ReqTradeDetailTopRequest extends AbstractSymbolIdRequest {

	private int count;

	public ReqTradeDetailTopRequest(int version, String symbolId) {
		super(version, "reqTradeDetailTop", symbolId);
	}

	/**
	 * @return 获取明细条数，缺省50条
	 */
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
