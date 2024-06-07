package org.jola.learning;

// One of the most common and versatile projects for beginners is a blogging platform.
// In this project, you’ll create a web application where users can create, read, update,
// and delete blog posts. This project will introduce you to core Spring Boot concepts
// such as Spring MVC for building web applications,
// Spring Data JPA for interacting with databases,
// and integrating a relational database like MySQL or PostgreSQL.

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleBlogApp {
    public static void main(String[]args){
        SpringApplication.run(SimpleBlogApp.class, args);
    }
}
