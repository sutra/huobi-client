package org.oxerr.huobi.rest.service.polling;

import static org.oxerr.huobi.rest.dto.trade.HuobiError.ORDER_NOT_EXIST;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oxerr.huobi.rest.HuobiAdapters;
import org.oxerr.huobi.rest.dto.trade.CancelOrderResult;
import org.oxerr.huobi.rest.dto.trade.Order;
import org.oxerr.huobi.rest.dto.trade.PlaceOrderResult;

import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.dto.trade.LimitOrder;
import com.xeiam.xchange.dto.trade.MarketOrder;
import com.xeiam.xchange.dto.trade.OpenOrders;
import com.xeiam.xchange.dto.trade.UserTrades;
import com.xeiam.xchange.exceptions.ExchangeException;
import com.xeiam.xchange.exceptions.NotAvailableFromExchangeException;
import com.xeiam.xchange.exceptions.NotYetImplementedForExchangeException;
import com.xeiam.xchange.service.polling.trade.PollingTradeService;
import com.xeiam.xchange.service.polling.trade.params.TradeHistoryParams;

public class HuobiTradeService extends HuobiTradeServiceRaw implements
		PollingTradeService {

	private final Map<CurrencyPair, Integer> coinTypes;

	public HuobiTradeService(Exchange exchange) {
		super(exchange);
		coinTypes = new HashMap<>(2);
		coinTypes.put(CurrencyPair.BTC_CNY, 1);
		coinTypes.put(CurrencyPair.LTC_CNY, 2);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OpenOrders getOpenOrders() throws ExchangeException,
			NotAvailableFromExchangeException,
			NotYetImplementedForExchangeException, IOException {
		List<LimitOrder> openOrders = new ArrayList<>();
		for (CurrencyPair currencyPair : getExchangeSymbols()) {
			Order[] orders = getHUOBIOrders(coinTypes.get(currencyPair));
			openOrders.addAll(HuobiAdapters.adaptOpenOrders(orders, currencyPair));
		}
		return new OpenOrders(openOrders);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String placeMarketOrder(MarketOrder marketOrder)
			throws ExchangeException, NotAvailableFromExchangeException,
			NotYetImplementedForExchangeException, IOException {
		PlaceOrderResult result = placeHUOBIMarketOrder(
				marketOrder.getType(),
				coinTypes.get(marketOrder.getCurrencyPair()),
				marketOrder.getTradableAmount());
		return HuobiAdapters.adaptPlaceOrderResult(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String placeLimitOrder(LimitOrder limitOrder)
			throws ExchangeException, NotAvailableFromExchangeException,
			NotYetImplementedForExchangeException, IOException {
		PlaceOrderResult result = placeHUOBILimitOrder(
			limitOrder.getType(),
			coinTypes.get(limitOrder.getCurrencyPair()),
			limitOrder.getLimitPrice(),
			limitOrder.getTradableAmount());
		return HuobiAdapters.adaptPlaceOrderResult(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean cancelOrder(String orderId) throws IOException {
		final long id = Long.parseLong(orderId);

		CancelOrderResult result = null;
		for (CurrencyPair currencyPair : getExchangeSymbols()) {
			result = cancelHUOBIOrder(coinTypes.get(currencyPair), id);

			if (result.getCode() == 0) {
				break;
			} else if (result.getCode() == ORDER_NOT_EXIST) {
				continue;
			} else {
				break;
			}
		}
		return result != null && "success".equals(result.getResult());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserTrades getTradeHistory(Object... arguments) {
		throw new NotAvailableFromExchangeException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserTrades getTradeHistory(TradeHistoryParams params)
			throws ExchangeException, NotAvailableFromExchangeException,
			NotYetImplementedForExchangeException, IOException {
		throw new NotYetImplementedForExchangeException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TradeHistoryParams createTradeHistoryParams() {
		return null;
	}

}
