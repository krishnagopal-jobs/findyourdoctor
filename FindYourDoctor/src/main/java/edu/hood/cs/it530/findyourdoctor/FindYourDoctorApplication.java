package edu.hood.cs.it530.findyourdoctor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(scanBasePackages={"edu.hood.cs.it530"})
@PropertySource("classpath:application.properties")
public class FindYourDoctorApplication {

    public static void main(String[] args) {
        
        SpringApplication.run(FindYourDoctorApplication.class, args);

    }

}
