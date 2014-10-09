package com.redv.huobi.dto.marketdata;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class HUOBIOHLCVTest extends UnmarshalTest {

	@Test
	public void test() throws IOException {
		String[][] ohlcv = readValue(getClass(), "ohlcv.json", String[][].class);

		// time
		assertEquals("20130101000000000", ohlcv[0][0]);

		// open
		assertEquals("770.0000", ohlcv[0][1]);

		// high
		assertEquals("8000", ohlcv[0][2]);

		// low
		assertEquals("679.107", ohlcv[0][3]);

		// close
		assertEquals("4373", ohlcv[0][4]);

		// volume
		assertEquals("2948602.5158", ohlcv[0][5]);

		// time
		assertEquals("20140101000000000", ohlcv[1][0]);

		// open
		assertEquals("4373.1", ohlcv[1][1]);

		// high
		assertEquals("5999", ohlcv[1][2]);

		// low
		assertEquals("2223.22", ohlcv[1][3]);

		// close
		assertEquals("3898.01", ohlcv[1][4]);

		// volume
		assertEquals("14126124.983874", ohlcv[1][5]);
	}

}
