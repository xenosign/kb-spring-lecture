package org.example.repository.todo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.Book;
import org.example.domain.Todo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Repository
public class TodoRepository {
    private final EntityManager em;

    public List<Todo> getTodos() {
        String jpql = "select t from Todo t";
        List<Todo> todoList = em.createQuery(jpql, Todo.class).getResultList();
        return todoList;
    }

    public Todo findById(Long id) {
        return em.find(Todo.class, id);
    }

    public Todo updateTodo(Long id, String todo) {
        Todo existingTodo = em.find(Todo.class, id);
        if (existingTodo != null) {
            existingTodo.setTodo(todo);
            em.merge(existingTodo);
        }
        return existingTodo;
    }

    public Todo changeComplete(Long id) {
        Todo existingTodo = em.find(Todo.class, id);
        if (existingTodo != null) {
            existingTodo.setDone(!existingTodo.isDone());
        }

        return existingTodo;
    }

    public Todo addTodo(Todo todo) {
        em.persist(todo);
        return todo;
    }

    public void deleteTodo(Long id) {
        Todo todo = em.find(Todo.class, id);
        if (todo != null) em.remove(todo);
    }
}
