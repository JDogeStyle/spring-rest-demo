package com.rest.demo.utils;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ConvertMapToEntity {

	@SuppressWarnings("unchecked")
	public static <T> T convert(Map<String, Object> updates, T entity) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule()); // Java 8 Date/Time API
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		Map<String, Object> map = mapper.convertValue(entity, HashMap.class);
		map.putAll(updates);
		entity = (T) mapper.convertValue(map, entity.getClass());
		return entity;
	}
	
}
