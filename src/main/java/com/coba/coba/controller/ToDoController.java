package com.coba.coba.controller;

import com.coba.coba.model.ToDo;
import com.coba.coba.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    @GetMapping
    public String home(Model model) {
        List<ToDo> unfinished = toDoService.getTodosByStatus(0);
        List<ToDo> finished = toDoService.getTodosByStatus(1);

        model.addAttribute("todos", unfinished);
        model.addAttribute("todosFinished", finished);
        model.addAttribute("newTodo", new ToDo());
        return "index";
    }

    @PostMapping("/add")
    public String addTodo(@ModelAttribute ToDo todo) {
        toDoService.saveTodo(todo);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        toDoService.deleteTodo(id);
        return "redirect:/";
    }

    @PostMapping("/done/{id}")
    public String markAsDone(@PathVariable Long id) {
        toDoService.markAsDone(id);
        return "redirect:/";
    }

    @PostMapping("/undo/{id}")
public String undoTodo(@PathVariable Long id) {
    ToDo todo = toDoService.getToDoById(id);
    if (todo != null) {
        todo.setCompleted(0); // ubah status jadi "belum selesai"
        toDoService.saveTodo(todo);
    }
    return "redirect:/";
}
}
