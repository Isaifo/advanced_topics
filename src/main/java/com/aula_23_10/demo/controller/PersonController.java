package com.aula_23_10.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aula_23_10.demo.model.Person;
import com.aula_23_10.demo.repository.PersonRepository;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired //injeção de dependência
    private PersonRepository personRepository;

    @PostMapping
    public Person create(@RequestBody Person person) {
        personRepository.save(person);
        System.out.println(person);
        return person;
    }

    @GetMapping("{id}")
    public String get(@PathVariable int id) {
        return "Obtendo uma pessoa: "+id;
    }

    @GetMapping
    public String getAll() {
        return "Obtendo todas as pessoas";
    }

    @PutMapping
    public String put() {
        return "Pessoa Atualizada";
    }

    @DeleteMapping
    public String delete() {
        return "Pessoa Deletada";
    }
}
