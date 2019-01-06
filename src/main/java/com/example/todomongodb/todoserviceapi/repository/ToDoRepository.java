package com.example.todomongodb.todoserviceapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.todomongodb.todoserviceapi.domain.ToDo;

@Repository
public interface ToDoRepository extends MongoRepository<ToDo, String> {

	public List<ToDo> findByUserId(String userId);

	public Optional<ToDo> findById(String id);
}