package ru.geekbrains.Taskmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class TaskMicroserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskMicroserviceApplication.class, args);
    }
}