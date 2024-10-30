package com.aula_23_10.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    // get person from id

    @GetMapping("{id}")
    public ResponseEntity<Object> get(@PathVariable long id) {

        // find person in db, return type optional
        var personOpt =  personRepository.findById(id);

       return personOpt.isPresent()
       ? ResponseEntity.ok(personOpt.get())
       : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Person> getAll() {

        return personRepository.findAll();
       
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
