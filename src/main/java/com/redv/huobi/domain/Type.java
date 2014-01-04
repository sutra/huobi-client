package com.redv.huobi.domain;

public enum Type {

	BUY("do_buy"), SELL("do_sell");

	public static Type toType(String typeString) {
		for (Type type : Type.values()) {
			if (type.type.equals(typeString)) {
				return type;
			}
		}

		throw new IllegalArgumentException("Unexpected type: " + typeString);
	}

	private String type;

	Type(String type) {
		this.type = type;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return type;
	}

}
