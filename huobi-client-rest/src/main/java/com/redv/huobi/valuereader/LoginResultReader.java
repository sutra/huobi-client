package com.redv.huobi.valuereader;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLDocument;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLFormElement;

import com.redv.huobi.LoginRequiredException;
import com.redv.huobi.domain.Funds;
import com.redv.huobi.domain.LoginResult;

public class LoginResultReader extends HTMLReader<LoginResult> {

	private final Logger log = LoggerFactory.getLogger(LoginResult.class);

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
				frozenCny = false,
				availableBtc = false,
				frozenBtc = false,
				availableLtc = false,
				frozenLtc = false,
				loanCny = false,
				loanBtc = false,
				loanLtc = false;

		NodeList spanNodeList = document.getElementsByTagName("span");
		for (int i = 0; i < spanNodeList.getLength(); i++) {
			HTMLElement element = (HTMLElement) spanNodeList.item(i);
			String className = element.getClassName();
			String textContent = element.getTextContent();
			log.debug("className: {}, textContent: {}.", className, textContent);
			switch (className) {
			case "user-info-level pull-left":
				loginResult.setLevel(textContent.trim());
				break;
			}
		}

		NodeList bNodeList = document.getElementsByTagName("b");
		for (int i = 0; i < bNodeList.getLength(); i++) {
			HTMLElement element = (HTMLElement) bNodeList.item(i);
			String className = element.getClassName();
			String textContent = element.getTextContent();
			log.debug("className: {}, textContent: {}.", className, textContent);
			switch (className) {
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
			case "user_frozen_cny":
				if (!frozenCny) {
					funds.setFrozenCny(new BigDecimal(textContent));
					frozenCny = true;
				}
				break;
			case "user_available_btc":
				if (!availableBtc) {
					funds.setAvailableBtc(new BigDecimal(textContent));
					availableBtc = true;
				}
				break;
			case "user_frozen_btc":
				if (!frozenBtc) {
					funds.setFrozenBtc(new BigDecimal(textContent));
					frozenBtc = true;
				}
				break;
			case "user_available_ltc":
				if (!availableLtc) {
					funds.setAvailableLtc(new BigDecimal(textContent));
					availableLtc = true;
				}
				break;
			case "user_frozen_ltc":
				if (!frozenLtc) {
					funds.setFrozenLtc(new BigDecimal(textContent));
					frozenLtc = true;
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
			case "user_loan_ltc":
				if (!loanLtc) {
					funds.setLoanLtc(new BigDecimal(textContent));
					loanLtc = true;
				}
				break;
			default:
				break;
			}
		}

		return loginResult;
	}

}
