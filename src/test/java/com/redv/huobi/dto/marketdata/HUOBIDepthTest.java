package com.redv.huobi.dto.marketdata;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;

public class HUOBIDepthTest extends UnmarshalTest {

	@Test
	public void test() throws IOException {
		HUOBIDepth depth = readValue("depth.json", HUOBIDepth.class);
		assertEquals(new BigDecimal("90.8"), depth.getAsks()[0][0]);
		assertEquals(new BigDecimal("0.5"), depth.getAsks()[0][1]);
		assertEquals(new BigDecimal("86.06"), depth.getBids()[0][0]);
		assertEquals(new BigDecimal("79.243"), depth.getBids()[0][1]);
	}

}
