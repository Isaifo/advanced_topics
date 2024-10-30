package com.aula_23_10.demo.controller;

import java.util.List;

import org.hibernate.query.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aula_23_10.demo.model.Person;
import com.aula_23_10.demo.repository.PersonRepository;

import jakarta.validation.Valid;

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


    // pageable search
    @GetMapping
    public ResponseEntity<Page<Person>> getAll(
        
    @PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.ASC) //page default in no return
    Pageable pageable) {

        return ResponseEntity.status(206)
        .body(personRepository.findAll(pageable)); // body response 
       
    }

    @PutMapping("{id}")
    public ResponseEntity put(@PathVariable long id,
        @Valid @RequestBody PersonDTO dto)
    
    {
        // find person in db
        var personOpt = personRepository.findById(id);

        if(personOpt.isPresent()){
            BeanUtils.copyProperties(dto, person);
          
            try {
                
                personRepository.save(person);
                return ResponseEntity.ok().body(person);

            } catch (Exception ex) {
                return ResponseEntity.badRequest()
                .body("Falha ao salvar: " + ex.getMessage());
            }
        } else{
            return ResponseEntity.notFound().build();
        }
         
      
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable long id) {

      var personOpt = personRepository.findById(id)
      
      
      
        if (personOpt.isPresent()) {
        
        try{
            personRepository.delete(personOpt.get());
            return ResponseEntity.ok().build();
        }catch(Exception ex){
            return ResponseEntity
            .badRequest()
        .body("Falha ao deletar" + ex.getMessage());
        }
      }
        
    
}

    private static class PersonDTO {

        public PersonDTO() {
        }
    }
