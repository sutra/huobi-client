package org.oxerr.huobi.rest.domain;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import org.junit.Test;
import org.oxerr.huobi.rest.domain.MyTradeInfo;
import org.oxerr.huobi.rest.domain.MyTradeInfo.Extra.Balance;
import org.oxerr.huobi.rest.valuereader.JsonValueReader;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MyTradeInfoTest {

	private JsonValueReader<MyTradeInfo> jvr = new JsonValueReader<>(
			new ObjectMapper(), MyTradeInfo.class);

	@Test
	public void test() throws IOException {
		try (InputStream inputStream = getClass().getResourceAsStream(
				"my_trade_info.json")) {
			MyTradeInfo info = jvr.read(inputStream);

			assertEquals(0, info.getCode());
			assertEquals("", info.getMsg());

			assertEquals(new BigDecimal("4837.000"), info.getExtra().getSell().getPrice());
			assertEquals(new BigDecimal("0.999"), info.getExtra().getSell().getAmount());
			assertEquals(new BigDecimal("0.9999"), info.getExtra().getSell().getAvailableBtc());

			assertEquals(new BigDecimal("0.000"), info.getExtra().getBuy().getPrice());
			assertEquals(new BigDecimal("0.000"), info.getExtra().getBuy().getAmount());
			assertEquals(new BigDecimal("0.00"), info.getExtra().getBuy().getAvailableCny());

			Balance balance = info.getExtra().getBalance();
			assertEquals(1L, balance.getId());
			assertEquals(new BigDecimal("0"), balance.getInitializedCny());
			assertEquals(new BigDecimal("0"), balance.getInitializedBtc());
			assertEquals(1L, balance.getUserId());
			assertEquals(new BigDecimal("0"), balance.getAvailableCny());
			assertEquals(new BigDecimal("0.9999000000"), balance.getAvailableBtc());
			assertEquals(new BigDecimal("0"), balance.getAvailableUsd());
			assertEquals(new BigDecimal("0"), balance.getFrozenCny());
			assertEquals(new BigDecimal("0"), balance.getFrozenBtc());
			assertEquals(new BigDecimal("0"), balance.getFrozenUsd());
			assertEquals(new BigDecimal("0"), balance.getDebtBitcoin());
			assertEquals(new BigDecimal("0"), balance.getDebtRmb());
			assertEquals(new BigDecimal("4836.51"), balance.getTotal());
			assertEquals(new BigDecimal("0.00"), balance.getLoanTotal());
			assertEquals(new BigDecimal("4836.51"), balance.getNetAsset());
			assertEquals(new BigDecimal("0.00"), balance.getLoanCnyDisplay());
			assertEquals(new BigDecimal("0.0000"), balance.getLoanBtcDisplay());
			assertEquals(new BigDecimal("0.9999"), balance.getAvailableBtcDisplay());
			assertEquals(new BigDecimal("0.00"), balance.getAvailableCnyDisplay());
			assertEquals(new BigDecimal("0.0000"), balance.getFrozenBtcDisplay());
			assertEquals(new BigDecimal("0.00"), balance.getFrozenCnyDisplay());
		}
	}

}
