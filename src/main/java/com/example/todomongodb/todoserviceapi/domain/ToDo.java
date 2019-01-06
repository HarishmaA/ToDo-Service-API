package com.example.todomongodb.todoserviceapi.domain;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Document
public class ToDo {
	@Id
	String _id;
	String toDoText;
	Boolean isFinished;
	Boolean isPriority;
	String userId;
	String createdAt;
	String updatedAt;

	public ToDo(String toDoText, Boolean isFinished, Boolean isPriority, String userId, String createdAt,
			String updatedAt) {
		super();
		this.toDoText = toDoText;
		this.isFinished = isFinished;
		this.isPriority = isPriority;
		this.userId = userId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public static String getCurrentTime() {
		return OffsetDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
	}

	public ToDo update(ToDo toDo) {
		this.toDoText = toDo.getToDoText();
		this.userId = toDo.getUserId();
		this.isFinished = toDo.getIsFinished();
		this.isPriority = toDo.getIsPriority();
		this.createdAt = toDo.getCreatedAt();
		this.updatedAt = getCurrentTime();
		return this;
	}

}