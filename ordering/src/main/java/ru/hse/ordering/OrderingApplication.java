package ru.hse.ordering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"ru.hse.authorization.*", "ru.hse.ordering"})
@EnableJpaRepositories(basePackages = {"ru.hse.authorization.*", "ru.hse.ordering"})
@EntityScan({"ru.hse.authorization.*", "ru.hse.ordering"})
public class OrderingApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderingApplication.class, args);
    }

}
