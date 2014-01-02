package com.redv.huobi.domain;

import java.math.BigDecimal;

import com.redv.huobi.domain.Depth.Marketdepth.Data;

public class Depth extends AbstractObject {

	private static final long serialVersionUID = 2014010201L;

	private Marketdepth[] marketdepth;

	public Data[] getBids() {
		return marketdepth[0].getData();
	}

	public Data[] getAsks() {
		return marketdepth[1].getData();
	}

	public void setMarketdepth(Marketdepth[] marketdepth) {
		this.marketdepth = marketdepth;
	}

	public static class Marketdepth extends AbstractObject {

		private static final long serialVersionUID = 2014010201L;

		private String name;

		private Data[] data;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Data[] getData() {
			return data;
		}

		public void setData(BigDecimal[][] data) {
			this.data = new Data[data.length];
			for (int i = 0; i < data.length; i++) {
				this.data[i] = new Data(data[i]);
			}
		}

		public static class Data extends AbstractObject {

			private static final long serialVersionUID = 2014010201L;

			private BigDecimal[] points;

			public Data(BigDecimal[] points) {
				this.points = points;
			}

			public BigDecimal getPrice() {
				return points[0];
			}

			public BigDecimal getTotal() {
				return points[1];
			}

			public BigDecimal getAmount() {
				return points[2];
			}

		}
	}
}

