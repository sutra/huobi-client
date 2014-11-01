package org.oxerr.huobi.fix.fix44;

import quickfix.Message;
import quickfix.MessageFactory;

/**
 * {@link MessageFactory} that added Huobi customized message support.
 */
public class HuobiMessageFactory extends quickfix.fix44.MessageFactory {

	@Override
	public Message create(String beginString, String msgType) {
		if (AccountInfoResponse.MSGTYPE.equals(msgType)) {
			return new AccountInfoResponse();
		}

		return super.create(beginString, msgType);
	}

}
