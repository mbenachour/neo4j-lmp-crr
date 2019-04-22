package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories
public class DemoApplication {
	private final static Logger log = LoggerFactory.getLogger(DemoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	

	@Bean
	CommandLineRunner demo(GeneratorRepository generatorRepository, ConsumerRepository consumerRepository, ConnectionRepository connectionRepository) {
		return args -> {
			generatorRepository.deleteAll();
			consumerRepository.deleteAll();
			connectionRepository.deleteAll();
			Generator gen1 = new Generator("generator1","10MW", 4);
			Generator gen2 = new Generator("generator2","20MW", 2);
			Consumer con1 = new Consumer("consumer","10MW",4);
			Connection conn1 = new Connection("connecion1","10MW");

			gen1.generatesToTen(conn1);
			gen2.generatesToFour(conn1);
			conn1.generatesTo(con1);
						
			log.info("save entities");
			generatorRepository.save(gen1);
			generatorRepository.save(gen2);
			connectionRepository.save(conn1);
			consumerRepository.save(con1);


		};
	}
}
