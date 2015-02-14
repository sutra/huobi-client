package org.oxerr.huobi.websocket.dto;

import java.math.BigDecimal;

import org.oxerr.huobi.websocket.dto.response.payload.Orders;

public interface TradeDetail {

	String getSymbolId();
	long[] getTradeId();
	BigDecimal[] getPrice();
	long[] getTime();
	BigDecimal[] getAmount();
	int[] getDirection();
	Orders[] getTopAsks();
	Orders[] getTopBids();

}
