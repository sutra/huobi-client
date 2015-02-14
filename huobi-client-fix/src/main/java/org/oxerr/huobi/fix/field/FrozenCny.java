package org.oxerr.huobi.fix.field;

import java.math.BigDecimal;

import quickfix.DecimalField;

public class FrozenCny extends DecimalField {

	private static final long serialVersionUID = 20141101L;

	public static final int FIELD = 1628;

	public FrozenCny() {
		super(FIELD);
	}

	public FrozenCny(BigDecimal data) {
		super(FIELD, data);
	}

	public FrozenCny(double data) {
		super(FIELD, new BigDecimal(data));
	}

}
