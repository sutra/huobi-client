package org.oxerr.huobi.xchange.service.streaming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.service.streaming.ExchangeStreamingConfiguration;

public class HuobiStreamingConfiguration implements
		ExchangeStreamingConfiguration {

	private final Set<CurrencyPair> currencyPairs = new HashSet<>();

	public HuobiStreamingConfiguration(CurrencyPair... currencyPairs) {
		this(Arrays.asList(currencyPairs));
	}

	public HuobiStreamingConfiguration(Iterable<CurrencyPair> currencyPairs) {
		for (CurrencyPair currencyPair : currencyPairs) {
			this.currencyPairs.add(currencyPair);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getMaxReconnectAttempts() {
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getReconnectWaitTimeInMs() {
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getTimeoutInMs() {
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEncryptedChannel() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean keepAlive() {
		return false;
	}

	public Set<CurrencyPair> getCurrencyPairs() {
		return currencyPairs;
	}

}
