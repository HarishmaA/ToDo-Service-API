package com.example.todomongodb.todoserviceapi.service;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todomongodb.todoserviceapi.domain.ToDo;
import com.example.todomongodb.todoserviceapi.model.ToDoDto;
import com.example.todomongodb.todoserviceapi.repository.ToDoRepository;
import com.example.todomongodb.todoserviceapi.utils.DateUtil;

@Service
public class ToDoService {

	@Autowired
	private ToDoRepository toDoRepository;

	public ToDo create(ToDoDto toDoDto) {
		return toDoRepository.save(new ToDo(toDoDto.getToDoText(), toDoDto.getUserId()));
	}

	public List<ToDo> getAll() {
		return toDoRepository.findAll();
	}

	
	public List<ToDo> getToDos(String userId) {
		List<ToDo> toDoList = toDoRepository.findByUserId(userId);
		Collections.sort(toDoList);
		return toDoList;
	}

	public List<ToDo> getPriorityToDos(String userId) {
		List<ToDo> toDoList = toDoRepository.findByUserId(userId);
		return toDoList.stream().filter(toDo -> !toDo.getFinished() && toDo.getPriority()).collect(toList());
	}

	
	public ToDo updateToDoText(String id, String toDoText) {
		ToDo toDo = toDoRepository.findById(id).orElseThrow(() -> new RuntimeException("ToDo Id not found"));
		toDo.setToDoText(toDoText);
		toDo.setUpdatedAt(DateUtil.getCurrentTime());
		return toDoRepository.save(toDo);
	}

	public ToDo markToDoAsFinished(String id) {
		ToDo toDo = toDoRepository.findById(id).orElseThrow(() -> new RuntimeException("ToDo Id not found"));
		toDo.finishToDo();
		return toDoRepository.save(toDo);
	}

	public ToDo markToDoAsPriority(String id) {
		ToDo toDo = toDoRepository.findById(id).orElseThrow(() -> new RuntimeException("ToDo Id not found"));
		toDo.prioritizeToDo();
		return toDoRepository.save(toDo);
	}

	
	public void deleteAll() {
		toDoRepository.deleteAll();
	}

	public void delete(String id) {
		ToDo toDo = toDoRepository.findById(id).orElseThrow(() -> new RuntimeException("ToDo Id not found"));
		toDoRepository.delete(toDo);
	}

}
