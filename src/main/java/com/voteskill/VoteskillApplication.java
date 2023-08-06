package com.voteskill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
//@PropertySource("classpath:/secretKey.properties")
public class VoteskillApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoteskillApplication.class, args);
	}

}