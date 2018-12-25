package com.example.todomongodb.todoserviceapi.model;


import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Document
public class ToDo {
	@Id
	String _id;
	String toDoText;
	String day;
	String userId;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;
	
}
