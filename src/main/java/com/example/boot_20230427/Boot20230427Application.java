package com.example.boot_20230427;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


//classpath ==> resources와 동일함
@SpringBootApplication
@PropertySource(value= {"classpath:global.properties"}) //직접만든 환경설정파일 위치
@MapperScan(basePackages = {"com.example.mapper"}) //매퍼 위치
@ComponentScan(basePackages = {
								"com.example.controller",
								"com.example.controller.jpa",
								"com.example.controller.mybatis",
								"com.example.service",
								"com.example.config",
								"com.example.restcontroller"
								})  //컨트롤러, 서비스 위치 , 시큐리티환경 설정
@EntityScan(basePackages = {"com.example.entity"}) //엔티티위치
@EnableJpaRepositories(basePackages = {"com.example.repository"}) //저장소 위치
public class Boot20230427Application {

	public static void main(String[] args) {
		SpringApplication.run(Boot20230427Application.class, args);
	}

}
