package org.oxerr.huobi.websocket.event;

import org.oxerr.huobi.websocket.dto.response.Response;
import org.oxerr.huobi.websocket.dto.response.payload.Payload;

/**
 * The listener interface for receiving Huobi exchange response.
 */
public interface ResponseListener {

	void onResponse(Response<? extends Payload> response);

}
