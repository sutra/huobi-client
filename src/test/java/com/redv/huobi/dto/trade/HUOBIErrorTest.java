package com.redv.huobi.dto.trade;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.redv.huobi.dto.marketdata.UnmarshalTest;

public class HUOBIErrorTest extends UnmarshalTest {

	@Test
	public void test() throws IOException {
		HUOBIError error = readValue(getClass(), "error.json", HUOBIError.class);
		assertEquals(65, error.getCode());
		assertEquals("无效的方法", error.getMsg());
		assertEquals(1404651007, error.getTime());
	}

}
