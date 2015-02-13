package org.oxerr.huobi.examples.fix;

import static org.oxerr.huobi.fix.MarketDataRequests.buildMarketDataRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.oxerr.huobi.fix.MarketApplication;
import org.oxerr.huobi.fix.fix44.HuobiMessageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quickfix.ConfigError;
import quickfix.DataDictionary;
import quickfix.FieldConvertError;
import quickfix.FileLogFactory;
import quickfix.FileStoreFactory;
import quickfix.Initiator;
import quickfix.LogFactory;
import quickfix.Message;
import quickfix.MessageFactory;
import quickfix.MessageStoreFactory;
import quickfix.SessionID;
import quickfix.SessionSettings;
import quickfix.SocketInitiator;
import quickfix.field.MDEntryType;
import quickfix.field.MDUpdateType;
import quickfix.field.SubscriptionRequestType;

public class MarketClient {

	private final Logger log = LoggerFactory.getLogger(MarketClient.class);

	private final DataDictionary dict;

	private final Initiator initiator;
	private final MarketApplication app;

	public MarketClient() throws IOException, ConfigError, FieldConvertError {
		app = new MarketApplication() {

			@Override
			public void onLogon(SessionID sessionId) {
				log.info("logged on");

				super.onLogon(sessionId);

				String mdReqId = UUID.randomUUID().toString();
				Message message = buildMarketDataRequest(
						mdReqId,
						"BTC/CNY",
						SubscriptionRequestType.SNAPSHOT_PLUS_UPDATES,
						100,
						MDUpdateType.FULL_REFRESH,
						MDEntryType.OPENING_PRICE, MDEntryType.CLOSING_PRICE, MDEntryType.TRADING_SESSION_HIGH_PRICE, MDEntryType.TRADING_SESSION_LOW_PRICE, MDEntryType.TRADING_SESSION_VWAP_PRICE, MDEntryType.TRADE_VOLUME);
				log.debug("sending message: {}", message.toXML(dict));
				sendMessage(message, sessionId);
			}

		};

		SessionSettings settings;
		try (InputStream inputStream = getClass().getResourceAsStream(
				"market.cfg")) {
			settings = new SessionSettings(inputStream);
		}

		dict = new DataDictionary(settings.getString("DataDictionary"));

		MessageStoreFactory storeFactory = new FileStoreFactory(settings);
		LogFactory logFactory = new FileLogFactory(settings);
		MessageFactory messageFactory = new HuobiMessageFactory();
		initiator = new SocketInitiator(app, storeFactory, settings,
				logFactory, messageFactory);
		initiator.block();
	}

	public static void main(String[] args) throws IOException, ConfigError, FieldConvertError {
		new MarketClient();
	}

}
