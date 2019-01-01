package com.example.todomongodb.todoserviceapi.service;

import java.time.OffsetDateTime;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.groupingBy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.todomongodb.todoserviceapi.domain.ToDo;
import com.example.todomongodb.todoserviceapi.model.ToDoDto;
import com.example.todomongodb.todoserviceapi.repository.ToDoRepository;

class SortbyUpdatedTime implements Comparator<ToDo> 
{ 

    public int compare(ToDo toDo1, ToDo toDo2) 
    { 
        return toDo1.getUpdatedAt().compareTo(toDo2.getUpdatedAt()); 
    } 
} 


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
		List<ToDo> toDoList = toDoRepository.findByUserId(userId);
		return toDoList
				                   .stream()
                                   .collect(groupingBy(ToDo::getDay));
	}
	public List<ToDo> getSortedToDos(String userId)
	{
		List<ToDo> toDoList = toDoRepository.findByUserId(userId);
		Collections.sort(toDoList,new SortbyUpdatedTime());
		return toDoList;
	}
	//Update operations
    public ToDo update(ToDo toDo) {	
		ToDo toDoById = toDoRepository.findById(toDo.get_id()).orElseThrow(()->new RuntimeException("ToDo Id not found"));
		ToDo toDoUpdated = toDoById.update(toDo);
		return toDoRepository.save(toDoUpdated);
	}
    public ToDo updateToDoText(String id, String toDoText)
    {
    	ToDo toDoById = toDoRepository.findById(id).orElseThrow(()->new RuntimeException("ToDo Id not found"));
    	toDoById.setToDoText(toDoText);
        toDoById.setUpdatedAt(OffsetDateTime.now());
    	return toDoRepository.save(toDoById);
    }
   
    public ToDo markToDoAsFinished(String id)
    {
    	ToDo toDoById = toDoRepository.findById(id).orElseThrow(()->new RuntimeException("ToDo Id not found"));
    	if(!toDoById.getIsFinished())
    	{
    		toDoById.setIsFinished(true);
    	}
    	return toDoRepository.save(toDoById);
    }
	//Delete operations
	public void deleteAll() {
		toDoRepository.deleteAll();
	}
	public void delete(String id) {
		ToDo toDoById = toDoRepository.findById(id).orElseThrow(()->new RuntimeException("ToDo Id not found"));
		toDoRepository.delete(toDoById);
	}

}
