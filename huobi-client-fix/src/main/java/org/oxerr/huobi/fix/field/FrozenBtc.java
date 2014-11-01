package org.oxerr.huobi.fix.field;

import java.math.BigDecimal;

import quickfix.DecimalField;

public class FrozenBtc extends DecimalField {

	private static final long serialVersionUID = 20141101L;

	public static final int FIELD = 1627;

	public FrozenBtc() {
		super(FIELD);
	}

	public FrozenBtc(BigDecimal data) {
		super(FIELD, data);
	}

	public FrozenBtc(double data) {
		super(FIELD, new BigDecimal(data));
	}

}
