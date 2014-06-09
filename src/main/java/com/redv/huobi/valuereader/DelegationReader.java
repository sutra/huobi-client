package com.redv.huobi.valuereader;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLCollection;
import org.w3c.dom.html.HTMLDivElement;
import org.w3c.dom.html.HTMLDocument;
import org.w3c.dom.html.HTMLTableCellElement;
import org.w3c.dom.html.HTMLTableElement;
import org.w3c.dom.html.HTMLTableRowElement;
import org.w3c.dom.html.HTMLTableSectionElement;

import com.redv.huobi.HUOBIClientException;
import com.redv.huobi.domain.Delegation;
import com.redv.huobi.domain.Type;

public class DelegationReader extends HTMLReader<List<Delegation>> {

	private final Logger log = LoggerFactory.getLogger(DelegationReader.class);

	private DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Delegation> parse(HTMLDocument document)
			throws HUOBIClientException {
		List<Delegation> delegations = new ArrayList<>();

		HTMLDivElement myDelegationLegend = findMyDelegationLegend(document);

		Node next = myDelegationLegend.getNextSibling();
		if (next != null) {
			next = next.getNextSibling();
		}

		if (next != null) {
			parse(next, delegations);
		} else {
			// 无委托记录
			log.debug("No delegations.");
		}

		return delegations;
	}

	private List<Delegation> parse(Node next, List<Delegation> delegations)
			throws HUOBIClientException {
		List<HTMLTableElement> tables = new ArrayList<>();
		findTables(next, tables);

		int count = tables.size();
		log.debug("tables.size: {}", count);

		if (count > 0) {
			HTMLTableElement table = tables.get(0);
			try {
				parse(table, delegations);
			} catch (DOMException | ParseException e) {
				throw new HUOBIClientException(e);
			}
		}

		return delegations;
	}

	/**
	 * Find: <xmp><div class="legend">我的委托</div></xmp>
	 */
	private HTMLDivElement findMyDelegationLegend(HTMLDocument document) {
		NodeList divNodeList = document.getElementsByTagName("div");
		HTMLDivElement myDelegationLegend = null;

		for (int i = 0; i < divNodeList.getLength(); i++) {
			HTMLDivElement div = (HTMLDivElement) divNodeList.item(i);
			String className = div.getClassName();
			String textContent = StringUtils.trim(div.getTextContent());
			if (StringUtils.equals(className, "legend")
					&& StringUtils.equals(textContent, "我的委托")) {
				myDelegationLegend = div;
				break;
			}
		}

		return myDelegationLegend;
	}

	private void findTables(Node node, List<HTMLTableElement> tables) {
		log.debug("node type: {}", node.getClass());

		if (node instanceof HTMLTableElement) {
			tables.add((HTMLTableElement) node);
		} else if (node.hasChildNodes()) {
			NodeList children = node.getChildNodes();
			for (int i = 0; i < children.getLength(); i++) {
				findTables(children.item(i), tables);
			}
		}
	}

	private void parse(HTMLTableElement table, List<Delegation> delegations)
			throws DOMException, ParseException {
	HTMLCollection tbodies = table.getTBodies();
		for (int i = 0; i < tbodies.getLength(); i++) {
			Node node = tbodies.item(i);
			log.debug("{}", node.getClass());
			HTMLTableSectionElement tableSectionElement = (HTMLTableSectionElement) node;
			HTMLCollection rows = tableSectionElement.getRows();
			for (int j = 0; j < rows.getLength(); j++) {
				Delegation delegation = new Delegation();

				HTMLTableRowElement row = (HTMLTableRowElement) rows.item(j);
				HTMLCollection cells = row.getCells();

				HTMLTableCellElement operationCell = (HTMLTableCellElement) cells.item(0);
				delegation.setId(parseId(operationCell));

				delegation.setDate(dateFormat.parse(cells.item(1).getTextContent()));
				delegation.setType(Type.delegationToType(cells.item(2).getTextContent()));
				delegation.setPrice(new BigDecimal(cells.item(3).getTextContent()));
				delegation.setAmount(new BigDecimal(cells.item(4).getTextContent()));
				delegation.setTrading(new BigDecimal(cells.item(5).getTextContent()));
				// delegation.setFee(new BigDecimal(cells.item(DATE_CELL_INDEX + 5).getTextContent()));
				// delegation.setTotal(new BigDecimal(cells.item(DATE_CELL_INDEX + 6).getTextContent()));

				delegations.add(delegation);
			}
		}
	}

	private long parseId(HTMLTableCellElement cell) {
		String id = cell.getFirstChild().getAttributes().getNamedItem("value").getTextContent();
		return Integer.parseInt(id);
	}
}
