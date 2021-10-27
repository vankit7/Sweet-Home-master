package com.sweethome.Eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableEurekaServer
public class EurekaserverApplication {
	//https://www.baeldung.com/spring-cloud-netflix-eureka
	//http://localhost:8761/
	public static void main(String[] args) {
		SpringApplication.run(EurekaserverApplication.class, args);
	}

}
