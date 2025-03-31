package com.project.TodoApp.Controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.project.TodoApp.Entity.Todo;
import com.project.TodoApp.Service.TodoService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/todos")
    public ResponseEntity<Todo> createTodo(@AuthenticationPrincipal UserDetails userDetails,
                                           @RequestBody Map<String, String> request) {
        Todo todo = todoService.createTodo(userDetails.getUsername(), request.get("title"), request.get("description"));
        return ResponseEntity.ok(todo);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getTodos(@AuthenticationPrincipal UserDetails userDetails) {
        List<Todo> todos = todoService.getTodos(userDetails.getUsername());
        return ResponseEntity.ok(todos);
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@AuthenticationPrincipal UserDetails userDetails,
                                           @PathVariable Long id,
                                           @RequestBody Map<String, String> request) {
        Optional<Todo> updatedTodo = todoService.updateTodo(userDetails.getUsername(), id, request.get("title"), request.get("description"));
        return updatedTodo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@AuthenticationPrincipal UserDetails userDetails,
                                           @PathVariable Long id) {
        return todoService.deleteTodo(userDetails.getUsername(), id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
