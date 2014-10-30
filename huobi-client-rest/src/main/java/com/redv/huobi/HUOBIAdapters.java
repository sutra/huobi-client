package com.redv.huobi;

import static com.xeiam.xchange.currency.Currencies.BTC;
import static com.xeiam.xchange.currency.Currencies.CNY;
import static com.xeiam.xchange.currency.Currencies.LTC;
import static com.xeiam.xchange.dto.Order.OrderType.ASK;
import static com.xeiam.xchange.dto.Order.OrderType.BID;
import static com.xeiam.xchange.dto.marketdata.Trades.TradeSortType.SortByTimestamp;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

import com.redv.huobi.dto.account.HUOBIAccountInfo;
import com.redv.huobi.dto.marketdata.HUOBIDepth;
import com.redv.huobi.dto.marketdata.HUOBIOrderBookTAS;
import com.redv.huobi.dto.marketdata.HUOBITicker;
import com.redv.huobi.dto.marketdata.HUOBITickerObject;
import com.redv.huobi.dto.marketdata.HUOBITradeObject;
import com.redv.huobi.dto.trade.HUOBIOrder;
import com.redv.huobi.dto.trade.HUOBIPlaceOrderResult;
import com.xeiam.xchange.ExchangeException;
import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.dto.Order.OrderType;
import com.xeiam.xchange.dto.account.AccountInfo;
import com.xeiam.xchange.dto.marketdata.OrderBook;
import com.xeiam.xchange.dto.marketdata.Ticker;
import com.xeiam.xchange.dto.marketdata.Trade;
import com.xeiam.xchange.dto.marketdata.Trades;
import com.xeiam.xchange.dto.trade.LimitOrder;
import com.xeiam.xchange.dto.trade.Wallet;

/**
 * Various adapters for converting from HUOBI DTOs to XChange DTOs
 */
public final class HUOBIAdapters {

	private HUOBIAdapters() {
	}

	public static Ticker adaptTicker(
			HUOBITicker huobiTicker,
			CurrencyPair currencyPair) {
		HUOBITickerObject ticker = huobiTicker.getTicker();
		return new Ticker.Builder()
			.currencyPair(currencyPair)
			.last(ticker.getLast())
			.bid(ticker.getBuy())
			.ask(ticker.getSell())
			.high(ticker.getHigh())
			.low(ticker.getLow())
			.volume(ticker.getVol())
			.build();
	}

	public static OrderBook adaptOrderBook(
			HUOBIDepth huobiDepth,
			CurrencyPair currencyPair) {
		List<LimitOrder> asks = adaptOrderBook(huobiDepth.getAsks(), ASK, currencyPair);
		Collections.reverse(asks);

		List<LimitOrder> bids = adaptOrderBook(huobiDepth.getBids(), BID, currencyPair);

		return new OrderBook(null, asks, bids);
	}

	private static List<LimitOrder> adaptOrderBook(
			BigDecimal[][] orders,
			OrderType type,
			CurrencyPair currencyPair) {
		List<LimitOrder> limitOrders = new ArrayList<>(orders.length);
		for (BigDecimal[] order : orders) {
			LimitOrder limitOrder = new LimitOrder(
				type, order[1], currencyPair, null, null, order[0]);
			limitOrders.add(limitOrder);
		}
		return limitOrders;
	}

	public static Trades adaptTrades(
			HUOBIOrderBookTAS huobiDetail,
			CurrencyPair currencyPair) {
		List<Trade> trades = adaptTrades(huobiDetail.getTrades(), currencyPair);
		return new Trades(trades, SortByTimestamp);
	}

	private static List<Trade> adaptTrades(
			HUOBITradeObject[] trades,
			CurrencyPair currencyPair) {
		List<Trade> tradeList = new ArrayList<>(trades.length);
		for (HUOBITradeObject trade : trades) {
			tradeList.add(adaptTrade(trade, currencyPair));
		}
		return tradeList;
	}

	private static Trade adaptTrade(
			HUOBITradeObject trade,
			CurrencyPair currencyPair) {
		OrderType type = trade.getType().equals("买入") ? BID : ASK;
		final Date time;
		try {
			time = DateUtils.parseDate(trade.getTime(), "HH:mm:ss");
		} catch (ParseException e) {
			throw new ExchangeException(e.getMessage(), e);
		}
		return new Trade(
				type,
				trade.getAmount(),
				currencyPair,
				trade.getPrice(),
				time,
				null);
	}

	public static AccountInfo adaptAccountInfo(HUOBIAccountInfo a) {
		Wallet cny = new Wallet(CNY,
				a.getAvailableCnyDisplay().add(a.getFrozenCnyDisplay()));
		Wallet btc = new Wallet(BTC,
				a.getAvailableBtcDisplay().add(a.getFrozenBtcDisplay()));
		Wallet ltc = new Wallet(LTC,
				a.getAvailableLtcDisplay().add(a.getFrozenLtcDisplay()));
		List<Wallet> wallets = Arrays.asList(cny, btc, ltc);
		return new AccountInfo(null, wallets);
	}

	public static String adaptPlaceOrderResult(HUOBIPlaceOrderResult result) {
		if (result.getCode() == 0) {
			return String.valueOf(result.getId());
		} else {
			throw new ExchangeException(result.getMsg());
		}
	}

	public static List<LimitOrder> adaptOpenOrders(
			HUOBIOrder[] orders,
			CurrencyPair currencyPair) {
		List<LimitOrder> openOrders = new ArrayList<>();
		for (HUOBIOrder order : orders) {
			openOrders.add(adaptOpenOrder(order, currencyPair));
		}
		return openOrders;
	}

	private static LimitOrder adaptOpenOrder(
		HUOBIOrder order, CurrencyPair currencyPair) {
		return new LimitOrder(
				order.getType() == 1 ? BID : ASK,
						order.getOrderAmount().subtract(order.getProcessedAmount()),
						currencyPair,
						String.valueOf(order.getId()),
						new Date(order.getOrderTime() * 1000),
						order.getOrderPrice());
	}

}
