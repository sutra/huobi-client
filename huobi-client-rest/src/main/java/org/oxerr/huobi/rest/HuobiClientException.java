package org.oxerr.huobi.rest;

import java.io.IOException;

public class HuobiClientException extends IOException {

	private static final long serialVersionUID = 2014010401L;

	public HuobiClientException() {
	}

	public HuobiClientException(String message) {
		super(message);
	}

	public HuobiClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public HuobiClientException(Throwable cause) {
		super(cause);
	}

}
