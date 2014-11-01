package org.oxerr.huobi.fix;

import org.oxerr.huobi.fix.fix44.AccountInfoRequest;
import org.oxerr.huobi.fix.fix44.AccountInfoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quickfix.Application;
import quickfix.FieldNotFound;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
import quickfix.Message;
import quickfix.SessionID;
import quickfix.UnsupportedMessageType;
import quickfix.field.MsgType;
import quickfix.field.Password;
import quickfix.field.Username;

/**
 * {@link Application} for trading interface.
 */
public class TradeApplication extends HuobiApplication {

	private final Logger log = LoggerFactory.getLogger(TradeApplication.class);

	private final String accessKey;
	private final String secretKey;

	public TradeApplication(String accessKey, String secretKey) {
		this.accessKey = accessKey;
		this.secretKey = secretKey;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void toAdmin(Message message, SessionID sessionId) {
		super.toAdmin(message, sessionId);

		String msgType;
		try {
			msgType = message.getHeader().getString(MsgType.FIELD);
		} catch (FieldNotFound e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		if (MsgType.LOGON.equals(msgType)) {
			message.setField(new Username(accessKey));
			message.setField(new Password(secretKey));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fromApp(Message message, SessionID sessionId)
			throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue,
			UnsupportedMessageType {
		log.trace("fromApp: {}", message);
		MsgType msgType = new MsgType();
		message.getHeader().getField(msgType);
		String msgTypeValue = msgType.getValue();

		if (msgTypeValue.equals(AccountInfoResponse.MSGTYPE)) {
			log.info("{}", message.getClass());
			onMessage((AccountInfoResponse) message, sessionId);
		} else {
			crack(message, sessionId);
		}
	}

	public void onMessage(AccountInfoResponse message, SessionID sessionId)
			throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {
	}

	public void requestAccountInfo(String symbol, SessionID sessionId) {
		AccountInfoRequest message = TradeRequests.buildAccountInfoRequest(
				accessKey, symbol);
		sendMessage(message, sessionId);
	}

}
