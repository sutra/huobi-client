package org.oxerr.huobi.websocket.dto.request.service;

import org.oxerr.huobi.websocket.dto.request.AbstractSymbolIdListRequest;

/**
 * Request of symbol list.
 */
public class ReqSymbolListRequest extends AbstractSymbolIdListRequest {

	public ReqSymbolListRequest(int version) {
		super(version, "reqSymbolList");
	}

}
