package org.oxerr.huobi.fix.field;

import java.math.BigDecimal;

import quickfix.DecimalField;

public class FrozenLtc extends DecimalField {

	private static final long serialVersionUID = 20141101L;

	public static final int FIELD = 1626;

	public FrozenLtc() {
		super(FIELD);
	}

	public FrozenLtc(BigDecimal data) {
		super(FIELD, data);
	}

	public FrozenLtc(double data) {
		super(FIELD, new BigDecimal(data));
	}

}
