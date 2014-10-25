package org.oxerr.huobi.xchange.service.streaming;

import static com.xeiam.xchange.service.streaming.ExchangeEventType.DEPTH;
import static com.xeiam.xchange.service.streaming.ExchangeEventType.TICKER;
import static org.oxerr.huobi.xchange.service.streaming.HuobiSocketIOAdapters.adaptTicker;

import java.util.concurrent.BlockingQueue;

import org.oxerr.huobi.websocket.HuobiSocketClient;
import org.oxerr.huobi.websocket.dto.Depth;
import org.oxerr.huobi.websocket.dto.Percent;
import org.oxerr.huobi.websocket.dto.response.Response;
import org.oxerr.huobi.websocket.dto.response.historydata.ReqMarketDepthResponse;
import org.oxerr.huobi.websocket.dto.response.historydata.ReqMarketDepthTopResponse;
import org.oxerr.huobi.websocket.dto.response.historydata.ReqTradeDetailTopResponse;
import org.oxerr.huobi.websocket.dto.response.marketdata.MarketDepthDiff;
import org.oxerr.huobi.websocket.dto.response.marketdata.MarketDepthTopDiff;
import org.oxerr.huobi.websocket.dto.response.marketdata.MarketOverview;
import org.oxerr.huobi.websocket.dto.response.marketdata.TradeDetail;
import org.oxerr.huobi.websocket.dto.response.payload.Payload;
import org.oxerr.huobi.websocket.event.ResponseAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xeiam.xchange.dto.marketdata.OrderBook;
import com.xeiam.xchange.dto.marketdata.Trade;
import com.xeiam.xchange.service.streaming.ExchangeEvent;
import com.xeiam.xchange.service.streaming.ExchangeEventType;

public class HuobiExchangeEventListener extends ResponseAdapter {

	private final Logger log = LoggerFactory
			.getLogger(HuobiExchangeEventListener.class);
	private final HuobiSocketClient client;
	private final BlockingQueue<ExchangeEvent> consumerEventQueue;

	private volatile Depth depth;
	private volatile Depth depthTop;
	private volatile OrderBook orderBook;

	public HuobiExchangeEventListener(HuobiSocketClient client,
			BlockingQueue<ExchangeEvent> consumerEventQueue) {
		this.client = client;
		this.consumerEventQueue = consumerEventQueue;
	}

	@Override
	public void onResponse(Response<? extends Payload> response) {
		if (response instanceof MarketOverview) {
			MarketOverview marketOverview = (MarketOverview) response;
			ExchangeEvent event = new HuobiExchangeEvent(TICKER, response,
					adaptTicker(marketOverview.getPayload()));
			putEvent(event);
		} else if (response instanceof ReqMarketDepthResponse) {
			ReqMarketDepthResponse reqMarketDepthResponse = (ReqMarketDepthResponse) response;
			depth = reqMarketDepthResponse.getPayload();
			putDepthEvent(reqMarketDepthResponse);
		} else if (response instanceof ReqMarketDepthTopResponse) {
			ReqMarketDepthTopResponse reqMarketDepthTopResponse = (ReqMarketDepthTopResponse) response;
			depthTop = reqMarketDepthTopResponse.getPayload();
			putDepthEvent(reqMarketDepthTopResponse);
		} else if (response instanceof MarketDepthDiff) {
			MarketDepthDiff marketDepthDiff = (MarketDepthDiff) response;
			if (depth != null) {
				try {
					depth.merge(marketDepthDiff.getPayload());
					putDepthEvent(marketDepthDiff);
				} catch (IllegalArgumentException e) {
					log.debug("{}", e.getMessage());
					client.reqMarketDepth(depth.getSymbolId(), Percent.PERCENT10);
				}
			}
		} else if (response instanceof MarketDepthTopDiff) {
			MarketDepthTopDiff marketDepthTopDiff = (MarketDepthTopDiff) response;
			if (depthTop != null) {
				try {
					depthTop.merge(marketDepthTopDiff.getPayload());
					putDepthEvent(marketDepthTopDiff);
				} catch (IllegalArgumentException e) {
					log.debug("{}", e.getMessage());
					client.reqMarketDepthTop(depthTop.getSymbolId());
				}
			}
		} else if (response instanceof ReqTradeDetailTopResponse) {
			ReqTradeDetailTopResponse reqTradeDetailTopResponse = (ReqTradeDetailTopResponse) response;
			putTradeEvent(reqTradeDetailTopResponse, reqTradeDetailTopResponse.getPayload());
		} else if (response instanceof TradeDetail) {
			TradeDetail tradeDetail = (TradeDetail) response;
			putTradeEvent(response, tradeDetail.getPayload());
		} else {
			log.debug("Unadaptable response: {}", response);
		}
	}

	private void putDepthEvent(Response<? extends Payload> response) {
		orderBook = HuobiSocketIOAdapters.adaptOrderBook(depth);
		ExchangeEvent event = new HuobiExchangeEvent(DEPTH, response, orderBook);
		putEvent(event);
	}

	private void putTradeEvent(Response<? extends Payload> response,
			org.oxerr.huobi.websocket.dto.TradeDetail tradeDetail) {
		Trade[] trades = HuobiSocketIOAdapters.adaptTrades(tradeDetail);
		for (Trade trade : trades) {
			putEvent(new HuobiExchangeEvent(ExchangeEventType.TRADE, response, trade));
		}
	}

	private void putEvent(ExchangeEvent event) {
		try {
			consumerEventQueue.put(event);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
