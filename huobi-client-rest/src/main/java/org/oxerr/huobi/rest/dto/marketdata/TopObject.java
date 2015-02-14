package org.oxerr.huobi.rest.dto.marketdata;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TopObject {

	private final BigDecimal price;
	private final BigDecimal level;
	private final BigDecimal amount;
	private final BigDecimal accu;

	public TopObject(
			@JsonProperty("price") final BigDecimal price,
			@JsonProperty("level") final BigDecimal level,
			@JsonProperty("amount") final BigDecimal amount,
			@JsonProperty("accu") final BigDecimal accu) {
		this.price = price;
		this.level = level;
		this.amount = amount;
		this.accu = accu;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getLevel() {
		return level;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public BigDecimal getAccu() {
		return accu;
	}

}
