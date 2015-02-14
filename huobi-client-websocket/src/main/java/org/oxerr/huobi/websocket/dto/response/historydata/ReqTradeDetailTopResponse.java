package org.oxerr.huobi.websocket.dto.response.historydata;

import org.oxerr.huobi.websocket.dto.response.ReqResponse;
import org.oxerr.huobi.websocket.dto.response.payload.ReqTradeDetailTopPayload;

/**
 * Response of top trade details.
 */
public class ReqTradeDetailTopResponse extends
		ReqResponse<ReqTradeDetailTopPayload> {

	public ReqTradeDetailTopResponse(int version, String msgType, int retCode,
			String retMsg) {
		super(version, msgType, retCode, retMsg);
	}

}
