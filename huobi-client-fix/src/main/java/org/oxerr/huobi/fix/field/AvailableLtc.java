package org.oxerr.huobi.fix.field;

import java.math.BigDecimal;

import quickfix.DecimalField;

public class AvailableLtc extends DecimalField {

	private static final long serialVersionUID = 20141101L;

	public static final int FIELD = 1625;

	public AvailableLtc() {
		super(FIELD);
	}

	public AvailableLtc(BigDecimal data) {
		super(FIELD, data);
	}

	public AvailableLtc(double data) {
		super(FIELD, new BigDecimal(data));
	}

}
