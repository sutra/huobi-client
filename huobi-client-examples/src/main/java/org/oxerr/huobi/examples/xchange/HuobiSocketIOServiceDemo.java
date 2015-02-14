package org.oxerr.huobi.examples.xchange;

import java.util.concurrent.TimeUnit;

import org.oxerr.huobi.xchange.HuobiExchange;
import org.oxerr.huobi.xchange.service.streaming.HuobiSocketIOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.ExchangeFactory;
import com.xeiam.xchange.service.streaming.ExchangeEvent;
import com.xeiam.xchange.service.streaming.StreamingExchangeService;

/**
 * Demonstration for {@link HuobiSocketIOService}.
 */
public class HuobiSocketIOServiceDemo {

	private static final Logger log = LoggerFactory
			.getLogger(HuobiSocketIOServiceDemo.class);

	public static void main(String[] args) throws InterruptedException {
		// Bridge/route all JUL log records to the SLF4J API.
		SLF4JBridgeHandler.removeHandlersForRootLogger();
		SLF4JBridgeHandler.install();

		final Exchange exchange = ExchangeFactory.INSTANCE
				.createExchange(HuobiExchange.class.getName());
		final StreamingExchangeService streamingExchangeService = exchange
				.getStreamingExchangeService(null);

		final Thread consumer = new Thread("consumer") {

			@Override
			public void run() {

				while (!isInterrupted()) {
					try {
						ExchangeEvent event = streamingExchangeService
								.getNextEvent();
						log.info("status: {}, type: {}, data: {}, payload: {}",
								streamingExchangeService.getWebSocketStatus(),
								event.getEventType(), event.getData(),
								event.getPayload());
					} catch (InterruptedException e) {
						this.interrupt();
					}
				}
			}

		};

		// Start consumer.
		consumer.start();
		log.info("Consumer started.");

		// Start streaming service.
		streamingExchangeService.connect();

		// Demonstrate for 1 minute.
		TimeUnit.MINUTES.sleep(1);

		// Disconnect streaming service.
		streamingExchangeService.disconnect();

		// Interrupt consumer to exit.
		consumer.interrupt();
		log.info("Consumer interrupted.");
	}

}
