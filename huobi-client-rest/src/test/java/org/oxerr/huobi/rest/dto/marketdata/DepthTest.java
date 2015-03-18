package org.oxerr.huobi.rest.dto.marketdata;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;

public class DepthTest extends UnmarshalTest {

	@Test
	public void test() throws IOException {
		Depth depth = readValue(getClass(), "depth.json", Depth.class);
		assertEquals(new BigDecimal("90.7"), depth.getAsks()[0][0]);
		assertEquals(new BigDecimal("0.01"), depth.getAsks()[0][1]);
		assertEquals(new BigDecimal("86.06"), depth.getBids()[0][0]);
		assertEquals(new BigDecimal("79.243"), depth.getBids()[0][1]);
	}

}
