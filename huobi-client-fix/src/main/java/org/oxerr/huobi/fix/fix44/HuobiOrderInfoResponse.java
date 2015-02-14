package org.oxerr.huobi.fix.fix44;

import org.oxerr.huobi.fix.field.Fee;
import org.oxerr.huobi.fix.field.ProcessedAmount;
import org.oxerr.huobi.fix.field.ProcessedPrice;
import org.oxerr.huobi.fix.field.Total;
import org.oxerr.huobi.fix.field.Vot;

import quickfix.FieldNotFound;
import quickfix.field.MsgType;
import quickfix.field.OrdStatus;
import quickfix.field.OrderID;
import quickfix.field.Price;
import quickfix.field.Quantity;
import quickfix.field.Side;
import quickfix.field.Symbol;

public class HuobiOrderInfoResponse extends quickfix.fix44.Message {

	private static final long serialVersionUID = 20141102L;

	public static final String MSGTYPE = "Z1003";

	public HuobiOrderInfoResponse() {
		getHeader().setField(new MsgType(MSGTYPE));
	}

	public void set(OrderID value) {
		setField(value);
	}

	public OrderID get(OrderID value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public OrderID getOrderID() throws FieldNotFound {
		OrderID value = new OrderID();
		getField(value);
		return value;
	}

	public boolean isSet(OrderID field) {
		return isSetField(field);
	}

	public boolean isSetOrderID() {
		return isSetField(OrderID.FIELD);
	}

	public void set(Side value) {
		setField(value);
	}

	public Side get(Side value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public Side getSide() throws FieldNotFound {
		Side value = new Side();
		getField(value);
		return value;
	}

	public boolean isSet(Side field) {
		return isSetField(field);
	}

	public boolean isSetSide() {
		return isSetField(Side.FIELD);
	}

	public void set(Price value) {
		setField(value);
	}

	public Price get(Price value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public Price getPrice() throws FieldNotFound {
		Price value = new Price();
		getField(value);
		return value;
	}

	public boolean isSet(Price field) {
		return isSetField(field);
	}

	public boolean isSetPrice() {
		return isSetField(Price.FIELD);
	}

	public void set(OrdStatus value) {
		setField(value);
	}

	public OrdStatus get(OrdStatus value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public OrdStatus getOrdStatus() throws FieldNotFound {
		OrdStatus value = new OrdStatus();
		getField(value);
		return value;
	}

	public boolean isSet(OrdStatus field) {
		return isSetField(field);
	}

	public boolean isSetOrdStatus() {
		return isSetField(OrdStatus.FIELD);
	}

	public void set(Quantity value) {
		setField(value);
	}

	public Quantity get(Quantity value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public Quantity getQuantity() throws FieldNotFound {
		Quantity value = new Quantity();
		getField(value);
		return value;
	}

	public boolean isSet(Quantity field) {
		return isSetField(field);
	}

	public boolean isSetQuantity() {
		return isSetField(Quantity.FIELD);
	}

	public void set(ProcessedPrice value) {
		setField(value);
	}

	public ProcessedPrice get(ProcessedPrice value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public ProcessedPrice getProcessedPrice() throws FieldNotFound {
		ProcessedPrice value = new ProcessedPrice();
		getField(value);
		return value;
	}

	public boolean isSet(ProcessedPrice field) {
		return isSetField(field);
	}

	public boolean isSetProcessedPrice() {
		return isSetField(ProcessedPrice.FIELD);
	}

	public void set(ProcessedAmount value) {
		setField(value);
	}

	public ProcessedAmount get(ProcessedAmount value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public ProcessedAmount getProcessedAmount() throws FieldNotFound {
		ProcessedAmount value = new ProcessedAmount();
		getField(value);
		return value;
	}

	public boolean isSet(ProcessedAmount field) {
		return isSetField(field);
	}

	public boolean isSetProcessedAmount() {
		return isSetField(ProcessedAmount.FIELD);
	}

	public void set(Vot value) {
		setField(value);
	}

	public Vot get(Vot value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public Vot getVot() throws FieldNotFound {
		Vot value = new Vot();
		getField(value);
		return value;
	}

	public boolean isSet(Vot field) {
		return isSetField(field);
	}

	public boolean isSetVot() {
		return isSetField(Vot.FIELD);
	}

	public void set(Fee value) {
		setField(value);
	}

	public Fee get(Fee value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public Fee getFee() throws FieldNotFound {
		Fee value = new Fee();
		getField(value);
		return value;
	}

	public boolean isSet(Fee field) {
		return isSetField(field);
	}

	public boolean isSetFee() {
		return isSetField(Fee.FIELD);
	}

	public void set(Total value) {
		setField(value);
	}

	public Total get(Total value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public Total getTotal() throws FieldNotFound {
		Total value = new Total();
		getField(value);
		return value;
	}

	public boolean isSet(Total field) {
		return isSetField(field);
	}

	public boolean isSetTotal() {
		return isSetField(Total.FIELD);
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
