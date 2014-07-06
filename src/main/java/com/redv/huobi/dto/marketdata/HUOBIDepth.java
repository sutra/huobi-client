package com.redv.huobi.dto.marketdata;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HUOBIDepth {

	private final BigDecimal[][] asks;
	private final BigDecimal[][] bids;

	public HUOBIDepth(
			@JsonProperty("asks") final BigDecimal[][] asks,
			@JsonProperty("bids") final BigDecimal[][] bids) {
		this.asks = asks;
		this.bids = bids;
	}

	public BigDecimal[][] getAsks() {
		return asks;
	}

	public BigDecimal[][] getBids() {
		return bids;
	}

}
