package com.redv.huobi.dto.marketdata;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;

public class HUOBIOrderBookTASTest extends UnmarshalTest {

	@Test
	public void testHUOBIOrderBookTAS() throws IOException {
		HUOBIOrderBookTAS obtas = readValue(getClass(), "detail.json", HUOBIOrderBookTAS.class);

		assertEquals(new BigDecimal("3904.7"), obtas.getSells()[0].getPrice());
		assertEquals(new BigDecimal("0"), obtas.getSells()[0].getLevel());
		assertEquals(new BigDecimal("0.3995"), obtas.getSells()[0].getAmount());

		assertEquals(new BigDecimal("3904.04"), obtas.getBuys()[0].getPrice());
		assertEquals(new BigDecimal("0"), obtas.getBuys()[0].getLevel());
		assertEquals(new BigDecimal("0.0624"), obtas.getBuys()[0].getAmount());

		assertEquals("13:19:57", obtas.getTrades()[0].getTime());
		assertEquals(new BigDecimal("3904.7"), obtas.getTrades()[0].getPrice());
		assertEquals(new BigDecimal("0.14"), obtas.getTrades()[0].getAmount());
		assertEquals("买入", obtas.getTrades()[0].getType());

		assertEquals(new BigDecimal("3904.7"), obtas.getPNew());
		assertEquals(new BigDecimal("-9.28"), obtas.getLevel());
		assertEquals(new BigDecimal("23886"), obtas.getAmount());
		assertEquals(new BigDecimal("93246158.340222"), obtas.getTotal());
		assertEquals(new BigDecimal("-0"), obtas.getAmp());
		assertEquals(new BigDecimal("3914"), obtas.getPOpen());
		assertEquals(new BigDecimal("3936"), obtas.getPHigh());
		assertEquals(new BigDecimal("3875.12"), obtas.getPLow());
		assertEquals(new BigDecimal("3914"), obtas.getPLast());

		assertEquals(new BigDecimal("3904.7"), obtas.getTopSell()[0].getPrice());
		assertEquals(new BigDecimal("0"), obtas.getTopSell()[0].getLevel());
		assertEquals(new BigDecimal("0.3995"), obtas.getTopSell()[0].getAmount());
		assertEquals(new BigDecimal("0.3995"), obtas.getTopSell()[0].getAccu());

		assertEquals(new BigDecimal("3904.04"), obtas.getTopBuy()[0].getPrice());
		assertEquals(new BigDecimal("0"), obtas.getTopBuy()[0].getLevel());
		assertEquals(new BigDecimal("0.0624"), obtas.getTopBuy()[0].getAmount());
		assertEquals(new BigDecimal("0.0624"), obtas.getTopBuy()[0].getAccu());
	}

}
