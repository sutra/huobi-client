package org.oxerr.huobi.fix.field;

import quickfix.StringField;

public class AccReqID extends StringField {

	private static final long serialVersionUID = 20141101L;

	public static final int FIELD = 1622;

	public AccReqID() {
		super(FIELD);
	}

	public AccReqID(String data) {
		super(FIELD, data);
	}

}
