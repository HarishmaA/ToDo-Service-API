package com.example.todomongodb.todoserviceapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.todomongodb.todoserviceapi.domain.ToDo;
import com.example.todomongodb.todoserviceapi.model.ToDoDto;
import com.example.todomongodb.todoserviceapi.service.ToDoService;

@RestController
@RequestMapping(value = "/todo/api")
public class ToDoController {

	@Autowired
	private ToDoService toDoService;

	@PostMapping(value = "/create/user/{userId}")
	public ResponseEntity<ToDo> create(@PathVariable String userId, @RequestBody ToDoDto toDoDto) {
		toDoDto.setUserId(userId);
		ToDo toDo = toDoService.create(toDoDto);
		return new ResponseEntity<>(toDo, HttpStatus.CREATED);
	}

	@GetMapping(value = "/all-todos")
	public List<ToDo> getAll() {
		return toDoService.getAll();
	}

	@GetMapping(value = "/todos/user/{userId}")
	public List<ToDo> getToDos(@PathVariable String userId) {
		return toDoService.getToDos(userId);
	}

	@GetMapping(value = "/todos/priority/user/{userId}")
	public List<ToDo> getPriorityToDos(@PathVariable String userId) {
		return toDoService.getPriorityToDos(userId);
	}

	@PutMapping("/update/todo/{id}")
	public ResponseEntity<ToDo> updateToDoText(@PathVariable String id, @RequestParam String toDoText) {
		ToDo toDo = toDoService.updateToDoText(id, toDoText);
		return new ResponseEntity<>(toDo, HttpStatus.OK);
	}

	@PutMapping("update/finish/todo/{id}")
	public ResponseEntity<ToDo> finishedTodo(@PathVariable String id) {
		ToDo toDo = toDoService.markToDoAsFinished(id);
		return new ResponseEntity<>(toDo, HttpStatus.OK);
	}

	@PutMapping("/update/priority/todo/{id}")
	public ResponseEntity<ToDo> priorityTodo(@PathVariable String id) {
		ToDo toDo = toDoService.markToDoAsPriority(id);
		return new ResponseEntity<>(toDo, HttpStatus.OK);
	}

	@DeleteMapping("/delete/user/{userId}")
	public ResponseEntity<String> delete(@PathVariable String userId, @RequestParam String id) {
		toDoService.delete(id);
		return new ResponseEntity<>(String.format("Deleted todo %s of user %s", id, userId), HttpStatus.OK);
	}

	@DeleteMapping("/delete-all")
	public ResponseEntity<String> deleteAll() {
		toDoService.deleteAll();
		return new ResponseEntity<>("Deleted all records", HttpStatus.OK);
	}

}
