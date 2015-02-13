package org.oxerr.huobi.fix;

import quickfix.field.MDEntryType;
import quickfix.field.MDReqID;
import quickfix.field.MarketDepth;
import quickfix.field.SubscriptionRequestType;
import quickfix.field.Symbol;
import quickfix.fix44.MarketDataRequest;
import quickfix.fix44.MarketDataRequest.NoMDEntryTypes;
import quickfix.fix44.MarketDataRequest.NoRelatedSym;

public final class MarketDataRequests {

	private MarketDataRequests() {
	}

	public static MarketDataRequest buildMarketDataRequest(
			String mdReqId,
			String symbol,
			char subscriptionRequestType,
			int marketDepth,
			int mdUpdateType,
			char... mdEntryTypes) {
		MarketDataRequest message = new MarketDataRequest();

		NoRelatedSym symGroup = new NoRelatedSym();
		symGroup.set(new Symbol(symbol));
		message.addGroup(symGroup);

		message.set(new MDReqID(mdReqId));
		message.set(new SubscriptionRequestType(subscriptionRequestType));
		message.set(new MarketDepth(marketDepth));
		// message.set(new MDUpdateType(mdUpdateType));

		for (char mdEntryType : mdEntryTypes) {
			NoMDEntryTypes entryTypesGroup = new NoMDEntryTypes();
			entryTypesGroup.set(new MDEntryType(mdEntryType));
			message.addGroup(entryTypesGroup);
		}

		return message;
	}

}
