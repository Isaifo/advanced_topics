package com.aula_23_10.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aula_23_10.demo.model.ToDo;
import com.aula_23_10.demo.repository.ToDoRepository;

@RestController
@RequestMapping("/todo")
public class ToDoController {
    @Autowired
    private ToDoRepository todoRepository;

    @PostMapping    
    public ToDo create(@RequestBody ToDo todo) {
        todoRepository.save(todo);
        return todo;
    }

    @GetMapping
    public List<ToDo> getAll() {
        return todoRepository.findAll();
    }

}
