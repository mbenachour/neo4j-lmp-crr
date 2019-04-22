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
public class Connection {

	@Id @GeneratedValue private Long id;

	private String name;
    private String power;

	private Connection() {
		// Empty constructor required as of Neo4j API 2.0.5
	};

	public Connection(String name,String power) {
		this.name = name;
        this.power = power;
	}

	/**
	 * Neo4j doesn't REALLY have bi-directional relationships. It just means when querying
	 * to ignore the direction of the relationship.
	 * https://dzone.com/articles/modelling-data-neo4j
	 */
	@Relationship(type = "10MW", direction = Relationship.UNDIRECTED)
	public Set<Consumer> consumers;

	public void generatesTo(Consumer consumer) {
		if (consumers == null) {
			consumers = new HashSet<>();
		}
		consumers.add(consumer);
	}

    

	public String toString() {

		return this.name + "'s is part of fleet => "
			+ Optional.ofNullable(this.consumers).orElse(
					Collections.emptySet()).stream()
						.map(Consumer::getName)
						.collect(Collectors.toList());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}
}