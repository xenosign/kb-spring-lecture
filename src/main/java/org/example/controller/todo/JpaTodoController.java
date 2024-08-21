package org.example.controller.todo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.Todo;
import org.example.repository.todo.TodoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/todo")
public class JpaTodoController {
    private final TodoRepository todoRepository;

    @GetMapping
    public ResponseEntity<List<Todo>> getTodos() {
        List<Todo> todos = todoRepository.getTodos();
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> findById(@PathVariable Long id) {
        Todo findTodo = todoRepository.findById(id);

        if(findTodo == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(findTodo);
    }

    @PostMapping("/{todo}")
    public ResponseEntity<Todo> addTodo(@PathVariable("todo") String todo) {
        Todo newTodo = new Todo(null, todo, false);
        Todo addedTodo = todoRepository.addTodo(newTodo);
        if(addedTodo == null) return ResponseEntity.internalServerError().build();
        return ResponseEntity.ok(addedTodo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> chagneComplete(@PathVariable Long id) {
        Todo updatedTodo = todoRepository.findById(id);
        if(updatedTodo == null) return ResponseEntity.notFound().build();
        updatedTodo = todoRepository.changeComplete(id);
        return ResponseEntity.ok(updatedTodo);
    }

    @PutMapping("/update/{id}/{todo}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @PathVariable("todo") String todo) {
        Todo updatedTodo = todoRepository.findById(id);
        if(updatedTodo == null) return ResponseEntity.notFound().build();
        updatedTodo = todoRepository.updateTodo(id, todo);
        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Todo> delete(@PathVariable("id") Long id) {
        Todo todo = todoRepository.findById(id);
        if(todo == null) return ResponseEntity.notFound().build();
        todoRepository.deleteTodo(id);
        return ResponseEntity.ok(todo);
    }
}
