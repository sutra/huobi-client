package org.oxerr.huobi.websocket.dto.response;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;

public abstract class AbstractResponseTest {

	private Gson gson = new Gson();

	protected <T> T fromJson(String resource, Type type) throws IOException {
		try (Reader reader = new InputStreamReader(getClass()
				.getResourceAsStream(resource), StandardCharsets.UTF_8)) {
			return gson.fromJson(reader, type);
		}
	}

}
