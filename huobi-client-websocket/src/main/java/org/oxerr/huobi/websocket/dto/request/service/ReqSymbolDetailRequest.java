package org.oxerr.huobi.websocket.dto.request.service;

import org.oxerr.huobi.websocket.dto.request.AbstractSymbolIdListRequest;

/**
 * Request of symbol details.
 */
public class ReqSymbolDetailRequest extends AbstractSymbolIdListRequest {

	public ReqSymbolDetailRequest(int version, String... symbolIdList) {
		super(version, "reqSymbolDetail");
		setSymbolIdList(symbolIdList);
	}

}
