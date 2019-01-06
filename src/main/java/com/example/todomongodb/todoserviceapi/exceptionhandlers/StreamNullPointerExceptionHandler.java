package com.example.todomongodb.todoserviceapi.exceptionhandlers;

import java.util.function.Predicate;

import com.example.todomongodb.todoserviceapi.domain.ToDo;

public class StreamNullPointerExceptionHandler {
	public static Predicate<ToDo> exceptionWrapper(Predicate<ToDo> predicate) {
		return toDo -> {
			try {
				return predicate.test(toDo);
			} catch (NullPointerException e) {
				throw new NullPointerException("Cannot retrieve Priority List");
			}
		};
	}
}
