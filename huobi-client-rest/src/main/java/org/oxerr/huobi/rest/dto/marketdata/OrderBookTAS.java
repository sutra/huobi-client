package org.oxerr.huobi.rest.dto.marketdata;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Order book and TAS(Times and sales)
 */
public class OrderBookTAS {

	/**
	 * Sell 10.
	 */
	private final OrderBookObject[] sells;

	/**
	 * Buy 10.
	 */
	private final OrderBookObject[] buys;

	/**
	 * Times and sales.
	 */
	private final TradeObject[] trades;

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
	private final TopObject[] topSell;

	/**
	 * Buy 5.
	 */
	private final TopObject[] topBuy;

	public OrderBookTAS(
			@JsonProperty("sells") final OrderBookObject[] sells,
			@JsonProperty("buys") final OrderBookObject[] buys,
			@JsonProperty("trades") final TradeObject[] trades,
			@JsonProperty("p_new") final BigDecimal pNew,
			@JsonProperty("level") final BigDecimal level,
			@JsonProperty("amount") final BigDecimal amount,
			@JsonProperty("total") final BigDecimal total,
			@JsonProperty("amp") final BigDecimal amp,
			@JsonProperty("p_open") final BigDecimal pOpen,
			@JsonProperty("p_high") final BigDecimal pHigh,
			@JsonProperty("p_low") final BigDecimal pLow,
			@JsonProperty("p_last") final BigDecimal pLast,
			@JsonProperty("top_sell") final TopObject[] topSell,
			@JsonProperty("top_buy") final TopObject[] topBuy){
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

	public OrderBookObject[] getSells() {
		return sells;
	}

	public OrderBookObject[] getBuys() {
		return buys;
	}

	public TradeObject[] getTrades() {
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

	public TopObject[] getTopSell() {
		return topSell;
	}

	public TopObject[] getTopBuy() {
		return topBuy;
	}

}
