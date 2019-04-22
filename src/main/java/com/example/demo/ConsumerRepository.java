package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface ConsumerRepository extends CrudRepository<Consumer, Long> {

    Consumer findByName(String name);
}