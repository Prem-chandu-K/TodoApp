package com.project.TodoApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.TodoApp.Entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
	
	List<Todo> findByUserId(Long userId);

	
}