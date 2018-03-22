package com.hank_01.edu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.hank_01.edu.mapper")
@ComponentScan(basePackages = {"com.hank_01.edu"})
public class EduApplication {
	public static void main(String[] args) {
		SpringApplication.run(EduApplication.class, args);
	}
}
