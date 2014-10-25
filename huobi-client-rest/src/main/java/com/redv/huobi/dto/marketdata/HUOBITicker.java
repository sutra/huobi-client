package com.redv.huobi.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HUOBITicker {

	private final HUOBITickerObject ticker;

	public HUOBITicker(@JsonProperty("ticker") final HUOBITickerObject ticker) {
		this.ticker = ticker;
	}

	public HUOBITickerObject getTicker() {
		return ticker;
	}

}
