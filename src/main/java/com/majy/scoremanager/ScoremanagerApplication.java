package com.majy.scoremanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.majy.scoremanager.mapper")
public class ScoremanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScoremanagerApplication.class, args);
	}
}
