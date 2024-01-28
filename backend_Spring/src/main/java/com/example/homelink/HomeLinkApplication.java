package com.example.homelink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HomeLinkApplication {
    public static void main(String[] args) {

         System.out.println("DB_URL: " + System.getenv("DB_URL"));
        System.out.println("DB_USERNAME: " + System.getenv("DB_USERNAME"));
        System.out.println("DB_PASSWORD: " + System.getenv("DB_PASSWORD"));
        System.out.println("SERVER_PORT: " + System.getenv("SERVER_PORT"));

        // Rest of your application code

        SpringApplication.run(HomeLinkApplication.class, args);
    }
}
