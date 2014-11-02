package org.oxerr.huobi.fix;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quickfix.Application;
import quickfix.DoNotSend;
import quickfix.FieldNotFound;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
import quickfix.Message;
import quickfix.RejectLogon;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.UnsupportedMessageType;
import quickfix.fix44.MessageCracker;

/**
 * Abstract {@link Application} for Huobi.
 */
public abstract class HuobiApplication extends MessageCracker implements
		Application {

	private final Logger log = LoggerFactory.getLogger(HuobiApplication.class);
	private final ExecutorService executorService;

	public HuobiApplication() {
		executorService = Executors.newFixedThreadPool(Runtime.getRuntime()
				.availableProcessors() * 2 + 1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onCreate(SessionID sessionId) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onLogon(SessionID sessionId) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onLogout(SessionID sessionId) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void toAdmin(Message message, SessionID sessionId) {
		log.trace("toAdmin: {}", message);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fromAdmin(Message message, SessionID sessionId)
			throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue,
			RejectLogon {
		log.trace("fromAdmin: {}", message);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void toApp(Message message, SessionID sessionId)
			throws DoNotSend {
		log.trace("toApp: {}", message);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fromApp(Message message, SessionID sessionId)
			throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue,
			UnsupportedMessageType {
		log.trace("fromApp: {}", message);
		crack(message, sessionId);
	}

	public void sendMessage(final Message message, final SessionID sessionId) {
		log.trace("sending message: {}", message);

		executorService.execute(new Runnable() {

			@Override
			public void run() {
				Session.lookupSession(sessionId).send(message);
			}

		});
	}

}
