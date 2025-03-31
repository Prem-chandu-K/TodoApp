package com.project.TodoApp.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.TodoApp.Entity.Todo;
import com.project.TodoApp.Entity.User;
import com.project.TodoApp.Repository.TodoRepository;
import com.project.TodoApp.Repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    public Todo createTodo(String email, String title, String description) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setDescription(description);
        todo.setUser(user);
        return todoRepository.save(todo);
    }

    public List<Todo> getTodos(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return todoRepository.findByUserId(user.getId());  // Fetch list instead of Page
    }

    public Optional<Todo> updateTodo(String email, Long id, String title, String description) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent() && todoOptional.get().getUser().getEmail().equals(email)) {
            Todo todo = todoOptional.get();
            todo.setTitle(title);
            todo.setDescription(description);
            return Optional.of(todoRepository.save(todo));
        }
        return Optional.empty();
    }

    public boolean deleteTodo(String email, Long id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent() && todoOptional.get().getUser().getEmail().equals(email)) {
            todoRepository.delete(todoOptional.get());
            return true;
        }
        return false;
    }
}
