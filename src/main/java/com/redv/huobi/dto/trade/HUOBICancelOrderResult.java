package com.redv.huobi.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HUOBICancelOrderResult extends HUOBIError {

	private final String result;

	public HUOBICancelOrderResult(
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
