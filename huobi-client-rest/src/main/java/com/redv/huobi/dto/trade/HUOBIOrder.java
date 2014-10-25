package com.redv.huobi.dto.trade;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HUOBIOrder extends HUOBIError {

	private final long id;

	/**
	 * 1 = buy, 2 = sell.
	 */
	private final int type;
	private final BigDecimal orderPrice;
	private final BigDecimal orderAmount;
	private final BigDecimal processedAmount;

	/**
	 * Average price.
	 */
	private final BigDecimal processedPrice;
	private final BigDecimal total;
	private final BigDecimal fee;
	private final BigDecimal vot;
	private final long orderTime;

	/**
	 * 0 = Unfilled,　1 = Partially filled,　2 = Finished,　3 = Cancelled.
	 */
	private final int status;

	public HUOBIOrder(
			@JsonProperty("code") final int code,
			@JsonProperty("msg") final String msg,
			@JsonProperty("time") final long time,
			@JsonProperty("id") final long id,
			@JsonProperty("type") final int type,
			@JsonProperty("order_price") final BigDecimal orderPrice,
			@JsonProperty("order_amount") final BigDecimal orderAmount,
			@JsonProperty("processed_amount") final BigDecimal processedAmount,
			@JsonProperty("processed_price") final BigDecimal processedPrice,
			@JsonProperty("total") final BigDecimal total,
			@JsonProperty("fee") final BigDecimal fee,
			@JsonProperty("vot") final BigDecimal vot,
			@JsonProperty("order_time") final long orderTime,
			@JsonProperty("status") final int status) {
		super(code, msg, time);
		this.id = id;
		this.type = type;
		this.orderPrice = orderPrice;
		this.orderAmount = orderAmount;
		this.processedAmount = processedAmount;
		this.processedPrice = processedPrice;
		this.total = total;
		this.fee = fee;
		this.vot = vot;
		this.orderTime = orderTime;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public int getType() {
		return type;
	}

	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public BigDecimal getProcessedAmount() {
		return processedAmount;
	}

	public BigDecimal getProcessedPrice() {
		return processedPrice;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public BigDecimal getVot() {
		return vot;
	}

	public long getOrderTime() {
		return orderTime;
	}

	public int getStatus() {
		return status;
	}

}
