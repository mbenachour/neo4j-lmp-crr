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
public class Generator {

	@Id @GeneratedValue private Long id;

	private String name;
    private String power;
	private Integer LMP;

	private Generator() {
		// Empty constructor required as of Neo4j API 2.0.5
	};

	public Generator(String name,String power, Integer LMP) {
		this.name = name;
        this.power = power;
		this.LMP = LMP;

	}

	/**
	 * Neo4j doesn't REALLY have bi-directional relationships. It just means when querying
	 * to ignore the direction of the relationship.
	 * https://dzone.com/articles/modelling-data-neo4j
	 */
	@Relationship(type = "POWERS", direction = Relationship.UNDIRECTED)
	public Set<Consumer> consumers;

	public void generatesTo(Consumer consumer) {
		if (consumers == null) {
			consumers = new HashSet<>();
		}
		consumers.add(consumer);
	}


    @Relationship(type = "10MW", direction = Relationship.UNDIRECTED)
	public Set<Connection> connections10;

	public void generatesToTen(Connection connection) {
		if (connections10 == null) {
			connections10 = new HashSet<>();
		}
		connections10.add(connection);
	}

    @Relationship(type = "4MW", direction = Relationship.UNDIRECTED)
	public Set<Connection> connections4;

	public void generatesToFour(Connection connection) {
		if (connections4 == null) {
			connections4 = new HashSet<>();
		}
		connections4.add(connection);
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

	public Integer getLMP() {
		return LMP;
	}

	public void setLMP(Integer LMP) {
		this.LMP = LMP;
	}
}