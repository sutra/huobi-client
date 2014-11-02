package org.oxerr.huobi.fix.field;

import java.math.BigDecimal;

import quickfix.DecimalField;

public class ProcessedAmount extends DecimalField {

	private static final long serialVersionUID = 20141102L;

	public static final int FIELD = 1631;

	public ProcessedAmount() {
		super(FIELD);
	}

	public ProcessedAmount(BigDecimal data) {
		super(FIELD, data);
	}

	public ProcessedAmount(double data) {
		super(FIELD, new BigDecimal(data));
	}

}
