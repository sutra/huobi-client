package org.oxerr.huobi.rest;

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
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.oxerr.huobi.rest.dto.marketdata.Depth;
import org.oxerr.huobi.rest.dto.marketdata.OrderBookTAS;
import org.oxerr.huobi.rest.dto.marketdata.TickerObject;
import org.oxerr.huobi.rest.dto.marketdata.TradeObject;
import org.oxerr.huobi.rest.dto.trade.Order;
import org.oxerr.huobi.rest.dto.trade.PlaceOrderResult;

import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.dto.Order.OrderType;
import com.xeiam.xchange.dto.account.AccountInfo;
import com.xeiam.xchange.dto.marketdata.OrderBook;
import com.xeiam.xchange.dto.marketdata.Ticker;
import com.xeiam.xchange.dto.marketdata.Trade;
import com.xeiam.xchange.dto.marketdata.Trades;
import com.xeiam.xchange.dto.trade.LimitOrder;
import com.xeiam.xchange.dto.trade.Wallet;
import com.xeiam.xchange.exceptions.ExchangeException;

/**
 * Various adapters for converting from HUOBI DTOs to XChange DTOs
 */
public final class HuobiAdapters {

	private HuobiAdapters() {
	}

	public static Ticker adaptTicker(
			org.oxerr.huobi.rest.dto.marketdata.Ticker huobiTicker,
			CurrencyPair currencyPair) {
		TickerObject ticker = huobiTicker.getTicker();
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
			Depth huobiDepth,
			CurrencyPair currencyPair) {
		List<LimitOrder> asks = adaptOrderBook(huobiDepth.getAsks(), ASK, currencyPair);
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
			OrderBookTAS huobiDetail,
			CurrencyPair currencyPair) {
		List<Trade> trades = adaptTrades(huobiDetail.getTrades(), currencyPair);
		return new Trades(trades, SortByTimestamp);
	}

	private static List<Trade> adaptTrades(
			TradeObject[] trades,
			CurrencyPair currencyPair) {
		List<Trade> tradeList = new ArrayList<>(trades.length);
		for (TradeObject trade : trades) {
			tradeList.add(adaptTrade(trade, currencyPair));
		}
		return tradeList;
	}

	private static Trade adaptTrade(
			TradeObject trade,
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

	public static AccountInfo adaptAccountInfo(org.oxerr.huobi.rest.dto.account.AccountInfo a) {
		Wallet cny = new Wallet(CNY,
				a.getAvailableCnyDisplay().add(a.getFrozenCnyDisplay()));
		Wallet btc = new Wallet(BTC,
				a.getAvailableBtcDisplay().add(a.getFrozenBtcDisplay()));
		Wallet ltc = new Wallet(LTC,
				a.getAvailableLtcDisplay().add(a.getFrozenLtcDisplay()));
		List<Wallet> wallets = Arrays.asList(cny, btc, ltc);
		return new AccountInfo(null, wallets);
	}

	public static String adaptPlaceOrderResult(PlaceOrderResult result) {
		if (result.getCode() == 0) {
			return String.valueOf(result.getId());
		} else {
			throw new ExchangeException(result.getMsg());
		}
	}

	public static List<LimitOrder> adaptOpenOrders(
			Order[] orders,
			CurrencyPair currencyPair) {
		List<LimitOrder> openOrders = new ArrayList<>();
		for (Order order : orders) {
			openOrders.add(adaptOpenOrder(order, currencyPair));
		}
		return openOrders;
	}

	private static LimitOrder adaptOpenOrder(
		Order order, CurrencyPair currencyPair) {
		return new LimitOrder(
				order.getType() == 1 ? BID : ASK,
						order.getOrderAmount().subtract(order.getProcessedAmount()),
						currencyPair,
						String.valueOf(order.getId()),
						new Date(order.getOrderTime() * 1000),
						order.getOrderPrice());
	}

}
