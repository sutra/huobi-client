package org.oxerr.huobi.rest.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MyTradeInfo extends AbstractObject {

	private static final long serialVersionUID = 2014010501L;

	@JsonProperty("code")
	private int code;

	@JsonProperty("msg")
	private String msg;

	@JsonProperty("extra")
	private Extra extra;

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public Extra getExtra() {
		return extra;
	}

	public static class Extra extends AbstractObject {

		private static final long serialVersionUID = 2014010501L;

		@JsonProperty("sell")
		private Sell sell;

		@JsonProperty("buy")
		private Buy buy;

		@JsonProperty("balance")
		private Balance balance;

		public Sell getSell() {
			return sell;
		}

		public Buy getBuy() {
			return buy;
		}

		public Balance getBalance() {
			return balance;
		}
		public static class Sell extends AbstractObject {

			private static final long serialVersionUID = 2014010501L;

			@JsonProperty("price")
			private BigDecimal price;

			@JsonProperty("amount")
			private BigDecimal amount;

			@JsonProperty("available_btc")
			private BigDecimal availableBtc;

			@JsonProperty("price_ltc")
			private BigDecimal priceLtc;

			@JsonProperty("amount_ltc")
			private BigDecimal amountLtc;

			@JsonProperty("available_ltc")
			private BigDecimal availableLtc;

			public BigDecimal getPrice() {
				return price;
			}

			public BigDecimal getAmount() {
				return amount;
			}

			public BigDecimal getAvailableBtc() {
				return availableBtc;
			}

		}

		public static class Buy extends AbstractObject {

			private static final long serialVersionUID = 2014010501L;

			@JsonProperty("price")
			private BigDecimal price;

			@JsonProperty("amount")
			private BigDecimal amount;

			@JsonProperty("available_cny")
			private BigDecimal availableCny;

			@JsonProperty("price_ltc")
			private BigDecimal priceLtc;

			@JsonProperty("amount_ltc")
			private BigDecimal amountLtc;

			public BigDecimal getPrice() {
				return price;
			}

			public BigDecimal getAmount() {
				return amount;
			}

			public BigDecimal getAvailableCny() {
				return availableCny;
			}

		}

		public static class Balance extends AbstractObject {

			private static final long serialVersionUID = 2014010501L;

			@JsonProperty("id")
			private long id;

			@JsonProperty("initialized_cny")
			private BigDecimal initializedCny;

			@JsonProperty("initialized_btc")
			private BigDecimal initializedBtc;

			@JsonProperty("user_id")
			private long userId;

			@JsonProperty("available_cny")
			private BigDecimal availableCny;

			@JsonProperty("available_btc")
			private BigDecimal availableBtc;

			@JsonProperty("available_usd")
			private BigDecimal availableUsd;

			@JsonProperty("frozen_cny")
			private BigDecimal frozenCny;

			@JsonProperty("frozen_btc")
			private BigDecimal frozenBtc;

			@JsonProperty("frozen_usd")
			private BigDecimal frozenUsd;

			@JsonProperty("debt_bitcoin")
			private BigDecimal debtBitcoin;

			@JsonProperty("debt_rmb")
			private BigDecimal debtRmb;

			@JsonProperty("initialized_ltc")
			private BigDecimal initializedLtc;

			@JsonProperty("available_cny_ltc")
			private BigDecimal availableCnyLtc;

			@JsonProperty("available_ltc")
			private BigDecimal availableLtc;

			@JsonProperty("frozen_cny_ltc")
			private BigDecimal frozenCnyLtc;

			@JsonProperty("frozen_ltc")
			private BigDecimal frozenLtc;

			@JsonProperty("debt_ltc")
			private BigDecimal debtLtc;

			@JsonProperty("total")
			private BigDecimal total;

			@JsonProperty("btc_total")
			private BigDecimal btcTotal;

			@JsonProperty("ltc_total")
			private BigDecimal ltcTotal;

			@JsonProperty("loan_total")
			private BigDecimal loanTotal;

			@JsonProperty("net_asset")
			private BigDecimal netAsset;

			@JsonProperty("loan_net_asset")
			private BigDecimal loanNetAsset;

			@JsonProperty("loan_cny_display")
			private BigDecimal loanCnyDisplay;

			@JsonProperty("loan_btc_display")
			private BigDecimal loanBtcDisplay;

			@JsonProperty("loan_ltc_display")
			private BigDecimal loanLtcDisplay;

			@JsonProperty("available_btc_display")
			private BigDecimal availableBtcDisplay;

			@JsonProperty("available_ltc_display")
			private BigDecimal availableLtcDisplay;

			@JsonProperty("available_cny_display")
			private BigDecimal availableCnyDisplay;

			@JsonProperty("frozen_btc_display")
			private BigDecimal frozenBtcDisplay;

			@JsonProperty("frozen_ltc_display")
			private BigDecimal frozenLtcDisplay;

			@JsonProperty("frozen_cny_display")
			private BigDecimal frozenCnyDisplay;

			public long getId() {
				return id;
			}

			public BigDecimal getInitializedCny() {
				return initializedCny;
			}

			public BigDecimal getInitializedBtc() {
				return initializedBtc;
			}

			public long getUserId() {
				return userId;
			}

			public BigDecimal getAvailableCny() {
				return availableCny;
			}

			public BigDecimal getAvailableBtc() {
				return availableBtc.divide(BigDecimal.TEN.pow(10),
						10,
						RoundingMode.FLOOR);
			}

			public BigDecimal getAvailableUsd() {
				return availableUsd;
			}

			public BigDecimal getFrozenCny() {
				return frozenCny;
			}

			public BigDecimal getFrozenBtc() {
				return frozenBtc;
			}

			public BigDecimal getFrozenUsd() {
				return frozenUsd;
			}

			public BigDecimal getDebtBitcoin() {
				return debtBitcoin;
			}

			public BigDecimal getDebtRmb() {
				return debtRmb;
			}

			public BigDecimal getTotal() {
				return total;
			}

			public BigDecimal getLoanTotal() {
				return loanTotal;
			}

			public BigDecimal getNetAsset() {
				return netAsset;
			}

			public BigDecimal getLoanCnyDisplay() {
				return loanCnyDisplay;
			}

			public BigDecimal getLoanBtcDisplay() {
				return loanBtcDisplay;
			}

			public BigDecimal getAvailableBtcDisplay() {
				return availableBtcDisplay;
			}

			public BigDecimal getAvailableCnyDisplay() {
				return availableCnyDisplay;
			}

			public BigDecimal getFrozenBtcDisplay() {
				return frozenBtcDisplay;
			}

			public BigDecimal getFrozenCnyDisplay() {
				return frozenCnyDisplay;
			}

		}
	}

}
