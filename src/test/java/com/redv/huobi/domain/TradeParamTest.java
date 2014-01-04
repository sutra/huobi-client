package com.redv.huobi.domain;

import static java.math.BigDecimal.ONE;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;

public class TradeParamTest {

	private final Logger log = LoggerFactory.getLogger(TradeParamTest.class);

	@Test
	public void testToJson() throws JsonProcessingException {
		TradeParam param = new TradeParam(Type.SELL, ONE, ONE);
		String expected = "{\"a\":\"do_sell\",\"price\":1,\"amount\":1}";
		assertEquals(expected, param.toJson());
	}

	@Test
	public void testToJsonBigDecimal() throws JsonProcessingException {
		TradeParam param = new TradeParam(Type.SELL, new BigDecimal(Long.MAX_VALUE), ONE);
		String expected = "{\"a\":\"do_sell\",\"price\":" + Long.MAX_VALUE + ",\"amount\":1}";
		log.debug("Expected: {}", expected);
		assertEquals(expected, param.toJson());
	}

}
