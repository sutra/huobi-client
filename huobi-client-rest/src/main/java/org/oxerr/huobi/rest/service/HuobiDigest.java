package org.oxerr.huobi.rest.service;

import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.FormParam;

import si.mazi.rescu.Params;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestInvocation;

public class HuobiDigest implements ParamsDigest {

	private final String secretKey;

	public HuobiDigest(final String secretKey) {
		this.secretKey = secretKey;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String digestParams(RestInvocation restInvocation) {
		final Params params = restInvocation.getParamsMap().get(FormParam.class);

		final Map<String, String> nameValueMap = params.asHttpHeaders();
		nameValueMap.remove("sign");
		nameValueMap.remove("trade_password");
		nameValueMap.put("secret_key", secretKey);

		final List<Map.Entry<String, String>> nameValueList = new ArrayList<>(
				nameValueMap.entrySet());
		Collections.sort(nameValueList, new Comparator<Map.Entry<String, String>>() {
			@Override
			public int compare(
					Entry<String, String> o1,
					Entry<String, String> o2) {
				return o1.getKey().compareTo(o2.getKey());
			}
		});

		final Params newParams = Params.of();
		for (Map.Entry<String, String> param : nameValueList) {
			newParams.add(param.getKey(), param.getValue());
		}

		final String message = newParams.asQueryString();

		return md5Hex(message);
	}

}
