package com.redv.huobi.valuereader;

import java.math.BigDecimal;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLDocument;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLFormElement;

import com.redv.huobi.LoginRequiredException;
import com.redv.huobi.domain.Funds;
import com.redv.huobi.domain.LoginResult;

public class LoginResultReader extends HTMLReader<LoginResult> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected LoginResult parse(HTMLDocument document) throws LoginRequiredException {
		Element loginForm = document.getElementById("login");
		if (loginForm instanceof HTMLFormElement) {
			throw new LoginRequiredException();
		}

		final LoginResult loginResult = new LoginResult();
		final Funds funds = new Funds();
		loginResult.setFunds(funds);

		NodeList spanNodeList = document.getElementsByTagName("span");
		for (int i = 0; i < spanNodeList.getLength(); i++) {
			HTMLElement element = (HTMLElement) spanNodeList.item(i);
			String className = element.getClassName();
			String textContent = element.getTextContent();
			switch (className) {
			case "user-info-level pull-left":
				loginResult.setLevel(textContent.trim());
				break;
			case "user_total":
				funds.setTotal(new BigDecimal(textContent));
				break;
			case "user_net_asset":
				funds.setNetAsset(new BigDecimal(textContent));
				break;
			case "user_available_cny":
				funds.setAvailableCny(new BigDecimal(textContent));
				break;
			case "user_available_btc":
				funds.setAvailableBtc(new BigDecimal(textContent));
				break;
			case "user_frozen_cny":
				funds.setFrozenCny(new BigDecimal(textContent));
				break;
			case "user_frozen_btc":
				funds.setFrozenBtc(new BigDecimal(textContent));
				break;
			case "user_loan_cny":
				funds.setLoanCny(new BigDecimal(textContent));
				break;
			case "user_loan_btc":
				funds.setLoanBtc(new BigDecimal(textContent));
				break;
			default:
				break;
			}
		}

		return loginResult;
	}

}
