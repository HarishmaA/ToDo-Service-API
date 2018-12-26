package com.example.todomongodb.todoserviceapi.conventers;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class OffsetDateTimeReadConverter implements Converter<String, OffsetDateTime> {
    @Override
    public OffsetDateTime convert(String dateString) {
    	DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        return OffsetDateTime.parse(dateString, formatter);
    }
}