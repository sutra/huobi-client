package com.redv.huobi.domain;

import java.math.BigDecimal;

public class Funds extends AbstractObject {

	private static final long serialVersionUID = 2014010201L;

	/**
	 * 总资产折合：元
	 */
	private BigDecimal total;

	/**
	 * 净资产折合：元
	 */
	private BigDecimal netAsset;

	/**
	 * 可用人民币：元
	 */
	private BigDecimal availableCny;

	/**
	 * 冻结人民币：元
	 */
	private BigDecimal frozenCny;

	/**
	 * 可用比特币
	 */
	private BigDecimal availableBtc;

	/**
	 * 冻结比特币
	 */
	private BigDecimal frozenBtc;

	/**
	 * 可用莱特币
	 */
	private BigDecimal availableLtc;

	/**
	 * 冻结莱特币
	 */
	private BigDecimal frozenLtc;


	/**
	 * 已借人民币
	 */
	private BigDecimal loanCny;

	/**
	 * 已借比特币
	 */
	private BigDecimal loanBtc;

	/**
	 * 已借莱特币
	 */
	private BigDecimal loanLtc;

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getNetAsset() {
		return netAsset;
	}

	public void setNetAsset(BigDecimal netAsset) {
		this.netAsset = netAsset;
	}

	public BigDecimal getAvailableCny() {
		return availableCny;
	}

	public void setAvailableCny(BigDecimal availableCny) {
		this.availableCny = availableCny;
	}

	public BigDecimal getFrozenCny() {
		return frozenCny;
	}

	public void setFrozenCny(BigDecimal frozenCny) {
		this.frozenCny = frozenCny;
	}

	public BigDecimal getAvailableBtc() {
		return availableBtc;
	}

	public void setAvailableBtc(BigDecimal availableBtc) {
		this.availableBtc = availableBtc;
	}

	public BigDecimal getFrozenBtc() {
		return frozenBtc;
	}

	public void setFrozenBtc(BigDecimal frozenBtc) {
		this.frozenBtc = frozenBtc;
	}

	public BigDecimal getAvailableLtc() {
		return availableLtc;
	}

	public void setAvailableLtc(BigDecimal availableLtc) {
		this.availableLtc = availableLtc;
	}

	public BigDecimal getFrozenLtc() {
		return frozenLtc;
	}

	public void setFrozenLtc(BigDecimal frozenLtc) {
		this.frozenLtc = frozenLtc;
	}

	public BigDecimal getLoanCny() {
		return loanCny;
	}

	public void setLoanCny(BigDecimal loanCny) {
		this.loanCny = loanCny;
	}

	public BigDecimal getLoanBtc() {
		return loanBtc;
	}

	public void setLoanBtc(BigDecimal loanBtc) {
		this.loanBtc = loanBtc;
	}

	public BigDecimal getLoanLtc() {
		return loanLtc;
	}

	public void setLoanLtc(BigDecimal loanLtc) {
		this.loanLtc = loanLtc;
	}

}
