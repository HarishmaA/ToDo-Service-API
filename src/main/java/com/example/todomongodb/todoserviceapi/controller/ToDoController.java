package com.example.todomongodb.todoserviceapi.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import  static java.util.stream.Collectors.toList;

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

import com.example.todomongodb.todoserviceapi.model.ToDo;
import com.example.todomongodb.todoserviceapi.service.ToDoService;



@RestController
@RequestMapping(value = "/todo/api")
public class ToDoController {
    
	@Autowired
	private ToDoService toDoService;
	
	@PostMapping(value ="/create/user/{userId}", consumes ="application/json" , produces ="application/json")
	public String create(@PathVariable String userId, @RequestBody ToDo toDo) {
		String id = toDo.get_id();
		String toDoText = toDo.getToDoText();
		String day = toDo.getDay();
		LocalDateTime updatedAt = toDo.getUpdatedAt();
		ToDo toDoResult = toDoService.create(id,toDoText, day,userId,updatedAt);
		return toDoResult.toString();
	}
	
	@GetMapping(value = "/get/user",produces = "application/json")
	public List<ToDo> getToDoByUserId(@RequestParam String userId) {
		return toDoService.getByUserId(userId);
	}
	@GetMapping(value = "/getAll", produces = "application/json")
	public List<ToDo> getAll(){
		return toDoService.getAll();
	}
	
//	@GetMapping(value = "/getMyTodosByDay/user/{userId}", produces = "application/json")
//	public List<List<ToDo>> toDoSplitedByDay(@PathVariable String userId){
//		String[] days = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
//		List<ToDo> toDoListOfParticularUser =toDoService.getByUserId(userId);
//		List<ToDo> toDoByDay = new ArrayList<>();
//		List<Todo> toDoByDay = Arrays.stream(days)
//		           .map(day->{
//		toDoListOfParticularUser.stream()
//		                       .filter(toDo->toDo.getDay().equalsIgnoreCase(day)? toDoByDay.add(toDo) :false)
//		                       .collect(toList());
//		                       }
//		               );
//	}
	@PutMapping("/update/user/{userId}")
	public String update(@PathVariable String userId,@RequestBody ToDo toDo) {
		String id = toDo.get_id();
		String toDoText = toDo.getToDoText();
		String day = toDo.getDay();
		LocalDateTime createdAt = toDo.getCreatedAt();
		ToDo p = toDoService.update(id,toDoText, day,userId,createdAt);
		return p.toString();
	}
	@DeleteMapping("/delete/user/{userId}")
	public ResponseEntity<String> delete(@PathVariable String userId) {
		toDoService.delete(userId);
		return new ResponseEntity<String>("Deleted "+userId,HttpStatus.OK);
	}
	@DeleteMapping ("/deleteAll")
	public ResponseEntity<String> deleteAll() {
		toDoService.deleteAll();
		return new ResponseEntity<String>("Deleted all records",HttpStatus.OK);
	}
	
}
