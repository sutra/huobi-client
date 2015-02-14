package org.oxerr.huobi.rest.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

abstract class AbstractObject implements Serializable {

	private static final long serialVersionUID = 2014010201L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
