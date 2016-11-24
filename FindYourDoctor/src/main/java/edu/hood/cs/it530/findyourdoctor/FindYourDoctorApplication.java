package edu.hood.cs.it530.findyourdoctor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@PropertySource("classpath:application.properties")
public class FindYourDoctorApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(FindYourDoctorApplication.class, args);

	}

}
