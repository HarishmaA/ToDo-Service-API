package com.example.todomongodb.todoserviceapi.domain;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.todomongodb.todoserviceapi.utils.DateUtil;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Document
public class ToDo implements Comparable<ToDo> {
	@Id
	String _id;
	String toDoText;
	Boolean finished;
	Boolean priority;
	String userId;
	String createdAt;
	String updatedAt;

	public ToDo(String toDoText, String userId) {
		this.toDoText = toDoText;
		this.finished = false;
		this.priority = false;
		this.userId = userId;
		this.createdAt = DateUtil.getCurrentTime();
		this.updatedAt = DateUtil.getCurrentTime();
	}

	public void finishTask() {
		if (!this.finished) {
			this.finished = true;
		}
	}

	public void prioritizeTask() {
		System.out.println(this.priority);
		if (!this.priority) {
			this.priority = true;
		}
	}

	public ToDo update(ToDo toDo) {
		this.toDoText = toDo.getToDoText();
		this.userId = toDo.getUserId();
		this.finished = toDo.getFinished();
		this.priority = toDo.getPriority();
		this.createdAt = toDo.getCreatedAt();
		this.updatedAt = DateUtil.getCurrentTime();
		return this;
	}

	@Override
	public int compareTo(ToDo otherToDo) {
		OffsetDateTime toDoTime1 = OffsetDateTime.parse(this.updatedAt, DateTimeFormatter.RFC_1123_DATE_TIME);
		OffsetDateTime toDoTime2 = OffsetDateTime.parse(otherToDo.getUpdatedAt(), DateTimeFormatter.RFC_1123_DATE_TIME);
		return toDoTime1.compareTo(toDoTime2);
	}

}