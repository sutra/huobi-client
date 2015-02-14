package org.oxerr.huobi.examples.fix;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.oxerr.huobi.fix.TradeApplication;
import org.oxerr.huobi.fix.fix44.AccountInfoResponse;
import org.oxerr.huobi.fix.fix44.HuobiMessageFactory;
import org.oxerr.huobi.fix.fix44.HuobiOrderInfoResponse;
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
import quickfix.field.MassStatusReqType;
import quickfix.field.Side;
import quickfix.fix44.ExecutionReport;
import quickfix.fix44.OrderCancelReject;

public class TradeClient {

	private final Logger log = LoggerFactory.getLogger(TradeClient.class);

	private final TradeApplication app;

	private SessionID sessionId;

	private volatile boolean oneOrderDemoed;

	public TradeClient(final String accessKey, final String secretKey)
			throws IOException, ConfigError, InterruptedException {
		app = new TradeApplication(accessKey, secretKey) {

			@Override
			public void onCreate(SessionID sessionId) {
				super.onCreate(sessionId);
				TradeClient.this.sessionId = sessionId;
			}

			@Override
			public void onMessage(AccountInfoResponse message,
					SessionID sessionId) throws FieldNotFound,
					UnsupportedMessageType, IncorrectTagValue {
				log.info("AccReqID: {}", message.getAccReqID().getValue());
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

			@Override
			public void onMessage(HuobiOrderInfoResponse message,
					SessionID sessionId) throws FieldNotFound,
					UnsupportedMessageType, IncorrectTagValue {
				log.info("[HuobiOrderInfoResponse] Symbol: {}, orderID: {}, side: {}, price: {},"
					+ " ordStatus: {}, quantity: {},"
					+ " processedPrice: {}, processedAmount: {},"
					+ " vot: {}, fee: {}, total: {}",
						message.getSymbol().getValue(),
						message.getOrderID().getValue(),
						message.getSide(),
						message.getPrice().getValue(),
						message.getOrdStatus().getValue(),
						message.getQuantity().getValue(),
						message.getProcessedPrice().getValue(),
						message.getProcessedAmount().getValue(),
						message.getVot().getValue(),
						message.getFee().getValue(),
						message.getTotal().getValue());
			}

			@Override
			public void onMessage(ExecutionReport message, SessionID sessionId) throws FieldNotFound {
				log.info("[ExecutionReport] ClOrdID: {}, orderID: {}, {} {}@{}, avgPx: {}, cumQty: {}, ordStatus: {}",
						message.getClOrdID().getValue(),
						message.getOrderID().getValue(),
						message.getSide().getValue() == Side.BUY ? "buy" : "sell",
						message.isSetOrderQty() ? message.getOrderQty().getValue() : null,
						message.isSetPrice() ? message.getPrice().getValue() : null,
						message.isSetAvgPx() ? message.getAvgPx().getValue() : null,
						message.isSetCumQty() ? message.getCumQty().getValue() : null,
						message.isSetOrdStatus() ? message.getOrdStatus().getValue() : null);

				if (!TradeClient.this.oneOrderDemoed) {
					TradeClient.this.oneOrderDemoed = true;

					log.info("Requesting one order status...");
					app.requestOrderStatus(
							message.getOrderID().getValue(),
							message.getSide().getValue(),
							"btccny",
							sessionId);
				}
			}

			@Override
			public void onMessage(OrderCancelReject message, SessionID sessionId)
					throws FieldNotFound, UnsupportedMessageType,
					IncorrectTagValue {
				log.info("[OrderCancelReject] Order cancel rejected. Requested cancelation ID: {}, "
						+ "orderID: {}, "
						+ "origClOrdID: {}, "
						+ "ordStatus: {}, "
						+ "text: {}.",
						message.getClOrdID().getValue(),
						message.getOrderID().getValue(),
						message.getOrigClOrdID().getValue(),
						message.getOrdStatus().getValue(),
						message.getText().getValue());
			}

		};

		SessionSettings settings;
		try (InputStream inputStream = getClass().getResourceAsStream(
				"trade.cfg")) {
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

	public void demo() throws InterruptedException, FieldNotFound {
		String accReqId = UUID.randomUUID().toString();
		log.info("Requesting AccountInfo, accReqId: {}", accReqId);
		app.requestAccountInfo(accReqId, "btc", sessionId);

		String massStatusReqId = UUID.randomUUID().toString();
		log.info("Requesting mass order stauts, massStatusReqId: {}", massStatusReqId);
		app.requestOrderMassStatus(massStatusReqId,
				MassStatusReqType.STATUS_FOR_ALL_ORDERS, "btccny", sessionId);

		/*
		String clOrdId = UUID.randomUUID().toString();
		log.info("Placing a limit order:, clOrdId: {}", clOrdId);
		app.placeOrder(clOrdId, Side.BUY, OrdType.LIMIT, new BigDecimal("0.001"), new BigDecimal("1"), "btccny", sessionId);
		*/

		/*
		String orderId = "56539539";
		String clOrdId = UUID.randomUUID().toString();
		log.info("Canceling the order {}, requested cancelation ID: {}", orderId, clOrdId);
		app.cancelOrder(clOrdId, orderId, Side.BUY, "btccny", sessionId);
		*/
	}

	public static void main(String[] args) throws IOException, ConfigError,
			InterruptedException, FieldNotFound {
		final String accessKey = args[0];
		final String secretKey = args[1];

		TradeClient tradeClient = new TradeClient(accessKey, secretKey);
		tradeClient.demo();
	}

}
