package com.redv.huobi;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redv.huobi.domain.Delegation;
import com.redv.huobi.domain.Depth;
import com.redv.huobi.domain.Funds;
import com.redv.huobi.domain.LoginResult;
import com.redv.huobi.domain.TradeResult;
import com.redv.huobi.domain.Type;
import com.redv.huobi.valuereader.DelegationReader;
import com.redv.huobi.valuereader.JsonValueReader;
import com.redv.huobi.valuereader.LoginResultReader;
import com.redv.huobi.valuereader.VoidValueReader;

public class HUOBIClient {

	public static final String ENCODING = "UTF-8";

	private static final URI HTTPS_BASE = URI.create("https://www.huobi.com/");

	private static final URI LOGIN_URI = URIUtils.resolve(HTTPS_BASE, "account/login.php");

	private static final URI DEPTH_URI = URI.create("https://market.huobi.com/market/depth.php");

	private static final URI TRADE_URI = URIUtils.resolve(HTTPS_BASE, "trade/index.php");

	private final Logger log = LoggerFactory.getLogger(HUOBIClient.class);

	private final HttpClient httpClient;

	private final String email;

	private final String password;

	public HUOBIClient() {
		this(null, null);
	}

	public HUOBIClient(String email, String password) {
		httpClient = new HttpClient();
		this.email = email;
		this.password = password;
	}

	public void login() throws IOException {
		initLoginPage();

		LoginResult loginResult = httpClient.post(
				LOGIN_URI,
				new LoginResultReader(),
				new BasicNameValuePair("email", email),
				new BasicNameValuePair("password", password));
		log.debug("Login result: {}", loginResult);
	}

	public Depth getDepth() throws IOException {
		final URI depthUri;
		try {
			depthUri = new URIBuilder(DEPTH_URI)
				.setParameter("a", "marketdepth")
				.setParameter("random", String.valueOf(Math.random()))
				.build();
		} catch (URISyntaxException e) {
			throw new IOException(e);
		}

		return httpClient.get(depthUri, Depth.class);
	}

	public Funds getFunds() throws IOException {
		LoginResult loginResult = httpClient.get(HTTPS_BASE,
				new LoginResultReader());
		return loginResult.getFunds();
	}

	public BigDecimal getMinAmountPerOrder() {
		return new BigDecimal("0.0001");
	}

	public void buy(BigDecimal price, BigDecimal amount) throws IOException {
		trade(Type.BUY, price, amount);
	}

	public void sell(BigDecimal price, BigDecimal amount) throws IOException {
		trade(Type.SELL, price, amount);
	}

	/**
	 * Cancels the delegation with the given ID and returns the left delegations.
	 *
	 * @param id the ID of the delegation to cancel.
	 * @return the left delegations.
	 * @throws IOException indicates I/O exception.
	 */
	public List<Delegation> cancel(long id) throws IOException {
		URI uri;
		try {
			uri = new URIBuilder(TRADE_URI)
				.setParameter("a", "cancel")
				.setParameter("id", String.valueOf(id))
				.build();
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException(e);
		}

		return httpClient.get(uri, new DelegationReader());
	}

	public List<Delegation> getDelegations() throws IOException {
		URI uri;
		try {
			uri = new URIBuilder(TRADE_URI)
				.setParameter("a", "delegation")
				.build();
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException(e);
		}
		return httpClient.get(uri, new DelegationReader());
	}

	private void trade(Type type, BigDecimal price, BigDecimal amount)
			throws IOException {
		List<NameValuePair> params = new ArrayList<>(3);
		params.add(new BasicNameValuePair("a", type.toString()));
		params.add(new BasicNameValuePair("price", price.toPlainString()));
		params.add(new BasicNameValuePair("amount", amount.toPlainString()));

		HttpPost post = new HttpPost(TRADE_URI);
		post.setHeader("X-Requested-With", "XMLHttpRequest");
		post.setEntity(new UrlEncodedFormEntity(params));

		TradeResult tradeResult = httpClient.execute(
				new JsonValueReader<>(new ObjectMapper(), TradeResult.class),
				post);

		if (tradeResult.getCode() != 0) {
			throw new HUOBIClientException(tradeResult.getMsg());
		}
	}

	/**
	 * Calls this method before doing login post is required.
	 */
	private void initLoginPage() throws IOException {
		httpClient.get(HTTPS_BASE, VoidValueReader.getInstance());
	}

}
