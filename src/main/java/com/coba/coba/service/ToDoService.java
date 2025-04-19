package com.coba.coba.service;

import com.coba.coba.model.ToDo;
import com.coba.coba.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    public List<ToDo> getTodosByStatus(int completed) {
        return toDoRepository.findByCompleted(completed);
    }

    public void saveTodo(ToDo todo) {
        toDoRepository.save(todo);
    }

    public void markAsDone(Long id) {
        ToDo todo = toDoRepository.findById(id).orElse(null);
        if (todo != null) {
            todo.setCompleted(1);;
            toDoRepository.save(todo);
        }
    }

    public ToDo getToDoById(Long id) {
        return toDoRepository.findById(id).orElse(null);
    }

    public void deleteTodo(Long id) {
        toDoRepository.deleteById(id);
    }

    public Optional<ToDo> getTodoById(Long id) {
        return toDoRepository.findById(id);
    }
}
