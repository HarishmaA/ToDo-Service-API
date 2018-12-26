package com.example.todomongodb.todoserviceapi.controller;

import java.util.List;
import java.util.Map;

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
	
	@PostMapping(value ="/create/user/{userId}", consumes ="application/json" , produces ="application/json")
	public ResponseEntity<ToDo> create(@PathVariable String userId, @RequestBody ToDoDto toDoDto) {
		toDoDto.setUserId(userId);
		ToDo toDoResult = toDoService.create(toDoDto);
		return new ResponseEntity<>(toDoResult,HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/get/user",produces = "application/json")
	public List<ToDo> getToDoByUserId(@RequestParam String userId) {
		return toDoService.getByUserId(userId);
	}
	@GetMapping(value = "/getAll", produces = "application/json")
	public List<ToDo> getAll(){
		return toDoService.getAll();
	}
	
	@GetMapping(value = "/getToDoSplitedByDay/user/{userId}", produces = "application/json")
	public Map<String,List<ToDo>> getToDoListSplitedByDay(@PathVariable String userId){
	 return toDoService.getToDoSplitedByDay(userId);
	}
	@PutMapping("/update/user/{userId}")
	public ResponseEntity<ToDo> update(@PathVariable String userId,@RequestBody ToDoDto toDoDto) {
		toDoDto.setUserId(userId);
		ToDo toDoResult = toDoService.update(toDoDto);
		return new ResponseEntity<>(toDoResult,HttpStatus.OK);
	}
	@DeleteMapping("/delete/user/{userId}")
	public ResponseEntity<String> delete(@PathVariable String userId) {
		toDoService.delete(userId);
		return new ResponseEntity<>(String.format("Deleted %s", userId),HttpStatus.OK);
	}
	@DeleteMapping ("/deleteAll")
	public ResponseEntity<String> deleteAll() {
		toDoService.deleteAll();
		return new ResponseEntity<>("Deleted all records",HttpStatus.OK);
	}
	
}
