package org.oxerr.huobi.rest.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CancelOrderResult extends HuobiError {

	private final String result;

	public CancelOrderResult(
			@JsonProperty("code") final int code,
			@JsonProperty("msg") final String msg,
			@JsonProperty("time") final long time,
			@JsonProperty("result") final String result) {
		super(code, msg, time);
		this.result = result;
	}

	public String getResult() {
		return result;
	}

}
