package org.oxerr.huobi.websocket.dto.response.historydata;

import org.oxerr.huobi.websocket.dto.response.ReqResponse;
import org.oxerr.huobi.websocket.dto.response.payload.ReqMarketDepthTopPayload;

/**
 * Response of top market-depth.
 */
public class ReqMarketDepthTopResponse extends
		ReqResponse<ReqMarketDepthTopPayload> {

	public ReqMarketDepthTopResponse(int version, String msgType, int retCode,
			String retMsg) {
		super(version, msgType, retCode, retMsg);
	}

}
