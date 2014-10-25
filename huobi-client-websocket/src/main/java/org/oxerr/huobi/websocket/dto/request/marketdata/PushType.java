package org.oxerr.huobi.websocket.dto.request.marketdata;

import com.google.gson.annotations.SerializedName;

/**
 * Push type.
 */
public enum PushType {

	@SerializedName("pushLong")
	PUSH_LONG,

	@SerializedName("pushShort")
	PUSH_SHORT;

}
