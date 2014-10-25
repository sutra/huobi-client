package com.redv.huobi.dto.marketdata;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Order book and TAS(Times and sales)
 */
public class HUOBIOrderBookTAS {

	/**
	 * Sell 10.
	 */
	private final HUOBIOrderBookObject[] sells;

	/**
	 * Buy 10.
	 */
	private final HUOBIOrderBookObject[] buys;

	/**
	 * Times and sales.
	 */
	private final HUOBITradeObject[] trades;

	/**
	 * Latest.
	 */
	private final BigDecimal pNew;

	/**
	 * Percent change.
	 */
	private final BigDecimal level;

	/**
	 * Volume.
	 */
	private final BigDecimal amount;

	/**
	 * Total(RMB).
	 */
	private final BigDecimal total;

	private final BigDecimal amp;

	/**
	 * Open.
	 */
	private final BigDecimal pOpen;

	/**
	 * High.
	 */
	private final BigDecimal pHigh;

	/**
	 * Low.
	 */
	private final BigDecimal pLow;

	/**
	 * Close.
	 */
	private final BigDecimal pLast;

	/**
	 * Sell 5.
	 */
	private final HUOBITopObject[] topSell;

	/**
	 * Buy 5.
	 */
	private final HUOBITopObject[] topBuy;

	public HUOBIOrderBookTAS(
			@JsonProperty("sells") final HUOBIOrderBookObject[] sells,
			@JsonProperty("buys") final HUOBIOrderBookObject[] buys,
			@JsonProperty("trades") final HUOBITradeObject[] trades,
			@JsonProperty("p_new") final BigDecimal pNew,
			@JsonProperty("level") final BigDecimal level,
			@JsonProperty("amount") final BigDecimal amount,
			@JsonProperty("total") final BigDecimal total,
			@JsonProperty("amp") final BigDecimal amp,
			@JsonProperty("p_open") final BigDecimal pOpen,
			@JsonProperty("p_high") final BigDecimal pHigh,
			@JsonProperty("p_low") final BigDecimal pLow,
			@JsonProperty("p_last") final BigDecimal pLast,
			@JsonProperty("top_sell") final HUOBITopObject[] topSell,
			@JsonProperty("top_buy") final HUOBITopObject[] topBuy){
		this.sells = sells;
		this.buys = buys;
		this.trades = trades;
		this.pNew = pNew;
		this.level = level;
		this.amount = amount;
		this.total = total;
		this.amp = amp;
		this.pOpen = pOpen;
		this.pHigh = pHigh;
		this.pLow = pLow;
		this.pLast = pLast;
		this.topSell = topSell;
		this.topBuy = topBuy;
	}

	public HUOBIOrderBookObject[] getSells() {
		return sells;
	}

	public HUOBIOrderBookObject[] getBuys() {
		return buys;
	}

	public HUOBITradeObject[] getTrades() {
		return trades;
	}

	public BigDecimal getPNew() {
		return pNew;
	}

	public BigDecimal getLevel() {
		return level;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public BigDecimal getAmp() {
		return amp;
	}

	public BigDecimal getPOpen() {
		return pOpen;
	}

	public BigDecimal getPHigh() {
		return pHigh;
	}

	public BigDecimal getPLow() {
		return pLow;
	}

	public BigDecimal getPLast() {
		return pLast;
	}

	public HUOBITopObject[] getTopSell() {
		return topSell;
	}

	public HUOBITopObject[] getTopBuy() {
		return topBuy;
	}

}
