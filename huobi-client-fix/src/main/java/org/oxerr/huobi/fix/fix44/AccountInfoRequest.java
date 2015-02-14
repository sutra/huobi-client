package org.oxerr.huobi.fix.fix44;

import org.oxerr.huobi.fix.field.AccReqID;

import quickfix.FieldNotFound;
import quickfix.field.Account;
import quickfix.field.MsgType;
import quickfix.field.Symbol;

/**
 * The request message for account info.
 */
public class AccountInfoRequest extends quickfix.fix44.Message {

	private static final long serialVersionUID = 20141101L;

	public static final String MSGTYPE = "Z1000";

	public AccountInfoRequest() {
		getHeader().setField(new MsgType(MSGTYPE));
	}

	public void set(Account value) {
		setField(value);
	}

	public Account get(Account value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public Account getAccount() throws FieldNotFound {
		Account value = new Account();
		getField(value);
		return value;
	}

	public boolean isSet(Account field) {
		return isSetField(field);
	}

	public boolean isSetAccount() {
		return isSetField(Account.FIELD);
	}

	public void set(AccReqID value) {
		setField(value);
	}

	public AccReqID get(AccReqID value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public AccReqID getAccReqID() throws FieldNotFound {
		AccReqID value = new AccReqID();
		getField(value);
		return value;
	}

	public boolean isSet(AccReqID field) {
		return isSetField(field);
	}

	public boolean isSetAccReqID() {
		return isSetField(AccReqID.FIELD);
	}

	public void set(Symbol value) {
		setField(value);
	}

	public Symbol get(Symbol value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public Symbol getSymbol() throws FieldNotFound {
		Symbol value = new Symbol();
		getField(value);
		return value;
	}

	public boolean isSet(Symbol field) {
		return isSetField(field);
	}

	public boolean isSetSymbol() {
		return isSetField(Symbol.FIELD);
	}

}
