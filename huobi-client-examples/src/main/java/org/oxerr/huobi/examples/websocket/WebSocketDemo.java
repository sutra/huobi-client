package org.oxerr.huobi.examples.websocket;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.oxerr.huobi.websocket.HuobiSocketClient;
import org.oxerr.huobi.websocket.dto.Percent;
import org.oxerr.huobi.websocket.dto.request.historydata.ReqTimeLineRequest;
import org.oxerr.huobi.websocket.dto.request.marketdata.Message;
import org.oxerr.huobi.websocket.dto.request.marketdata.PushType;
import org.oxerr.huobi.websocket.dto.response.Response;
import org.oxerr.huobi.websocket.dto.response.historydata.ReqMarketDepthResponse;
import org.oxerr.huobi.websocket.dto.response.marketdata.MarketDepthDiff;
import org.oxerr.huobi.websocket.dto.response.payload.Payload;
import org.oxerr.huobi.websocket.dto.response.payload.ReqMarketDepthPayload;
import org.oxerr.huobi.websocket.event.ResponseListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

/**
 * Demonstration of {@link HuobiSocketClient}.
 */
public class WebSocketDemo {

	private static final Logger log = LoggerFactory.getLogger(WebSocketDemo.class);

	private static volatile ReqMarketDepthPayload depth;

	public static void main(String[] args) throws MalformedURLException,
			InterruptedException {
		// Bridge/route all JUL log records to the SLF4J API.
		SLF4JBridgeHandler.removeHandlersForRootLogger();
		SLF4JBridgeHandler.install();

		HuobiSocketClient client = new HuobiSocketClient(URI.create(
				"http://hq.huobi.com:80").toURL());
		client.addListener(new ResponseListener() {

			@Override
			public void onResponse(Response<? extends Payload> response) {
				log.info("{}", response);
				if (response instanceof ReqMarketDepthResponse) {
					ReqMarketDepthResponse reqMarketDepthResponse = (ReqMarketDepthResponse) response;
					depth = reqMarketDepthResponse.getPayload();
					log.info("depth: {}", depth);
				} else if (response instanceof MarketDepthDiff) {
					MarketDepthDiff marketDepthDiff = (MarketDepthDiff) response;
					if (depth != null) {
						depth.merge(marketDepthDiff.getPayload());
						log.info("merged depth: {}", depth);
						if (marketDepthDiff.getPayload().getBidInsert().getPrice().length > 0
								|| marketDepthDiff.getPayload().getAskInsert().getPrice().length > 0) {
							System.exit(0);
						}
					}
				}
			}
		});

		client.connect();

		final String btccny = "btccny", ltccny = "ltccny";

		// History data API
		// client.reqTimeLine(btccny);
		ReqTimeLineRequest req = new ReqTimeLineRequest(1, btccny);
		req.setFrom(1400000000L);
		req.setTo(  1400000220L);
		client.send(req);
		// client.reqKLine(btccny, Period.KLINE_1MIN, null, null);
		// client.reqMarketDepthTop(btccny);
		// client.reqMarketDepth(btccny, Percent.PERCENT10);
		// client.reqTradeDetailTop(btccny, 10);
		// client.reqMarketDetail(btccny);

		// Service API
		// client.reqSymbolList(btccny, ltccny);
		// client.reqSymbolDetail(btccny, ltccny);

		Message message = new Message();
		// message.addLastTimeLine(btccny, PushType.PUSH_LONG);
		// message.addLastKLine(btccny, PushType.PUSH_LONG, Period.KLINE_1MIN);
		message.addMarketDepthDiff(btccny, PushType.PUSH_LONG, Percent.PERCENT10);
		// message.addMarketDepthTopDiff(btccny, PushType.PUSH_LONG);
		// message.addMarketDetail(btccny, PushType.PUSH_LONG);
		// message.addTradeDetail(btccny, PushType.PUSH_LONG);
		// message.addMarketOverview(btccny, PushType.PUSH_LONG);

		// client.reqMsgSubscribe(message);

		TimeUnit.MINUTES.sleep(1);
		// client.reqMsgUnsubscribe(message);

		TimeUnit.SECONDS.sleep(5);
		client.disconnect();
	}

}
