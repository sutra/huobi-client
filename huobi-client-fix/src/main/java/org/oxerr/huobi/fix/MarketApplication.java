package org.oxerr.huobi.fix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quickfix.FieldNotFound;
import quickfix.IncorrectTagValue;
import quickfix.SessionID;
import quickfix.UnsupportedMessageType;
import quickfix.fix44.MarketDataSnapshotFullRefresh;
import quickfix.fix44.Reject;

public class MarketApplication extends HuobiApplication {

	private final Logger log = LoggerFactory.getLogger(MarketApplication.class);

	@Override
	public void onMessage(MarketDataSnapshotFullRefresh message,
			SessionID sessionId) throws FieldNotFound, UnsupportedMessageType,
			IncorrectTagValue {
		log.info("{}", message);
	}

	@Override
	public void onMessage(Reject message, SessionID sessionId)
			throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {
		log.error("{}", message);
	}

}
