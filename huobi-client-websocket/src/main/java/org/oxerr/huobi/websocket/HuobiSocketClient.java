package org.oxerr.huobi.websocket;

import io.socket.IOAcknowledge;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.oxerr.huobi.websocket.dto.GsonFactory;
import org.oxerr.huobi.websocket.dto.Percent;
import org.oxerr.huobi.websocket.dto.Period;
import org.oxerr.huobi.websocket.dto.request.Request;
import org.oxerr.huobi.websocket.dto.request.historydata.ReqKLineRequest;
import org.oxerr.huobi.websocket.dto.request.historydata.ReqMarketDepthRequest;
import org.oxerr.huobi.websocket.dto.request.historydata.ReqMarketDepthTopRequest;
import org.oxerr.huobi.websocket.dto.request.historydata.ReqMarketDetailRequest;
import org.oxerr.huobi.websocket.dto.request.historydata.ReqTimeLineRequest;
import org.oxerr.huobi.websocket.dto.request.historydata.ReqTradeDetailTopRequest;
import org.oxerr.huobi.websocket.dto.request.marketdata.Message;
import org.oxerr.huobi.websocket.dto.request.service.ReqMsgSubscribeRequest;
import org.oxerr.huobi.websocket.dto.request.service.ReqMsgUnsubscribeRequest;
import org.oxerr.huobi.websocket.dto.request.service.ReqSymbolDetailRequest;
import org.oxerr.huobi.websocket.dto.request.service.ReqSymbolListRequest;
import org.oxerr.huobi.websocket.dto.response.Response;
import org.oxerr.huobi.websocket.dto.response.ResponseFactory;
import org.oxerr.huobi.websocket.dto.response.payload.Payload;
import org.oxerr.huobi.websocket.event.HuobiSocketAdapter;
import org.oxerr.huobi.websocket.event.HuobiSocketListener;
import org.oxerr.huobi.websocket.event.ResponseListener;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

/**
 * The entry point of the Huobi WebSocket API Client.
 */
public class HuobiSocketClient {

	private static final int VERSION = 1;

	private final HuobiSocket socket;
	private final Gson gson = GsonFactory.getGson();
	private final List<ResponseListener> listeners = new ArrayList<>();
	private final ResponseFactory responseFactory = ResponseFactory
			.getInstance();

	public HuobiSocketClient(URL url) {
		socket = new HuobiSocket(url);
		socket.addListener(new HuobiSocketAdapter() {

			@Override
			public void on(String event, IOAcknowledge ack, JsonElement... args) {
				Response<? extends Payload> response = responseFactory
						.fromJson(event, args);
				onResponse(response);
			}

		});
	}

	public void connect() {
		socket.connect();
	}

	public void disconnect() {
		socket.disconnect();
	}

	public void reconnect() {
		socket.reconnect();
	}

	public void addListener(HuobiSocketListener listener) {
		socket.addListener(listener);
	}

	public void addListener(ResponseListener listener) {
		listeners.add(listener);
	}

	protected void onResponse(Response<? extends Payload> response) {
		for (ResponseListener listener : listeners) {
			listener.onResponse(response);
		}
	}

	public void send(Request request) {
		socket.emit("request", gson.toJson(request));
	}

	public void reqSymbolList(String... symbol) {
		ReqSymbolListRequest request = new ReqSymbolListRequest(VERSION);
		request.setSymbolIdList(symbol);
		send(request);
	}

	public void reqSymbolDetail(String... symbol) {
		ReqSymbolDetailRequest request = new ReqSymbolDetailRequest(VERSION,
				symbol);
		send(request);
	}

	public void reqMsgSubscribe(Message message) {
		ReqMsgSubscribeRequest request = new ReqMsgSubscribeRequest(VERSION,
				message);
		send(request);
	}

	public void reqMsgUnsubscribe(Message message) {
		ReqMsgUnsubscribeRequest request = new ReqMsgUnsubscribeRequest(
				VERSION, message);
		send(request);
	}

	public void reqTimeLine(String symbol) {
		ReqTimeLineRequest request = new ReqTimeLineRequest(VERSION, symbol);
		send(request);
	}

	public void reqKLine(String symbol, Period period, Date from, Date to) {
		ReqKLineRequest request = new ReqKLineRequest(VERSION, symbol, period);
		// request.setFrom(from);
		// request.setTo(to);
		send(request);
	}

	public void reqMarketDepthTop(String symbol) {
		ReqMarketDepthTopRequest request = new ReqMarketDepthTopRequest(
				VERSION, symbol);
		send(request);
	}

	public void reqMarketDepth(String symbol, Percent percent) {
		ReqMarketDepthRequest request = new ReqMarketDepthRequest(VERSION,
				symbol, percent);
		send(request);
	}

	public void reqTradeDetailTop(String symbol, int count) {
		ReqTradeDetailTopRequest request = new ReqTradeDetailTopRequest(
				VERSION, symbol);
		request.setCount(count);
		send(request);
	}

	public void reqMarketDetail(String symbol) {
		ReqMarketDetailRequest request = new ReqMarketDetailRequest(VERSION,
				symbol);
		send(request);
	}

}
