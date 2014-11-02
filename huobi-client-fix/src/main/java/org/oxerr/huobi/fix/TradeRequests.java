package org.oxerr.huobi.fix;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.oxerr.huobi.fix.field.AccReqID;
import org.oxerr.huobi.fix.fix44.AccountInfoRequest;

import quickfix.field.Account;
import quickfix.field.ClOrdID;
import quickfix.field.MassStatusReqID;
import quickfix.field.MassStatusReqType;
import quickfix.field.MinQty;
import quickfix.field.OrdType;
import quickfix.field.OrigClOrdID;
import quickfix.field.Price;
import quickfix.field.Side;
import quickfix.field.Symbol;
import quickfix.field.TransactTime;
import quickfix.fix44.NewOrderSingle;
import quickfix.fix44.OrderCancelRequest;
import quickfix.fix44.OrderMassStatusRequest;
import quickfix.fix44.OrderStatusRequest;

/**
 * Various methods for constructing requests for trading interface.
 */
public final class TradeRequests {

	private TradeRequests() {
	}

	public static AccountInfoRequest buildAccountInfoRequest(
			String accessKey,
			String symbol) {
		AccountInfoRequest message = new AccountInfoRequest();
		message.set(new Account(accessKey));
		message.set(new AccReqID(UUID.randomUUID().toString()));
		message.set(new Symbol(symbol));
		return message;
	}

	public static NewOrderSingle buildNewOrderSingle(
			String accessKey,
			String clOrdId,
			char side,
			char ordType,
			BigDecimal minQty,
			BigDecimal price,
			String symbol) {
		NewOrderSingle message = new NewOrderSingle(
				new ClOrdID(clOrdId),
				new Side(side),
				new TransactTime(new Date()),
				new OrdType(ordType));
		message.set(new Account(accessKey));
		message.set(new MinQty(minQty));
		message.set(new Price(price));
		message.set(new Symbol(symbol));
		return message;
	}

	public static OrderCancelRequest buildOrderCancelRequest(
			String origClOrdId,
			String clOrdId,
			char side,
			String symbol) {
		OrderCancelRequest message = new OrderCancelRequest(
				new OrigClOrdID(origClOrdId),
				new ClOrdID(clOrdId),
				new Side(side),
				new TransactTime(new Date()));
		message.set(new Symbol(symbol));
		return message;
	}

	public static OrderMassStatusRequest buildOrderMassStatusRequest(
			String accessKey,
			String massStatusReqId,
			int massStatusReqType,
			String symbol) {
		OrderMassStatusRequest message = new OrderMassStatusRequest(
				new MassStatusReqID(massStatusReqId),
				new MassStatusReqType(massStatusReqType));
		message.set(new Account(accessKey));
		message.set(new Symbol(symbol));
		return message;
	}

	public static OrderStatusRequest buildOrderStatusRequest(
			String accessKey,
			String clOrdId,
			char side,
			String symbol) {
		OrderStatusRequest message = new OrderStatusRequest(
				new ClOrdID(clOrdId),
				new Side(side));
		message.set(new Account(accessKey));
		message.set(new Symbol(symbol));
		return message;
	}

}
