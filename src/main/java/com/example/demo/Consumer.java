package com.example.demo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Consumer {

	@Id @GeneratedValue private Long id;

	private String name;
    private String power;
	private Integer LMP;

	private Consumer() {
		// Empty constructor required as of Neo4j API 2.0.5
	};

	public Consumer(String name, String power, Integer LMP) {
		this.name = name;
        this.power = power;
		this.LMP = LMP;
	}

	/**
	 * Neo4j doesn't REALLY have bi-directional relationships. It just means when querying
	 * to ignore the direction of the relationship.
	 * https://dzone.com/articles/modelling-data-neo4j
	 */


	public String toString() {

		return this.name ;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Integer getLMP() {
		return LMP;
	}

	public void setLMP(Integer LMP) {
		this.LMP = LMP;
	}
}