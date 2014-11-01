package org.oxerr.huobi.fix.field;

import java.math.BigDecimal;

import quickfix.DecimalField;

public class AvailableBtc extends DecimalField {

	private static final long serialVersionUID = 20141101L;

	public static final int FIELD = 1624;

	public AvailableBtc() {
		super(FIELD);
	}

	public AvailableBtc(BigDecimal data) {
		super(FIELD, data);
	}

	public AvailableBtc(double data) {
		super(FIELD, new BigDecimal(data));
	}

}
