package org.oxerr.huobi.rest.valuereader;

import static org.oxerr.huobi.rest.HuobiClient.ENCODING;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonValueReader<T> implements ValueReader<T> {

	private final Logger log = LoggerFactory.getLogger(JsonValueReader.class);

	private final ObjectMapper objectMapper;

	private final Class<T> valueType;

	public JsonValueReader(ObjectMapper objectMapper, Class<T> valueType) {
		this.objectMapper = objectMapper;
		this.valueType = valueType;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T read(InputStream inputStream) throws IOException {
		final String content = IOUtils.toString(inputStream, ENCODING);
		return read(content);
	}

	protected T read(String content) throws IOException {
		log.debug("Reading {} from \"{}\".", valueType, content);

		try (InputStream in = IOUtils.toInputStream(content, ENCODING)) {
			return objectMapper.readValue(in, valueType);
		} catch (JsonParseException e) {
			String msg = String.format("Parse from \"%1$s\" failed: %2$s",
					content,
					e.getMessage());
			throw new JsonParseException(msg, e.getLocation(), e);
		}
	}

}