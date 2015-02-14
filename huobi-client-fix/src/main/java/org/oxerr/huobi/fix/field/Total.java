package org.oxerr.huobi.fix.field;

import java.math.BigDecimal;

import quickfix.DecimalField;

public class Total extends DecimalField {

	private static final long serialVersionUID = 20141102L;

	public static final int FIELD = 1634;

	public Total() {
		super(FIELD);
	}

	public Total(BigDecimal data) {
		super(FIELD, data);
	}

	public Total(double data) {
		super(FIELD, new BigDecimal(data));
	}

}
