package org.oxerr.huobi.xchange.service.streaming;

import static com.xeiam.xchange.service.streaming.ExchangeEventType.CONNECT;
import static com.xeiam.xchange.service.streaming.ExchangeEventType.DISCONNECT;
import static com.xeiam.xchange.service.streaming.ExchangeEventType.ERROR;
import static org.java_websocket.WebSocket.READYSTATE.CLOSED;
import static org.java_websocket.WebSocket.READYSTATE.NOT_YET_CONNECTED;
import static org.java_websocket.WebSocket.READYSTATE.OPEN;
import static org.oxerr.huobi.xchange.HuobiExchange.WEBSOCKET_URI_KEY;
import static org.oxerr.huobi.xchange.service.streaming.HuobiSocketIOAdapters.adaptSymbol;
import io.socket.SocketIOException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.java_websocket.WebSocket.READYSTATE;
import org.oxerr.huobi.websocket.HuobiSocketClient;
import org.oxerr.huobi.websocket.dto.Percent;
import org.oxerr.huobi.websocket.dto.request.Request;
import org.oxerr.huobi.websocket.dto.request.marketdata.Message;
import org.oxerr.huobi.websocket.dto.request.marketdata.PushType;
import org.oxerr.huobi.websocket.event.HuobiSocketAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.xeiam.xchange.ExchangeSpecification;
import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.service.streaming.DefaultExchangeEvent;
import com.xeiam.xchange.service.streaming.ExchangeEvent;
import com.xeiam.xchange.service.streaming.ExchangeEventType;
import com.xeiam.xchange.service.streaming.StreamingExchangeService;

/**
 * Huobi streaming service implementation over WebSocket Market API.
 */
public class HuobiSocketIOService implements StreamingExchangeService {

	private final Logger log = LoggerFactory.getLogger(HuobiSocketIOService.class);
	private final HuobiSocketClient client;
	private final Message message;
	private final Gson gson = new Gson();
	private final BlockingQueue<ExchangeEvent> consumerEventQueue = new LinkedBlockingQueue<ExchangeEvent>();
	private final HuobiExchangeEventListener listener;
	private volatile READYSTATE webSocketStatus = NOT_YET_CONNECTED;

	public HuobiSocketIOService(
			final ExchangeSpecification exchangeSpecification,
			final HuobiStreamingConfiguration configuration) {
		String url = (String) exchangeSpecification.getExchangeSpecificParametersItem(WEBSOCKET_URI_KEY);
		try {
			client = new HuobiSocketClient(new URL(url));
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(e);
		}

		message = new Message();

		final Set<CurrencyPair> currencyPairs = configuration.getCurrencyPairs();
		final Set<String> symbols = new HashSet<>(currencyPairs.size());

		for (CurrencyPair currencyPair : currencyPairs) {
			String symbol = adaptSymbol(currencyPair);
			symbols.add(symbol);

			// Ticker
			message.addMarketOverview(symbol, PushType.PUSH_LONG);

			// Depth
			message.addMarketDepthDiff(symbol, PushType.PUSH_LONG, Percent.PERCENT10);

			// Trade
			message.addTradeDetail(symbol, PushType.PUSH_LONG);
		}

		client.addListener(new HuobiSocketAdapter() {

			private final AtomicInteger reconnectAttempts = new AtomicInteger();

			@Override
			public void onConnect() {
				reconnectAttempts.set(0);

				webSocketStatus = OPEN;
				putEvent(CONNECT);

				for (String symbol : symbols) {
					// Depth
					client.reqMarketDepth(symbol, Percent.PERCENT10);

					// Trade
					client.reqTradeDetailTop(symbol, 10);
				}

				client.reqMsgSubscribe(message);
			}

			@Override
			public void onDisconnect() {
				webSocketStatus = CLOSED;
				putEvent(DISCONNECT);
			}


			@Override
			public void onError(SocketIOException socketIOException) {
				putEvent(new DefaultExchangeEvent(ERROR,
						socketIOException.getMessage(), socketIOException));

				final int attempts = reconnectAttempts.incrementAndGet();

				if (configuration.getMaxReconnectAttempts() <= 0
						|| attempts <= configuration.getMaxReconnectAttempts()) {
					sleepQuietly(configuration.getReconnectWaitTimeInMs());
					log.trace("Reconnecting({}/{})...",
							attempts,
							configuration.getMaxReconnectAttempts());
					client.connect();
				} else {
					log.warn("Reconnect attempts reached the max attempts {}, giving up.",
							configuration.getMaxReconnectAttempts());
				}
			}

			private void sleepQuietly(long millis) {
				log.trace("Sleeping {} milliseconds...", millis);

				try {
					Thread.sleep(millis);
				} catch (InterruptedException e) {
				}
			}

		});

		listener = new HuobiExchangeEventListener(client, consumerEventQueue);
		client.addListener(listener);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void connect() {
		client.connect();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void disconnect() {
		client.reqMsgUnsubscribe(message);
		client.disconnect();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ExchangeEvent getNextEvent() throws InterruptedException {
		return consumerEventQueue.take();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void send(String msg) {
		client.send(gson.fromJson(msg, Request.class));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public READYSTATE getWebSocketStatus() {
		return webSocketStatus;
	}

	private void putEvent(ExchangeEvent event) {
		try {
			consumerEventQueue.put(event);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private void putEvent(ExchangeEventType exchangeEventType) {
		putEvent(new DefaultExchangeEvent(exchangeEventType, null));
	}

}
