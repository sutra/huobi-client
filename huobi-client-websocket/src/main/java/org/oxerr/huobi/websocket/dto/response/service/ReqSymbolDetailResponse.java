package org.oxerr.huobi.websocket.dto.response.service;

import org.oxerr.huobi.websocket.dto.response.ReqResponse;
import org.oxerr.huobi.websocket.dto.response.payload.ReqSymbolDetailPayload;

/**
 * Response of symbol details.
 */
public class ReqSymbolDetailResponse extends ReqResponse<ReqSymbolDetailPayload> {

	public ReqSymbolDetailResponse(int version, String msgType, int retCode,
			String retMsg, ReqSymbolDetailPayload payload) {
		super(version, msgType, retCode, retMsg);
		setPayload(payload);
	}

}
