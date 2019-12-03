package org.mycompany;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyObjectMapper extends ObjectMapper {

	@Override
	public ObjectMapper configure(Feature f, boolean state) {
		return super.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
	}
	
	

}
