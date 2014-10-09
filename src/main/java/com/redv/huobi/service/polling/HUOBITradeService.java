package com.redv.huobi.service.polling;

import static com.redv.huobi.dto.trade.HUOBIError.ORDER_NOT_EXIST;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.redv.huobi.HUOBIAdapters;
import com.redv.huobi.dto.trade.HUOBICancelOrderResult;
import com.redv.huobi.dto.trade.HUOBIOrder;
import com.redv.huobi.dto.trade.HUOBIPlaceOrderResult;
import com.xeiam.xchange.ExchangeException;
import com.xeiam.xchange.ExchangeSpecification;
import com.xeiam.xchange.NotAvailableFromExchangeException;
import com.xeiam.xchange.NotYetImplementedForExchangeException;
import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.dto.marketdata.Trades;
import com.xeiam.xchange.dto.trade.LimitOrder;
import com.xeiam.xchange.dto.trade.MarketOrder;
import com.xeiam.xchange.dto.trade.OpenOrders;
import com.xeiam.xchange.service.polling.PollingTradeService;

public class HUOBITradeService extends HUOBITradeServiceRaw implements
		PollingTradeService {

	private final Map<CurrencyPair, Integer> coinTypes;

	public HUOBITradeService(ExchangeSpecification exchangeSpecification) {
		super(exchangeSpecification);
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
			HUOBIOrder[] orders = getHUOBIOrders(coinTypes.get(currencyPair));
			HUOBIAdapters.adaptOpenOrders(orders, currencyPair);
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
		HUOBIPlaceOrderResult result = placeHUOBIMarketOrder(
				marketOrder.getType(),
				coinTypes.get(marketOrder.getCurrencyPair()),
				marketOrder.getTradableAmount());
		return HUOBIAdapters.adaptPlaceOrderResult(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String placeLimitOrder(LimitOrder limitOrder)
			throws ExchangeException, NotAvailableFromExchangeException,
			NotYetImplementedForExchangeException, IOException {
		HUOBIPlaceOrderResult result = placeHUOBILimitOrder(
			limitOrder.getType(),
			coinTypes.get(limitOrder.getCurrencyPair()),
			limitOrder.getLimitPrice(),
			limitOrder.getTradableAmount());
		return HUOBIAdapters.adaptPlaceOrderResult(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean cancelOrder(String orderId) throws IOException {
		final long id = Long.parseLong(orderId);

		HUOBICancelOrderResult result = null;
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
	public Trades getTradeHistory(Object... arguments) {
		throw new NotAvailableFromExchangeException();
	}

}
