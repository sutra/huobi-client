package com.redv.huobi;

import java.io.IOException;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redv.huobi.domain.Depth;
import com.redv.huobi.domain.Depth.Marketdepth.Data;
import com.redv.huobi.domain.Funds;

public class Main {

	private static final Logger log = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws IOException {
		final String email = args[0];
		final String password = args[1];

		HUOBIClient client = new HUOBIClient(email, password);

		// Get funds before login
		try {
			client.getFunds();
		} catch (LoginRequiredException e) {
			log.debug("login required.");
		}

		// Login
		client.login();

		// Depth
		Depth depth = client.getDepth();
		log.debug("Depth: {}", depth);

		for (Data bid : depth.getBids()) {
			log.debug("Bid: {}", bid.getPrice());
		}

		for (Data ask : depth.getAsks()) {
			log.debug("Ask: {}", ask.getPrice());
		}

		// Funds
		Funds funds = client.getFunds();
		log.debug("Funds: {}", funds);

		// buy
		client.buy(new BigDecimal("0.1"), new BigDecimal("0.0001"));
	}

}
