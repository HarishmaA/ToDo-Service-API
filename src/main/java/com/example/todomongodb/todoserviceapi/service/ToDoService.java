package com.example.todomongodb.todoserviceapi.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todomongodb.todoserviceapi.model.ToDo;
import com.example.todomongodb.todoserviceapi.repository.ToDoRepository;



@Service
public class ToDoService {
   
	@Autowired
	private ToDoRepository toDoRepository;
	
	//Create operation
	public ToDo create(String  id, String toDoText, String day, String userId,LocalDateTime updatedAt) {
		LocalDateTime createdAt= LocalDateTime.now();
		return toDoRepository.save(new ToDo(id,toDoText,day,userId,createdAt,updatedAt));
	}
	//Retrieve operation
	public List<ToDo> getAll(){
		return toDoRepository.findAll();
	}
	public List<ToDo> getByUserId(String userId) {
		return toDoRepository.findByUserId(userId);
	}
	//Update operation
	public ToDo update(String id, String toDoText, String day, String userId,LocalDateTime createdAt) {
		List<ToDo> toDoListOfParticularUser = toDoRepository.findByUserId(userId);
		ToDo toDoResult = toDoListOfParticularUser.get(0);
		toDoResult.setToDoText(toDoText);
		toDoResult.setDay(day);
	    toDoResult.setUpdatedAt(LocalDateTime.now());
		return toDoRepository.save(toDoResult);
	}
	//Delete operation
	public void deleteAll() {
		toDoRepository.deleteAll();
	}
	public void delete(String userId) {
		List<ToDo> toDoListOfParticularUser = toDoRepository.findByUserId(userId);
		ToDo toDoResult = toDoListOfParticularUser.get(0);
		toDoRepository.delete(toDoResult);
	}
}
