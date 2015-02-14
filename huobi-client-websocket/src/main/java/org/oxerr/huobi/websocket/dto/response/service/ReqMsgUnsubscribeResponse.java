package org.oxerr.huobi.websocket.dto.response.service;

import org.oxerr.huobi.websocket.dto.response.ReqResponse;
import org.oxerr.huobi.websocket.dto.response.payload.VoidPayload;

/**
 * Response of canceling push message subscription.
 */
public class ReqMsgUnsubscribeResponse extends ReqResponse<VoidPayload> {

	public ReqMsgUnsubscribeResponse(int version, String msgType, int retCode,
			String retMsg) {
		super(version, msgType, retCode, retMsg);
	}

}
