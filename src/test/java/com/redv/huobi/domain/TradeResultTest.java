package com.redv.huobi.domain;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redv.huobi.valuereader.JsonValueReader;

public class TradeResultTest {

	private JsonValueReader<TradeResult> jvr = new JsonValueReader<>(
			new ObjectMapper(), TradeResult.class);

	@Test
	public void test0() throws IOException {
		try (InputStream inputStream = getClass().getResourceAsStream(
				"tradeResult0.json")) {
			TradeResult tr = jvr.read(inputStream);
			assertEquals(0, tr.getCode());
			assertEquals("卖单已委托，<a href=\"/trade/index.php?a=delegation\">查看结果</a>",
					tr.getMsg());
		}
	}

	@Test
	public void test2() throws IOException {
		try (InputStream inputStream = getClass().getResourceAsStream(
				"tradeResult2.json")) {
			TradeResult tr = jvr.read(inputStream);
			assertEquals(2, tr.getCode());
			assertEquals("没有足够的人民币", tr.getMsg());
		}
	}

}
