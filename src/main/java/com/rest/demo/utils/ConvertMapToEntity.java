package com.rest.demo.utils;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;

public class ConvertMapToEntity {

	@Autowired
	private static ObjectMapper objectMapper;
	
	@SuppressWarnings("unchecked")
	public static <T> T convert(Map<String, Object> updates, T entity) {
		Map<String, Object> map = objectMapper.convertValue(entity, HashMap.class);
		map.putAll(updates);
		entity = (T) objectMapper.convertValue(map, entity.getClass());
		return entity;
	}
	
}
