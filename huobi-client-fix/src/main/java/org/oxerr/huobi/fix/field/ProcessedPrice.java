package org.oxerr.huobi.fix.field;

import java.math.BigDecimal;

import quickfix.DecimalField;

public class ProcessedPrice extends DecimalField {

	private static final long serialVersionUID = 20141102L;

	public static final int FIELD = 1630;

	public ProcessedPrice() {
		super(FIELD);
	}

	public ProcessedPrice(BigDecimal data) {
		super(FIELD, data);
	}

	public ProcessedPrice(double data) {
		super(FIELD, new BigDecimal(data));
	}

}
