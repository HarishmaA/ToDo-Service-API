package com.example.todomongodb.todoserviceapi.conventers;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class OffsetDateTimeReadConverter implements Converter<String, OffsetDateTime> {
    @Override
    public OffsetDateTime convert(String dateString) {
        return OffsetDateTime.parse(dateString, DateTimeFormatter.RFC_1123_DATE_TIME);
    }
}