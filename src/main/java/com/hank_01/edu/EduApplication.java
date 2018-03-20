package com.hank_01.edu;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.hank_01.edu.mapper")
@ComponentScan(basePackages = {"com.hank_01.edu"})
public class EduApplication {

	protected static final Logger LOG = LoggerFactory.getLogger(EduApplication.class);
	public static void main(String[] args) {
		LOG.info("hanktest__________________________====================_________________==");
		SpringApplication.run(EduApplication.class, args);
	}
}
