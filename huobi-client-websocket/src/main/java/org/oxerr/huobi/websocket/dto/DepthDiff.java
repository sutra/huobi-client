package org.oxerr.huobi.websocket.dto;

import org.oxerr.huobi.websocket.dto.response.marketdata.payload.Update;

public interface DepthDiff {

	long getVersion();
	long getVersionOld();

	Update getBidInsert();
	int[] getBidDelete();
	Update getBidUpdate();

	Update getAskInsert();
	int[] getAskDelete();
	Update getAskUpdate();

}
