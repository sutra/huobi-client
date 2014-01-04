package com.redv.huobi.domain;

public enum Type {

	BUY("do_buy", "买入"), SELL("do_sell", "卖出");

	public static Type toType(String typeString) {
		for (Type type : Type.values()) {
			if (type.type.equals(typeString)) {
				return type;
			}
		}

		throw new IllegalArgumentException("Unexpected type: " + typeString);
	}

	public static Type delegationToType(String delegationTypeString) {
		for (Type type : Type.values()) {
			if (type.delegationType.equals(delegationTypeString)) {
				return type;
			}
		}

		throw new IllegalArgumentException("Unexpected delegation type: "
				+ delegationTypeString);
	}

	private String type;

	private String delegationType;

	Type(String type, String delegationType) {
		this.type = type;
		this.delegationType = delegationType;
	}

	/**
	 * @return the delegationType
	 */
	public String getDelegationType() {
		return delegationType;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return type;
	}

}
