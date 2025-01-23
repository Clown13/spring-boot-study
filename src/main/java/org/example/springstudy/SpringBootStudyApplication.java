package org.example.springstudy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// SpringBootApplication enables auto-configuration.
//Starts the Spring Boot Application with SpringApplication.run()
//Default Component Scan.
@SpringBootApplication
public class SpringBootStudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootStudyApplication.class, args);
    }

}