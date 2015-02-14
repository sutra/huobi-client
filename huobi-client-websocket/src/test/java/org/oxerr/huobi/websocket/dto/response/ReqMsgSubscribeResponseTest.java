package org.oxerr.huobi.websocket.dto.response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;
import org.oxerr.huobi.websocket.dto.response.payload.VoidPayload;
import org.oxerr.huobi.websocket.dto.response.service.ReqMsgSubscribeResponse;

public class ReqMsgSubscribeResponseTest extends AbstractResponseTest {

	@Test
	public void testReqMsgSubscribeResponse() throws IOException {
		ReqMsgSubscribeResponse r = fromJson("reqMsgSubscribe.json",
				ReqMsgSubscribeResponse.class);
		assertEquals(1, r.getVersion());
		assertEquals("reqMsgSubscribe", r.getMsgType());
		assertEquals(0, r.getRequestIndex());
		assertEquals(200, r.getRetCode());
		assertEquals("", r.getRetMsg());
		VoidPayload p = r.getPayload();
		assertNotNull(p);
	}

}
