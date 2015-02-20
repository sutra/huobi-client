package org.oxerr.huobi.rest.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ticker {

	private final TickerObject ticker;

	public Ticker(@JsonProperty("ticker") final TickerObject ticker) {
		this.ticker = ticker;
	}

	public TickerObject getTicker() {
		return ticker;
	}

}
