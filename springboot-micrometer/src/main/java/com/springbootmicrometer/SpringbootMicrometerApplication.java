package com.springbootmicrometer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringbootMicrometerApplication 
{
	@GetMapping("/msg")
	public String msg()
	{
		return "Starting Prometheus";
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringbootMicrometerApplication.class, args);
	}

}
