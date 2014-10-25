package org.oxerr.huobi.websocket.dto.response.historydata;

import org.oxerr.huobi.websocket.dto.response.ReqResponse;
import org.oxerr.huobi.websocket.dto.response.payload.ReqMarketDepthPayload;

/**
 * Response of market-depth.
 */
public class ReqMarketDepthResponse extends ReqResponse<ReqMarketDepthPayload> {

	public ReqMarketDepthResponse(int version, String msgType, int retCode,
			String retMsg) {
		super(version, msgType, retCode, retMsg);
	}

}
