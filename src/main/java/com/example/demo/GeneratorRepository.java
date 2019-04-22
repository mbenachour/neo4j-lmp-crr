package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface GeneratorRepository extends CrudRepository<Generator, Long> {

    Generator findByName(String name);
}