package com.example.todomongodb.todoserviceapi.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.todomongodb.todoserviceapi.domain.ToDo;
import com.example.todomongodb.todoserviceapi.model.ToDoDto;
import com.example.todomongodb.todoserviceapi.repository.ToDoRepository;



@Service
public class ToDoService {
   
	@Autowired
	private ToDoRepository toDoRepository;
	
	//Create operation
	public ToDo create(ToDoDto toDoDto) {
		return toDoRepository.save(new ToDo(toDoDto.getToDoText(),toDoDto.getDay(),toDoDto.getUserId(),OffsetDateTime.now(),toDoDto.getUpdatedAt()));
	}
	//Retrieve operations
	public List<ToDo> getAll(){
		return toDoRepository.findAll();
	}
	public List<ToDo> getByUserId(String userId) {
		return toDoRepository.findByUserId(userId);
	}
	
	public Map<String,List<ToDo>> getToDoSplitedByDay(@PathVariable String userId){
		List<ToDo> toDoListOfParticularUser = toDoRepository.findByUserId(userId);
		return toDoListOfParticularUser
				                   .stream()
                                   .collect(Collectors.groupingBy(ToDo::getDay));
	}
	//Update operations
	public ToDo update(ToDoDto toDoDto) {
		List<ToDo> toDoListOfParticularUser = toDoRepository.findByUserId(toDoDto.getUserId());
		ToDo toDoResult = toDoListOfParticularUser.get(0);
		System.out.println(toDoResult.getUpdatedAt());
		toDoResult.update(toDoDto,toDoResult.getCreatedAt());	
		return toDoRepository.save(toDoResult);
	}
	//Delete operations
	public void deleteAll() {
		toDoRepository.deleteAll();
	}
	public void delete(String userId) {
		List<ToDo> toDoListOfParticularUser = toDoRepository.findByUserId(userId);
		ToDo toDoResult = toDoListOfParticularUser.get(0);
		toDoRepository.delete(toDoResult);
	}

}
