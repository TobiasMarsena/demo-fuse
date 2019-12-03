package org.mycompany;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.slf4j.internal.LoggerFactory;

import infinispan.org.jboss.logging.Logger;

public class MyObjectMapper extends ObjectMapper {
	
	Logger logger = LoggerFactory.getLogger(MyObjectMapper.class);

	@Override
	public ObjectMapper configure(Feature f, boolean state) {
		logger.info("MyObjectMapper configured. Enable feature ALLOW_UNQUOTED_FIELD_NAMES");
		return super.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
	}
	
	

}
