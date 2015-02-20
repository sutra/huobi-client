package org.oxerr.huobi.rest.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Delegation extends AbstractObject {

	private static final long serialVersionUID = 2014010401L;

	/**
	 * Delegation ID.
	 */
	private long id;

	/**
	 * 委托时间
	 */
	private Date date;

	/**
	 * 类别
	 */
	private Type type;

	/**
	 * 价格
	 */
	private BigDecimal price;

	/**
	 * 数量
	 */
	private BigDecimal amount;

	/**
	 * 交易额
	 */
	private BigDecimal trading;

	/**
	 * 手续费
	 */
	private BigDecimal fee;

	/**
	 * 总金额
	 */
	private BigDecimal total;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getTrading() {
		return trading;
	}

	public void setTrading(BigDecimal trading) {
		this.trading = trading;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
