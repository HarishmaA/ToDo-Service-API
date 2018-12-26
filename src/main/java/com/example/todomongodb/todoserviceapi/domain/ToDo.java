package com.example.todomongodb.todoserviceapi.domain;

import java.time.OffsetDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.todomongodb.todoserviceapi.model.ToDoDto;

import lombok.Getter;
import lombok.Setter;




@Getter
@Setter
@Document
public class ToDo {
	@Id
	String _id;
	String toDoText;
	String day;
	String userId;
	OffsetDateTime createdAt;
	OffsetDateTime updatedAt;
	
	public ToDo(String toDoText, String day, String userId, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
		super();
		this.toDoText = toDoText;
		this.day = day;
		this.userId = userId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public ToDo update(ToDoDto toDoDto,OffsetDateTime createdAt)
	{
		this.toDoText = toDoDto.getToDoText();
		this.day = toDoDto.getDay();
		this.userId = toDoDto.getUserId();
		this.createdAt = createdAt;
		this.updatedAt = OffsetDateTime.now();
		return this;
	}
	
	
}