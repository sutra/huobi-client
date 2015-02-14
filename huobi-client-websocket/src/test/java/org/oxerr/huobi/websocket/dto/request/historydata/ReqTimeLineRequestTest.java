package org.oxerr.huobi.websocket.dto.request.historydata;

import static org.junit.Assert.assertEquals;

import java.time.Instant;

import org.junit.Test;
import org.oxerr.huobi.websocket.dto.GsonFactory;

import com.google.gson.Gson;

public class ReqTimeLineRequestTest {

	private static Gson gson = GsonFactory.getGson();

	@Test
	public void test() {
		ReqTimeLineRequest req = new ReqTimeLineRequest(1, "btccny");
		req.setFrom(Instant.ofEpochMilli(1));
		req.setTo(Instant.ofEpochMilli(2));
		String json = gson.toJson(req);
		assertEquals("{\"from\":1,\"to\":2,\"symbolId\":\"btccny\",\"version\":1,\"msgType\":\"reqTimeLine\",\"requestIndex\":0}", json);
	}

}
