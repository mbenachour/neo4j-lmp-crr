package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface ConnectionRepository extends CrudRepository<Connection, Long> {

    Consumer findByName(String name);
}