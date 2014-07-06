package com.redv.huobi;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import si.mazi.rescu.ParamsDigest;

import com.redv.huobi.dto.account.HUOBIAccountInfo;
import com.redv.huobi.dto.marketdata.HUOBIDepth;
import com.redv.huobi.dto.marketdata.HUOBIOrderBookTAS;
import com.redv.huobi.dto.marketdata.HUOBITicker;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public interface HUOBI {

	@GET
	@Path("ticker_{symbol}_json.js")
	public HUOBITicker getTicker(@PathParam("symbol") String symbol);

	@GET
	@Path("depth_{symbol}_json.js")
	public HUOBIDepth getDepth(@PathParam("symbol") String symbol);

	@GET
	@Path("{symbol}_kline_{period}_json.js")
	public String[][] getKline(
			@PathParam("symbol") String symbol,
			@PathParam("period") String period);

	@GET
	@Path("detail_{symbol}_json.js")
	public HUOBIOrderBookTAS getDetail(@PathParam("symbol") String symbol);

	@POST
	@Path("apiv2.php")
	public HUOBIAccountInfo getAccountInfo(
			@FormParam("method") String method,
			@FormParam("access_key") String accessKey,
			@FormParam("created") long created,
			@FormParam("sign") ParamsDigest sign);

}
