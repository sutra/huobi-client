package org.oxerr.huobi.fix.field;

import java.math.BigDecimal;

import quickfix.DecimalField;

public class AvailableCny extends DecimalField {

	private static final long serialVersionUID = 20141101L;

	public static final int FIELD = 1623;

	public AvailableCny() {
		super(FIELD);
	}

	public AvailableCny(BigDecimal data) {
		super(FIELD, data);
	}

	public AvailableCny(double data) {
		super(FIELD, new BigDecimal(data));
	}

}
