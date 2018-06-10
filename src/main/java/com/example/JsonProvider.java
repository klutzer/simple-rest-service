package com.example;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Provider
public class JsonProvider implements ContextResolver<ObjectMapper> {
	
	public static final String DEFAULT_DATE_PATTERN = "dd/MM/yyyy";
	private final ObjectMapper mapper = new CustomMapper();

	@Override
	public ObjectMapper getContext(Class<?> arg0) {
		return mapper;
	}

	@SuppressWarnings("serial")
	public static final class CustomMapper extends ObjectMapper {

		public CustomMapper() {
			configure(SerializationFeature.USE_EQUALITY_FOR_OBJECT_ID, true);
			configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
			configure(SerializationFeature.INDENT_OUTPUT, true);
			configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			setSerializationInclusion(Include.NON_NULL);
			configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			JavaTimeModule dateModule = new JavaTimeModule();
			//dateModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN)));
			//dateModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN)));
			registerModule(dateModule);
		}
	}

}
