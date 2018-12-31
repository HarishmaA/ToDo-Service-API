package com.example.todomongodb.todoserviceapi.domain;

import java.time.OffsetDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.Getter;
import lombok.Setter;




@Getter
@Setter
@Document
public class ToDo {
	@Id
	String _id;
	String toDoText;
	Boolean isFinished;
	String day;
	String userId;
	OffsetDateTime createdAt;
	OffsetDateTime updatedAt;
	
	public ToDo(String toDoText, Boolean isFinished, String day, String userId, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
		super();
		this.toDoText = toDoText;
		this.isFinished = isFinished;
		this.day = day;
		this.userId = userId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public ToDo() {}
	public ToDo update(ToDo toDo)
	{
		this.toDoText = toDo.getToDoText();
		this.day = toDo.getDay();
		this.userId = toDo.getUserId();
		this.isFinished = toDo.getIsFinished();
		this.createdAt = toDo.getCreatedAt();
		this.updatedAt = OffsetDateTime.now();
		return this;
	}
	
	
}