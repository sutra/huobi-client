package org.oxerr.huobi.fix.field;

import java.math.BigDecimal;

import quickfix.DecimalField;

public class Vot extends DecimalField {

	private static final long serialVersionUID = 20141102L;

	public static final int FIELD = 1632;

	public Vot() {
		super(FIELD);
	}

	public Vot(BigDecimal data) {
		super(FIELD, data);
	}

	public Vot(double data) {
		super(FIELD, new BigDecimal(data));
	}

}
