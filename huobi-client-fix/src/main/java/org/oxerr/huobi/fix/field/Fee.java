package org.oxerr.huobi.fix.field;

import java.math.BigDecimal;

import quickfix.DecimalField;

public class Fee extends DecimalField {

	private static final long serialVersionUID = 20141102L;

	public static final int FIELD = 1633;

	public Fee() {
		super(FIELD);
	}

	public Fee(BigDecimal data) {
		super(FIELD, data);
	}

	public Fee(double data) {
		super(FIELD, new BigDecimal(data));
	}

}
