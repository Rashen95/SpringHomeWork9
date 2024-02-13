package ru.geekbrains.Executormicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ExecutorMicroserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExecutorMicroserviceApplication.class, args);
    }
}