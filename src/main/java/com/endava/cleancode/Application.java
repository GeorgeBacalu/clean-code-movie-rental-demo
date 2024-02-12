package com.endava.cleancode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@Slf4j
@SpringBootApplication
public class Application implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        log.info(new Customer("Customer", List.of(
                new Rental(new Movie("Movie1", Movie.REGULAR), 2),
                new Rental(new Movie("Movie2", Movie.NEW_RELEASE), 2),
                new Rental(new Movie("Movie3", Movie.CHILDREN), 3))).statement());
    }
}
