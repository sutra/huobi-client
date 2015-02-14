package org.oxerr.huobi.rest.domain;

public class LoginResult extends AbstractObject {

	private static final long serialVersionUID = 2014010201L;

	private String level;

	private Funds funds;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Funds getFunds() {
		return funds;
	}

	public void setFunds(Funds funds) {
		this.funds = funds;
	}
}
