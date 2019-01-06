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
	

	@PostMapping(value ="/create/user/{userId}")
	public ResponseEntity<ToDo> create(@PathVariable String userId, @RequestBody ToDoDto toDoDto) {
		toDoDto.setUserId(userId);
		ToDo toDoResult = toDoService.create(toDoDto);
		return new ResponseEntity<>(toDoResult,HttpStatus.CREATED);
	}
	
	@GetMapping(value = "todo/user/{userId}")
	public List<ToDo> getToDoByUserId(@PathVariable String userId) {
		return toDoService.getByUserId(userId);
	}
	@GetMapping(value = "/all-todos")
	public List<ToDo> getAll(){
		return toDoService.getAll();
	}
	
	@GetMapping(value = "/sorted-todos/user/{userId}")
	public List<ToDo> getSortedToDos(@PathVariable String userId){
		 return toDoService.getSortedToDos(userId);
		}
	
	@PutMapping("/update")
	public ResponseEntity<ToDo> update(@RequestBody ToDo toDo) {
		ToDo toDoResult = toDoService.update(toDo);
		return new ResponseEntity<>(toDoResult,HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ToDo> updateToDoText(@PathVariable String id, @RequestParam String toDoText) {
		ToDo toDoResult = toDoService.updateToDoText(id,toDoText);
		return new ResponseEntity<>(toDoResult,HttpStatus.OK);
	}

	@PutMapping("/finished/{id}")
	public ResponseEntity<ToDo> finishedTodo(@PathVariable String id)
	{
		ToDo toDoResult = toDoService.markToDoAsFinished(id);
		return new ResponseEntity<>(toDoResult,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/user/{userId}")
	public ResponseEntity<String> delete(@PathVariable String userId,@RequestParam String id) {
		toDoService.delete(id);
		return new ResponseEntity<>(String.format("Deleted todo %s of user %s", id,userId),HttpStatus.OK);
	}
	@DeleteMapping ("/delete-all")
	public ResponseEntity<String> deleteAll() {
		toDoService.deleteAll();
		return new ResponseEntity<>("Deleted all records",HttpStatus.OK);
	}
	
}
