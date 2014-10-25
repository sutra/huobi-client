package org.oxerr.huobi.xchange.service.streaming;

import org.oxerr.huobi.websocket.dto.response.Response;
import org.oxerr.huobi.websocket.dto.response.payload.Payload;

import com.xeiam.xchange.service.streaming.DefaultExchangeEvent;
import com.xeiam.xchange.service.streaming.ExchangeEventType;

public class HuobiExchangeEvent extends DefaultExchangeEvent {

	private final Response<? extends Payload> response;

	public HuobiExchangeEvent(ExchangeEventType exchangeEventType,
			Response<? extends Payload> response, Object payload) {
		super(exchangeEventType, null, payload);
		this.response = response;
	}

	public Response<? extends Payload> getResponse() {
		return response;
	}

}
