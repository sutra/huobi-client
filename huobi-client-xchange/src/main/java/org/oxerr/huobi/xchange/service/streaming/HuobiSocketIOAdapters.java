package org.oxerr.huobi.xchange.service.streaming;

import static com.xeiam.xchange.dto.Order.OrderType.ASK;
import static com.xeiam.xchange.dto.Order.OrderType.BID;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.oxerr.huobi.websocket.dto.Depth;
import org.oxerr.huobi.websocket.dto.TradeDetail;
import org.oxerr.huobi.websocket.dto.response.marketdata.payload.MarketOverviewPayload;

import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.dto.Order.OrderType;
import com.xeiam.xchange.dto.marketdata.OrderBook;
import com.xeiam.xchange.dto.marketdata.Ticker;
import com.xeiam.xchange.dto.marketdata.Trade;
import com.xeiam.xchange.dto.trade.LimitOrder;

/**
 * Various adapters for converting from Huobi WebSocket DTOs to XChange DTOs.
 */
public final class HuobiSocketIOAdapters {

	private HuobiSocketIOAdapters() {
	}

	public static String adaptSymbol(CurrencyPair currencyPair) {
		return (currencyPair.baseSymbol + currencyPair.counterSymbol)
			.toLowerCase();
	}

	public static CurrencyPair adaptCurrencyPair(String symbolId) {
		return new CurrencyPair(symbolId.substring(0, 3).toUpperCase(),
				symbolId.substring(3, 6).toUpperCase());
	}

	public static OrderType adaptOrderType(int direction) {
		OrderType type;
		switch (direction) {
		case 1:
			type = BID;
			break;
		case 2:
			type = ASK;
			break;
		default:
			type = BID;
			break;
		}
		return type;
	}

	public static Ticker adaptTicker(MarketOverviewPayload marketOverviewPayload) {
		return new Ticker.Builder()
			.timestamp(new Date())
			.currencyPair(adaptCurrencyPair(marketOverviewPayload.getSymbolId()))
			.last(marketOverviewPayload.getPriceNew())
			.high(marketOverviewPayload.getPriceHigh())
			.low(marketOverviewPayload.getPriceLow())
			.ask(marketOverviewPayload.getPriceAsk())
			.bid(marketOverviewPayload.getPriceBid())
			.volume(marketOverviewPayload.getTotalAmount())
			.build();
	}

	public static OrderBook adaptOrderBook(Depth depth) {
		Date timeStamp = new Date(depth.getTime());
		List<LimitOrder> asks = new ArrayList<>(depth.getAskPrice().length);
		List<LimitOrder> bids = new ArrayList<>(depth.getBidPrice().length);
		CurrencyPair currencyPair = adaptCurrencyPair(depth.getSymbolId());

		// asks should be sorted ascending
		for (int i = 0, l = depth.getAskPrice().length; i < l; i++) {
			LimitOrder limitOrder = new LimitOrder.Builder(ASK, currencyPair)
				.limitPrice(depth.getAskPrice()[i])
				.tradableAmount(depth.getAskAmount()[i])
				.build();
			asks.add(limitOrder);
		}

		// bids should be sorted descending
		for (int i = 0, l = depth.getBidPrice().length; i < l; i++) {
			LimitOrder limitOrder = new LimitOrder.Builder(BID, currencyPair)
				.limitPrice(depth.getBidPrice()[i])
				.tradableAmount(depth.getBidAmount()[i]).build();
			bids.add(limitOrder);
		}

		OrderBook orderBook = new OrderBook(timeStamp, asks, bids);
		return orderBook;
	}

	public static Trade[] adaptTrades(TradeDetail tradeDetail) {
		int length = tradeDetail.getTradeId().length;
		Trade[] trades = new Trade[length];
		CurrencyPair currencyPair = adaptCurrencyPair(tradeDetail.getSymbolId());
		for (int i = 0; i < length; i++) {
			trades[i] = new Trade(adaptOrderType(tradeDetail.getDirection()[i]),
				tradeDetail.getAmount()[i],
				currencyPair,
				tradeDetail.getPrice()[i],
				new Date(tradeDetail.getTime()[i] * 1000),
				String.valueOf(tradeDetail.getTradeId()[i]));
		}
		return trades;
	}

}
