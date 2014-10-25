package com.redv.huobi.dto.account;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;

import com.redv.huobi.dto.marketdata.UnmarshalTest;

public class HUOBIAccountInfoTest extends UnmarshalTest {

	@Test
	public void test() throws IOException {
		HUOBIAccountInfo accountInfo = readValue(getClass(), "account-info.json", HUOBIAccountInfo.class);
		assertEquals(new BigDecimal("4459.38"), accountInfo.getTotal());
		assertEquals(new BigDecimal("4459.38"), accountInfo.getNetAsset());
		assertEquals(new BigDecimal("2985.40"), accountInfo.getAvailableCnyDisplay());
		assertEquals(new BigDecimal("0.3244"), accountInfo.getAvailableBtcDisplay());
		assertEquals(new BigDecimal("0.1000"), accountInfo.getAvailableLtcDisplay());
		assertEquals(new BigDecimal("0.20"), accountInfo.getFrozenCnyDisplay());
		assertEquals(new BigDecimal("0.0530"), accountInfo.getFrozenBtcDisplay());
		assertEquals(new BigDecimal("0.3000"), accountInfo.getFrozenLtcDisplay());
		assertEquals(new BigDecimal("0.40"), accountInfo.getLoanCnyDisplay());
		assertEquals(new BigDecimal("0.5000"), accountInfo.getLoanBtcDisplay());
		assertEquals(new BigDecimal("0.6000"), accountInfo.getLoanLtcDisplay());
	}

}
