package com.aula_23_10.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aula_23_10.demo.model.ToDo;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {

}
