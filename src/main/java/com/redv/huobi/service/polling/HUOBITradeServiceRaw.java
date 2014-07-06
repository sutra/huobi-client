package com.redv.huobi.service.polling;

import static com.redv.huobi.HUOBIExchange.TRADE_PASSWORD_PARAMETER;
import static com.xeiam.xchange.dto.Order.OrderType.BID;

import java.math.BigDecimal;

import com.redv.huobi.dto.trade.HUOBICancelOrderResult;
import com.redv.huobi.dto.trade.HUOBIOrder;
import com.redv.huobi.dto.trade.HUOBIPlaceOrderResult;
import com.xeiam.xchange.ExchangeSpecification;
import com.xeiam.xchange.dto.Order.OrderType;

public class HUOBITradeServiceRaw extends HUOBIBaseTradeService {

	private final String tradePassword;

	protected HUOBITradeServiceRaw(ExchangeSpecification exchangeSpecification) {
		super(exchangeSpecification);
		tradePassword = (String) exchangeSpecification
				.getExchangeSpecificParametersItem(TRADE_PASSWORD_PARAMETER);
	}

	public HUOBIOrder[] getHUOBIOrders(int coinType) {
		return huobi.getOrders(
			"get_orders",
			accessKey,
			coinType,
			nextCreated(),
			digest);
	}

	public HUOBIOrder getHUOBIOrder(int coinType, long id) {
		return huobi.getOrder(
			"order_info",
			accessKey,
			coinType,
			id,
			nextCreated(),
			digest);
	}

	public HUOBIPlaceOrderResult placeHUOBILimitOrder(
		OrderType type,
		int coinType,
		BigDecimal price,
		BigDecimal amount) {
		final String method = type == BID ? "buy" : "sell";

		final HUOBIPlaceOrderResult result;

		if (tradePassword == null) {
			result = huobi.placeLimitOrder(
				method,
				accessKey,
				coinType,
				price.toPlainString(),
				amount.toPlainString(),
				nextCreated(),
				digest);
		} else {
			result = huobi.placeLimitOrder(
				method,
				accessKey,
				coinType,
				price.toPlainString(),
				amount.toPlainString(),
				nextCreated(),
				digest,
				tradePassword);
		}

		return result;
	}

	public HUOBIPlaceOrderResult placeHUOBIMarketOrder(
		OrderType type,
		int coinType,
		BigDecimal amount) {
		final String method = type == BID ? "buy_market" : "sell_market";

		final HUOBIPlaceOrderResult result;

		if (tradePassword == null) {
			result = huobi.placeMarketOrder(
				method,
				accessKey,
				coinType,
				amount.toPlainString(),
				nextCreated(),
				digest);
		} else {
			result = huobi.placeMarketOrder(
				method,
				accessKey,
				coinType,
				amount.toPlainString(),
				nextCreated(),
				digest,
				tradePassword);
		}

		return result;
	}

	public HUOBICancelOrderResult cancelHUOBIOrder(int coinType, long id) {
		return huobi.cancelOrder(
			"cancel_order",
			accessKey,
			coinType,
			id,
			nextCreated(),
			digest);
	}

	public HUOBIPlaceOrderResult modifyHUOBIOrder(
		int coinType,
		long id,
		BigDecimal price,
		BigDecimal amount) {
		return huobi.modifyOrder(
			"modify_order",
			accessKey,
			coinType,
			id,
			price.toPlainString(),
			amount.toPlainString(),
			nextCreated(),
			digest);
	}

}
