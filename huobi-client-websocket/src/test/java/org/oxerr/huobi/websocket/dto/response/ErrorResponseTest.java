package org.oxerr.huobi.websocket.dto.response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;
import org.oxerr.huobi.websocket.dto.response.payload.VoidPayload;

public class ErrorResponseTest extends AbstractResponseTest {

	@Test
	public void test() throws IOException {
		ErrorResponse r = fromJson("error-601.json", ErrorResponse.class);
		assertEquals(1, r.getVersion());
		assertEquals("test", r.getMsgType());
		assertEquals(0, r.getRequestIndex());
		assertEquals(601, r.getRetCode());
		assertEquals("", r.getRetMsg());
		VoidPayload p = r.getPayload();
		assertNotNull(p);
	}

}
