package com.redv.huobi.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TradeParam extends AbstractObject {

	private static final long serialVersionUID = 2014010401L;

	@JsonProperty("a")
	private String action;

	@JsonProperty
	private BigDecimal price;

	@JsonProperty
	private BigDecimal amount;

	public TradeParam() {
	}

	public TradeParam(Type action, BigDecimal price, BigDecimal amount) {
		this.action = action.toString();
		this.price = price;
		this.amount = amount;
	}

	public String getAction() {
		return action;
	}

	public void setAction(Type action) {
		this.action = action.toString();
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String toJson() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(this);
	}

}
