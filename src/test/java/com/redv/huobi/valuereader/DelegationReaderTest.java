package com.redv.huobi.valuereader;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;

import com.redv.huobi.domain.Delegation;
import com.redv.huobi.domain.Type;

public class DelegationReaderTest {

	private DelegationReader delegationReader = new DelegationReader();

	@Test
	public void testRead() throws IOException {
		try (InputStream inputStream = getClass().getResourceAsStream(
				"delegation.html")) {
			List<Delegation> delegations = delegationReader.read(inputStream);

			assertEquals(3, delegations.size());

			Delegation delegation = delegations.get(0);
			assertEquals("2014.01.04 19:16", new SimpleDateFormat(
					"yyyy.MM.dd HH:mm").format(delegation.getDate()));
			assertEquals(Type.SELL, delegation.getType());
			assertEquals(new BigDecimal("99999.000"), delegation.getPrice());
			assertEquals(new BigDecimal("0.0001"), delegation.getAmount());
			assertEquals(new BigDecimal("9.999"), delegation.getTrading());
			assertEquals(new BigDecimal("0.000"), delegation.getFee());
			assertEquals(new BigDecimal("9.999"), delegation.getTotal());

			assertEquals(3536208, delegation.getId());
		}
	}

}
