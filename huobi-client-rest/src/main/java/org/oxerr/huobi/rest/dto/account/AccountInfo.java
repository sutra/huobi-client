package org.oxerr.huobi.rest.dto.account;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountInfo {

	private final BigDecimal total;
	private final BigDecimal netAsset;
	private final BigDecimal availableCnyDisplay;
	private final BigDecimal availableBtcDisplay;
	private final BigDecimal availableLtcDisplay;
	private final BigDecimal frozenCnyDisplay;
	private final BigDecimal frozenBtcDisplay;
	private final BigDecimal frozenLtcDisplay;
	private final BigDecimal loanCnyDisplay;
	private final BigDecimal loanBtcDisplay;
	private final BigDecimal loanLtcDisplay;

	public AccountInfo(
		@JsonProperty("total") final BigDecimal total,
		@JsonProperty("net_asset") final BigDecimal netAsset,
		@JsonProperty("available_cny_display") final BigDecimal availableCnyDisplay,
		@JsonProperty("available_btc_display") final BigDecimal availableBtcDisplay,
		@JsonProperty("available_ltc_display") final BigDecimal availableLtcDisplay,
		@JsonProperty("frozen_cny_display") final BigDecimal frozenCnyDisplay,
		@JsonProperty("frozen_btc_display") final BigDecimal frozenBtcDisplay,
		@JsonProperty("frozen_ltc_display") final BigDecimal frozenLtcDisplay,
		@JsonProperty("loan_cny_display") final BigDecimal loanCnyDisplay,
		@JsonProperty("loan_btc_display") final BigDecimal loanBtcDisplay,
		@JsonProperty("loan_ltc_display") final BigDecimal loanLtcDisplay) {
		this.total = total;
		this.netAsset = netAsset;
		this.availableCnyDisplay = availableCnyDisplay;
		this.availableBtcDisplay = availableBtcDisplay;
		this.availableLtcDisplay = availableLtcDisplay;
		this.frozenCnyDisplay = frozenCnyDisplay;
		this.frozenBtcDisplay = frozenBtcDisplay;
		this.frozenLtcDisplay = frozenLtcDisplay;
		this.loanCnyDisplay = loanCnyDisplay;
		this.loanBtcDisplay = loanBtcDisplay;
		this.loanLtcDisplay = loanLtcDisplay;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public BigDecimal getNetAsset() {
		return netAsset;
	}

	public BigDecimal getAvailableCnyDisplay() {
		return availableCnyDisplay;
	}

	public BigDecimal getAvailableBtcDisplay() {
		return availableBtcDisplay;
	}

	public BigDecimal getAvailableLtcDisplay() {
		return availableLtcDisplay;
	}

	public BigDecimal getFrozenCnyDisplay() {
		return frozenCnyDisplay;
	}

	public BigDecimal getFrozenBtcDisplay() {
		return frozenBtcDisplay;
	}

	public BigDecimal getFrozenLtcDisplay() {
		return frozenLtcDisplay;
	}

	public BigDecimal getLoanCnyDisplay() {
		return loanCnyDisplay;
	}

	public BigDecimal getLoanBtcDisplay() {
		return loanBtcDisplay;
	}

	public BigDecimal getLoanLtcDisplay() {
		return loanLtcDisplay;
	}

}
