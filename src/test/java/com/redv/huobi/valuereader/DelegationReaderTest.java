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
			assertEquals(12787624, delegation.getId());
			assertEquals("2014.04.12 01:42", new SimpleDateFormat(
					"yyyy.MM.dd HH:mm").format(delegation.getDate()));
			assertEquals(Type.BUY, delegation.getType());
			assertEquals(new BigDecimal("0.10"), delegation.getPrice());
			assertEquals(new BigDecimal("0.0010"), delegation.getAmount());
			assertEquals(new BigDecimal("0.00"), delegation.getTrading());
			assertEquals(null, delegation.getFee());
			assertEquals(null, delegation.getTotal());
		}
	}

}
