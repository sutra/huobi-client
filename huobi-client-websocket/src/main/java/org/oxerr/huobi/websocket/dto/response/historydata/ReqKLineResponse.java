package org.oxerr.huobi.websocket.dto.response.historydata;

import org.oxerr.huobi.websocket.dto.response.ReqResponse;
import org.oxerr.huobi.websocket.dto.response.payload.ReqKLinePayload;

/**
 * Response of history candlestick data.
 */
public class ReqKLineResponse extends ReqResponse<ReqKLinePayload> {

	public ReqKLineResponse(int version, String msgType, int retCode,
			String retMsg) {
		super(version, msgType, retCode, retMsg);
	}

}
