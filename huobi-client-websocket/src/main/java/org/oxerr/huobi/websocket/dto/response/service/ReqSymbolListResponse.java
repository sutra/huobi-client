package org.oxerr.huobi.websocket.dto.response.service;

import org.oxerr.huobi.websocket.dto.response.ReqResponse;
import org.oxerr.huobi.websocket.dto.response.payload.ReqSymbolListPayload;

/**
 * Response of symbol list.
 */
public class ReqSymbolListResponse extends ReqResponse<ReqSymbolListPayload> {

	public ReqSymbolListResponse(int version, String msgType, int retCode,
			String retMsg) {
		super(version, msgType, retCode, retMsg);
	}

}
