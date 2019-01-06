package com.example.todomongodb.todoserviceapi.service;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todomongodb.todoserviceapi.domain.ToDo;
import com.example.todomongodb.todoserviceapi.model.ToDoDto;
import com.example.todomongodb.todoserviceapi.repository.ToDoRepository;

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
		return toDoRepository.save(new ToDo(toDoDto.getToDoText(), false, toDoDto.getUserId(), ToDo.getCurrentTime(),
				ToDo.getCurrentTime()));
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

	// Update operations
	public ToDo update(ToDo toDo) {
		ToDo toDoById = toDoRepository.findById(toDo.get_id())
				.orElseThrow(() -> new RuntimeException("ToDo Id not found"));
		ToDo toDoUpdated = toDoById.update(toDo);
		return toDoRepository.save(toDoUpdated);
	}

	public ToDo updateToDoText(String id, String toDoText) {
		ToDo toDoById = toDoRepository.findById(id).orElseThrow(() -> new RuntimeException("ToDo Id not found"));
		toDoById.setToDoText(toDoText);
		toDoById.setUpdatedAt(ToDo.getCurrentTime());
		return toDoRepository.save(toDoById);
	}

	public ToDo markToDoAsFinished(String id) {
		ToDo toDoById = toDoRepository.findById(id).orElseThrow(() -> new RuntimeException("ToDo Id not found"));
		if (!toDoById.getIsFinished()) {
			toDoById.setIsFinished(true);
		}
		return toDoRepository.save(toDoById);
	}

	// Delete operations
	public void deleteAll() {
		toDoRepository.deleteAll();
	}

	public void delete(String id) {
		ToDo toDoById = toDoRepository.findById(id).orElseThrow(() -> new RuntimeException("ToDo Id not found"));
		toDoRepository.delete(toDoById);
	}

}
