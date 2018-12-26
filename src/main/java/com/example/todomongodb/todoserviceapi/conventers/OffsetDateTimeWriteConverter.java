package com.example.todomongodb.todoserviceapi.conventers;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class OffsetDateTimeWriteConverter implements Converter<OffsetDateTime, String> {
	@Override
	public String convert(OffsetDateTime offsetDateTime) {
		return offsetDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX"));
	}
}
