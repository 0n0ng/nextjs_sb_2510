package com.rest.proj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Spring Boot 애플리케이션의 메인 클래스입니다.
 */
@SpringBootApplication
@EnableJpaAuditing // baseEntity의 EntityListeners 실행되기 위함
public class ProjApplication {

	/**
	 * 애플리케이션의 진입점입니다.
	 * @param args 커맨드 라인 인수
	 */
	public static void main(String[] args) {
		SpringApplication.run(ProjApplication.class, args);
	}

}