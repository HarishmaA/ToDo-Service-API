package com.example.todomongodb.todoserviceapi;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.Document;



//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
@SpringBootApplication
public class TodoserviceapiApplication {
	
    

	public static void main(String[] args) {
		SpringApplication.run(TodoserviceapiApplication.class, args);
	}
	
 //       @Bean
//	    CommandLineRunner init(DomainRepository domainRepository) {
//
//	        return args -> {
//
//	            Domain obj1 = domainRepository.findFirstByDomain("mkyong.com");
//	            System.out.println("Hi, find by domain" +obj1);
//	           // Document canvas = new Document("item", "canvas")
//	                 //   .append("qty", 100));
//	             
//
//	        };
//
//	    }

}

