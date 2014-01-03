package com.redv.huobi;

import java.io.IOException;

public class HUOBIClientException extends IOException {

	private static final long serialVersionUID = 2014010401L;

	public HUOBIClientException() {
	}

	public HUOBIClientException(String message) {
		super(message);
	}

	public HUOBIClientException(String message, Throwable cause) {
		super(message, cause);
	}

}
