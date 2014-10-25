package org.oxerr.huobi.websocket.dto.response.service;

import org.oxerr.huobi.websocket.dto.response.ReqResponse;
import org.oxerr.huobi.websocket.dto.response.payload.VoidPayload;

/**
 * Response of subscribing push message.
 */
public class ReqMsgSubscribeResponse extends ReqResponse<VoidPayload> {

	public ReqMsgSubscribeResponse(int version, String msgType, int retCode,
			String retMsg) {
		super(version, msgType, retCode, retMsg);
	}

}
