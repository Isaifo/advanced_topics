package com.aula_23_10.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aula_23_10.demo.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
