package ru.nikolay.node;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan({"ru.nikolay"})
@EnableJpaRepositories(basePackages = {"ru.nikolay"})
@SpringBootApplication
public class NodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(NodeApplication.class, args);
	}

}
