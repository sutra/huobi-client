package org.oxerr.huobi.websocket.dto.response.marketdata.payload;

import java.math.BigDecimal;

import org.oxerr.huobi.websocket.dto.response.marketdata.TradeDetail;
import org.oxerr.huobi.websocket.dto.response.payload.Orders;
import org.oxerr.huobi.websocket.dto.response.payload.ReqTradeDetailTopPayload;

/**
 * Payload of {@link TradeDetail}.
 */
public class TradeDetailPayload extends ReqTradeDetailTopPayload implements
		org.oxerr.huobi.websocket.dto.TradeDetail {

	public TradeDetailPayload(String symbolId, long[] tradeId,
			BigDecimal[] price, long[] time, BigDecimal[] amount,
			int[] direction, Orders[] topAsks, Orders[] topBids) {
		super(symbolId, tradeId, price, time, amount, direction, topAsks,
				topBids);
	}

}
