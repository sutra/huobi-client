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

		boolean total = false,
				netAsset = false,
				availableCny = false,
				availableBtc = false,
				frozenCny = false,
				frozenBtc = false,
				loanCny = false,
				loanBtc = false;

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
				if (!total) {
					funds.setTotal(new BigDecimal(textContent));
					total = true;
				}
				break;
			case "user_net_asset":
				if (!netAsset) {
					funds.setNetAsset(new BigDecimal(textContent));
					netAsset = true;
				}
				break;
			case "user_available_cny":
				if (!availableCny) {
					funds.setAvailableCny(new BigDecimal(textContent));
					availableCny = true;
				}
				break;
			case "user_available_btc":
				if (!availableBtc) {
					funds.setAvailableBtc(new BigDecimal(textContent));
					availableBtc = true;
				}
				break;
			case "user_frozen_cny":
				if (!frozenCny) {
					funds.setFrozenCny(new BigDecimal(textContent));
					frozenCny = true;
				}
				break;
			case "user_frozen_btc":
				if (!frozenBtc) {
					funds.setFrozenBtc(new BigDecimal(textContent));
					frozenBtc = true;
				}
				break;
			case "user_loan_cny":
				if (!loanCny) {
					funds.setLoanCny(new BigDecimal(textContent));
					loanCny = true;
				}
				break;
			case "user_loan_btc":
				if (!loanBtc) {
					funds.setLoanBtc(new BigDecimal(textContent));
					loanBtc = true;
				}
				break;
			default:
				break;
			}
		}

		return loginResult;
	}

}
