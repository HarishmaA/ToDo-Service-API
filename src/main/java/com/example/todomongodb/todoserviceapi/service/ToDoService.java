package com.example.todomongodb.todoserviceapi.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.groupingBy;

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
		return toDoRepository.save(new ToDo(toDoDto.getToDoText(),false,toDoDto.getDay(),toDoDto.getUserId(),OffsetDateTime.now(),OffsetDateTime.now()));
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
                                   .collect(groupingBy(ToDo::getDay));
	}
	//Update operations
	public ToDo update(ToDo toDo) {
		
		ToDo toDoById = toDoRepository.findById(toDo.get_id()).orElse(new ToDo());
		ToDo toDoUpdated = toDoById.update(toDo);
		return toDoRepository.save(toDoUpdated);
	}
	//Delete operations
	public void deleteAll() {
		toDoRepository.deleteAll();
	}
	public void delete(String id) {
		ToDo toDoById = toDoRepository.findById(id).orElse(new ToDo());
		toDoRepository.delete(toDoById);
	}

}
