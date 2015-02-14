package org.oxerr.huobi.rest.dto.trade;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;
import org.oxerr.huobi.rest.dto.marketdata.UnmarshalTest;
import org.oxerr.huobi.rest.dto.trade.Order;

public class HuobiOrderTest extends UnmarshalTest {

	@Test
	public void testOrders() throws IOException {
		Order[] orders = readValue(getClass(), "orders.json", Order[].class);
		assertEquals(8, orders.length);
		assertEquals(25502826L, orders[0].getId());
		assertEquals(2, orders[0].getType());
		assertEquals(new BigDecimal("3964.00"), orders[0].getOrderPrice());
		assertEquals(new BigDecimal("0.0100"), orders[0].getOrderAmount());
		assertEquals(new BigDecimal("0.0000"), orders[0].getProcessedAmount());
		assertEquals(1404469218L, orders[0].getOrderTime());
	}

	@Test
	public void testOrder() throws IOException {
		Order order = readValue(getClass(), "order.json", Order.class);
		assertEquals(25502826L, order.getId());
		assertEquals(2, order.getType());
		assertEquals(new BigDecimal("3964.00"), order.getOrderPrice());
		assertEquals(new BigDecimal("0.0100"), order.getOrderAmount());
		assertEquals(new BigDecimal("0.0000"), order.getProcessedAmount());
		assertEquals(new BigDecimal("0.00"), order.getProcessedPrice());
		assertEquals(new BigDecimal("0.00"), order.getTotal());
		assertEquals(new BigDecimal("0.00"), order.getFee());
		assertEquals(new BigDecimal("0.00"), order.getVot());
	}

}
