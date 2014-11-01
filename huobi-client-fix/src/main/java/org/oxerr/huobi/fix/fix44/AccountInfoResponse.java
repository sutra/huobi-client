package org.oxerr.huobi.fix.fix44;

import org.oxerr.huobi.fix.field.AccReqID;
import org.oxerr.huobi.fix.field.AvailableBtc;
import org.oxerr.huobi.fix.field.AvailableCny;
import org.oxerr.huobi.fix.field.AvailableLtc;
import org.oxerr.huobi.fix.field.FrozenBtc;
import org.oxerr.huobi.fix.field.FrozenCny;
import org.oxerr.huobi.fix.field.FrozenLtc;

import quickfix.FieldNotFound;
import quickfix.field.Account;
import quickfix.field.MsgType;

/**
 * The response message of account info
 */
public class AccountInfoResponse extends quickfix.fix44.Message {

	private static final long serialVersionUID = 20141101L;

	public static final String MSGTYPE = "Z1001";

	public AccountInfoResponse() {
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
		return isSetField(1);
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
		return isSetField(1622);
	}

	public void set(AvailableCny value) {
		setField(value);
	}

	public AvailableCny get(AvailableCny value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public AvailableCny getAvailableCny() throws FieldNotFound {
		AvailableCny value = new AvailableCny();
		getField(value);
		return value;
	}

	public boolean isSet(AvailableCny field) {
		return isSetField(field);
	}

	public boolean isSetAvailableCny() {
		return isSetField(1623);
	}

	public void set(AvailableBtc value) {
		setField(value);
	}

	public AvailableBtc get(AvailableBtc value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public AvailableBtc getAvailableBtc() throws FieldNotFound {
		AvailableBtc value = new AvailableBtc();
		getField(value);
		return value;
	}

	public boolean isSet(AvailableBtc field) {
		return isSetField(field);
	}

	public boolean isSetAvailableBtc() {
		return isSetField(1624);
	}

	public void set(AvailableLtc value) {
		setField(value);
	}

	public AvailableLtc get(AvailableLtc value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public AvailableLtc getAvailableLtc() throws FieldNotFound {
		AvailableLtc value = new AvailableLtc();
		getField(value);
		return value;
	}

	public boolean isSet(AvailableLtc field) {
		return isSetField(field);
	}

	public boolean isSetAvailableLtc() {
		return isSetField(1625);
	}

	public void set(FrozenLtc value) {
		setField(value);
	}

	public FrozenLtc get(FrozenLtc value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public FrozenLtc getFrozenLtc() throws FieldNotFound {
		FrozenLtc value = new FrozenLtc();
		getField(value);
		return value;
	}

	public boolean isSet(FrozenLtc field) {
		return isSetField(field);
	}

	public boolean isSetFrozenLtc() {
		return isSetField(1626);
	}

	public void set(FrozenBtc value) {
		setField(value);
	}

	public FrozenBtc get(FrozenBtc value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public FrozenBtc getFrozenBtc() throws FieldNotFound {
		FrozenBtc value = new FrozenBtc();
		getField(value);
		return value;
	}

	public boolean isSet(FrozenBtc field) {
		return isSetField(field);
	}

	public boolean isSetFrozenBtc() {
		return isSetField(1627);
	}

	public void set(FrozenCny value) {
		setField(value);
	}

	public FrozenCny get(FrozenCny value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public FrozenCny getFrozenCny() throws FieldNotFound {
		FrozenCny value = new FrozenCny();
		getField(value);
		return value;
	}

	public boolean isSet(FrozenCny field) {
		return isSetField(field);
	}

	public boolean isSetFrozenCny() {
		return isSetField(1628);
	}

}
