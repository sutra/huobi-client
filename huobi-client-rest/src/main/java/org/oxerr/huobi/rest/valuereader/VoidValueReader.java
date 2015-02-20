package org.oxerr.huobi.rest.valuereader;

import static org.oxerr.huobi.rest.HuobiClient.ENCODING;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VoidValueReader implements ValueReader<Void>{

	private static final VoidValueReader INSTANCE = new VoidValueReader();

	private final Logger log = LoggerFactory.getLogger(VoidValueReader.class);

	public static final VoidValueReader getInstance() {
		return INSTANCE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Void read(InputStream inputStream) throws IOException {
		final String content = IOUtils.toString(inputStream, ENCODING);
		log.debug("Parsing: {}", content);

		return null;
	}

}
