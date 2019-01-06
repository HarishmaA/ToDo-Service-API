package com.example.todomongodb.todoserviceapi.service;

import static java.util.stream.Collectors.toList;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todomongodb.todoserviceapi.domain.ToDo;
import com.example.todomongodb.todoserviceapi.exceptionhandlers.StreamNullPointerExceptionHandler;
import com.example.todomongodb.todoserviceapi.model.ToDoDto;
import com.example.todomongodb.todoserviceapi.repository.ToDoRepository;
import com.example.todomongodb.todoserviceapi.utils.TimeUtil;

class SortbyUpdatedTime implements Comparator<ToDo> {

	public int compare(ToDo toDo1, ToDo toDo2) {
		OffsetDateTime toDo1Time = OffsetDateTime.parse(toDo1.getUpdatedAt(), DateTimeFormatter.RFC_1123_DATE_TIME);
		OffsetDateTime toDo2Time = OffsetDateTime.parse(toDo2.getUpdatedAt(), DateTimeFormatter.RFC_1123_DATE_TIME);
		return toDo1Time.compareTo(toDo2Time);
	}
}

@Service
public class ToDoService {

	@Autowired
	private ToDoRepository toDoRepository;

	// Create operation
	public ToDo create(ToDoDto toDoDto) {
		return toDoRepository.save(new ToDo(toDoDto.getToDoText(), toDoDto.getUserId()));
	}

	// Retrieve operations
	public List<ToDo> getAll() {
		return toDoRepository.findAll();
	}

	public List<ToDo> getByUserId(String userId) {
		return toDoRepository.findByUserId(userId);
	}

	public List<ToDo> getSortedToDos(String userId) {
		List<ToDo> toDoList = toDoRepository.findByUserId(userId);
		Collections.sort(toDoList, new SortbyUpdatedTime());
		return toDoList;
	}

	public List<ToDo> getPriorityToDosToBeDone(String userId) {
		List<ToDo> toDoList = toDoRepository.findByUserId(userId);
		return toDoList.stream().filter(StreamNullPointerExceptionHandler.exceptionWrapper(toDo -> !toDo.getFinished() && toDo.getPriority()))
				.collect(toList());
	}

	// Update operations
	public ToDo updateToDoText(String id, String toDoText) {
		ToDo toDo = toDoRepository.findById(id).orElseThrow(() -> new RuntimeException("ToDo Id not found"));
		toDo.setToDoText(toDoText);
		toDo.setUpdatedAt(TimeUtil.getCurrentTime());
		return toDoRepository.save(toDo);
	}

	public ToDo markToDoAsFinished(String id) {
		ToDo toDo = toDoRepository.findById(id).orElseThrow(() -> new RuntimeException("ToDo Id not found"));
		toDo.finishedTask();
		return toDoRepository.save(toDo);
	}

	public ToDo markToDoAsPriority(String id) {
		ToDo toDo = toDoRepository.findById(id).orElseThrow(() -> new RuntimeException("ToDo Id not found"));
		toDo.prioritizedTask();
		return toDoRepository.save(toDo);
	}

	// Delete operations
	public void deleteAll() {
		toDoRepository.deleteAll();
	}

	public void delete(String id) {
		ToDo toDo = toDoRepository.findById(id).orElseThrow(() -> new RuntimeException("ToDo Id not found"));
		toDoRepository.delete(toDo);
	}

}
