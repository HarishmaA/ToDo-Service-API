package com.example.todomongodb.todoserviceapi.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import com.example.todomongodb.todoserviceapi.conventers.OffsetDateTimeReadConverter;
import com.example.todomongodb.todoserviceapi.conventers.OffsetDateTimeWriteConverter;
import com.mongodb.MongoClient;


@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

    private final List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
    
    @Override
    protected String getDatabaseName() {
        return "todo_db";
    }
    
    @Override
    public MongoClient mongoClient() {
        return new MongoClient("127.0.0.1", 27017);
    }
    @Override
    public MongoCustomConversions customConversions() {
        converters.add(new OffsetDateTimeReadConverter());
        converters.add(new OffsetDateTimeWriteConverter());
        return new MongoCustomConversions(converters);
    }
}
