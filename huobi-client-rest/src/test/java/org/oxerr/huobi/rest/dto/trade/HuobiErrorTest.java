package org.oxerr.huobi.rest.dto.trade;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.oxerr.huobi.rest.dto.marketdata.UnmarshalTest;
import org.oxerr.huobi.rest.dto.trade.HuobiError;

public class HuobiErrorTest extends UnmarshalTest {

	@Test
	public void test() throws IOException {
		HuobiError error = readValue(getClass(), "error.json", HuobiError.class);
		assertEquals(65, error.getCode());
		assertEquals("无效的方法", error.getMsg());
		assertEquals(1404651007, error.getTime());
	}

}
