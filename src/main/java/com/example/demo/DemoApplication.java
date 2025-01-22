package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().load();

        System.out.println("DB_HOST: " + dotenv.get("DATABASE_PORT"));

        SpringApplication.run(DemoApplication.class, args);
    }
}
