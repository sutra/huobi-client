package org.oxerr.huobi.websocket.dto;

import java.time.Instant;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Provides Gson object for Huobi WebSocket API implementation.
 */
public class GsonFactory {

	private static final Gson gson = buildGson();

	private static Gson buildGson() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Instant.class, new InstantAdapter());
		Gson gson = gsonBuilder.create();
		return gson;
	}

	public static Gson getGson() {
		return gson;
	}

}
