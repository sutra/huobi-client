package org.oxerr.huobi.rest.service.polling;

import static com.xeiam.xchange.dto.Order.OrderType.BID;
import static org.oxerr.huobi.rest.HuobiExchange.TRADE_PASSWORD_PARAMETER;

import java.io.IOException;
import java.math.BigDecimal;

import org.oxerr.huobi.rest.dto.trade.CancelOrderResult;
import org.oxerr.huobi.rest.dto.trade.Order;
import org.oxerr.huobi.rest.dto.trade.PlaceOrderResult;

import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.ExchangeSpecification;
import com.xeiam.xchange.dto.Order.OrderType;

public class HuobiTradeServiceRaw extends HuobiBaseTradeService {

	private final String tradePassword;

	protected HuobiTradeServiceRaw(Exchange exchange) {
		super(exchange);
		ExchangeSpecification spec = exchange.getExchangeSpecification();
		tradePassword = (String) spec
				.getExchangeSpecificParametersItem(TRADE_PASSWORD_PARAMETER);
	}

	public Order[] getHUOBIOrders(int coinType) throws IOException {
		return huobi.getOrders(
			"get_orders",
			accessKey,
			coinType,
			nextCreated(),
			digest);
	}

	public Order getHUOBIOrder(int coinType, long id) throws IOException {
		return huobi.getOrder(
			"order_info",
			accessKey,
			coinType,
			id,
			nextCreated(),
			digest);
	}

	public PlaceOrderResult placeHUOBILimitOrder(
		OrderType type,
		int coinType,
		BigDecimal price,
		BigDecimal amount) throws IOException {
		final String method = type == BID ? "buy" : "sell";

		final PlaceOrderResult result;

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

	public PlaceOrderResult placeHUOBIMarketOrder(
		OrderType type,
		int coinType,
		BigDecimal amount) throws IOException {
		final String method = type == BID ? "buy_market" : "sell_market";

		final PlaceOrderResult result;

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

	public CancelOrderResult cancelHUOBIOrder(int coinType, long id)
			throws IOException {
		return huobi.cancelOrder(
			"cancel_order",
			accessKey,
			coinType,
			id,
			nextCreated(),
			digest);
	}

	public PlaceOrderResult modifyHUOBIOrder(
		int coinType,
		long id,
		BigDecimal price,
		BigDecimal amount) throws IOException {
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
