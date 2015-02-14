package org.oxerr.huobi.websocket.dto.response.payload;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;
import org.oxerr.huobi.websocket.dto.Depth;
import org.oxerr.huobi.websocket.dto.DepthDiff;
import org.oxerr.huobi.websocket.dto.response.AbstractResponseTest;
import org.oxerr.huobi.websocket.dto.response.historydata.ReqMarketDepthResponse;
import org.oxerr.huobi.websocket.dto.response.marketdata.MarketDepthDiff;

public class ReqMarketDepthTopPayloadTest extends AbstractResponseTest {

	@Test
	public void testMerge1() throws IOException {
		testMerge("1416496077000/reqMarketDepth.json", 1416496077000L,
				new String[] { "1416496077000/marketDepthDiff.json", },
				new long[] { 1416496078000L, });
	}

	@Test
	public void testMerge2() throws IOException {
		testMerge("1416498909000/reqMarketDepth.json",
				1416498909000L,
				new String[] { "1416498909000/marketDepthDiff1.json",
				"1416498909000/marketDepthDiff2.json", },
				new long[] { 1416498910000L, 1416498911000L });
	}

	public void testMerge(String depthJson, long initialVersion,
			String[] diffJsons, long[] versions) throws IOException {

		ReqMarketDepthResponse reqMarketDepthResponse = fromJson(depthJson,
				ReqMarketDepthResponse.class);
		Depth depth = reqMarketDepthResponse.getPayload();

		assertEquals(initialVersion, depth.getVersion());
		assertBid(depth.getBidPrice());
		assertAsk(depth.getAskPrice());

		// Merge
		for (int i = 0, l = diffJsons.length; i < l; i++) {
			MarketDepthDiff marketDepthDiff = fromJson(diffJsons[i],
					MarketDepthDiff.class);
			DepthDiff depthDiff = marketDepthDiff.getPayload();

			depth.merge(depthDiff);

			assertEquals(versions[i], depth.getVersion());
			assertBid(depth.getBidPrice());
			assertAsk(depth.getAskPrice());
		}
	}


	private void assertBid(BigDecimal[] bidPrices) {
		for (int i = 1, l = bidPrices.length; i < l; i++) {
			assertEquals(String.format(
					"Price(%f) at %d should be greater than price(%f) at %d",
					bidPrices[i - 1], i - 1, bidPrices[i], i), 1,
					bidPrices[i - 1].compareTo(bidPrices[i]));
		}
	}

	private void assertAsk(BigDecimal[] askPrices) {
		for (int i = 1, l = askPrices.length; i < l; i++) {
			assertEquals(String.format(
					"Price(%f) at %d should be less than price(%f) at %d",
					askPrices[i - 1], i - 1, askPrices[i], i), -1,
					askPrices[i - 1].compareTo(askPrices[i]));
		}
	}

}
