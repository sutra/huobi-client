package org.oxerr.huobi.examples.fix;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import org.oxerr.huobi.fix.TradeApplication;
import org.oxerr.huobi.fix.fix44.AccountInfoResponse;
import org.oxerr.huobi.fix.fix44.HuobiMessageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quickfix.ConfigError;
import quickfix.FieldNotFound;
import quickfix.FileLogFactory;
import quickfix.FileStoreFactory;
import quickfix.IncorrectTagValue;
import quickfix.Initiator;
import quickfix.LogFactory;
import quickfix.MessageFactory;
import quickfix.MessageStoreFactory;
import quickfix.SessionID;
import quickfix.SessionSettings;
import quickfix.SocketInitiator;
import quickfix.UnsupportedMessageType;

public class TradeClient {

	private final Logger log = LoggerFactory.getLogger(TradeClient.class);

	private final TradeApplication app;

	private SessionID tradeSessionId;

	public TradeClient(final String accessKey, final String secretKey)
			throws IOException, ConfigError, InterruptedException {
		app = new TradeApplication(accessKey, secretKey) {

			@Override
			public void onCreate(SessionID sessionId) {
				super.onCreate(sessionId);
				tradeSessionId = sessionId;
			}

			@Override
			public void onMessage(AccountInfoResponse message,
					SessionID sessionId) throws FieldNotFound,
					UnsupportedMessageType, IncorrectTagValue {
				log.info("Available BTC: {}", message.getAvailableBtc()
						.getValue());
				log.info("Available LTC: {}", message.getAvailableLtc()
						.getValue());
				log.info("Available CNY: {}", message.getAvailableCny()
						.getValue());
				log.info("Frozen BTC: {}", message.getFrozenBtc().getValue());
				log.info("Frozen LTC: {}", message.getFrozenLtc().getValue());
				log.info("Frozen CNY: {}", message.getFrozenCny().getValue());
			}

		};

		SessionSettings settings;
		try (InputStream inputStream = MarketClient.class
				.getResourceAsStream("trade.cfg")) {
			settings = new SessionSettings(inputStream);
		}

		MessageStoreFactory storeFactory = new FileStoreFactory(settings);
		LogFactory logFactory = new FileLogFactory(settings);
		MessageFactory messageFactory = new HuobiMessageFactory();
		Initiator initiator = new SocketInitiator(app, storeFactory, settings,
				logFactory, messageFactory);
		initiator.start();

		while (!initiator.isLoggedOn()) {
			log.info("Waiting for logged on...");
			TimeUnit.SECONDS.sleep(1);
		}
	}

	public void demo() {
		log.info("Requesting AccountInfo...");
		app.requestAccountInfo("btc", tradeSessionId);
	}

	public static void main(String[] args) throws IOException, ConfigError,
			InterruptedException {
		final String accessKey = args[0];
		final String secretKey = args[1];

		TradeClient tradeClient = new TradeClient(accessKey, secretKey);
		tradeClient.demo();
	}

}
