package com.redv.huobi.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HUOBIPlaceOrderResult extends HUOBIError {

	private final String result;
	private final long id;

	public HUOBIPlaceOrderResult(
			@JsonProperty("code") final int code,
			@JsonProperty("msg") final String msg,
			@JsonProperty("time") final long time,
			@JsonProperty("result") final String result,
			@JsonProperty("id") final long id) {
		super(code, msg, time);
		this.result = result;
		this.id = id;
	}

	public String getResult() {
		return result;
	}

	public long getId() {
		return id;
	}

}
