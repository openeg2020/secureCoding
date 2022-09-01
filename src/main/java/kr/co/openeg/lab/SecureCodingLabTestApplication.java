package kr.co.openeg.lab;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:key.properties")
@SpringBootApplication
public class SecureCodingLabTestApplication {

	private static final Logger logger = Logger.getLogger(SecureCodingLabTestApplication.class);
	
	public static void main(String[] args) {
		logger.info("Application Start!!");
		SpringApplication.run(SecureCodingLabTestApplication.class, args);
	}

}
