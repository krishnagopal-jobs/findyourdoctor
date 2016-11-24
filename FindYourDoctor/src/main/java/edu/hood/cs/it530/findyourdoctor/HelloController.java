package edu.hood.cs.it530.findyourdoctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.hood.cs.it530.findyourdoctor.doctordetails.DoctorInsertionModule;

@RestController
public class HelloController {
	
	@Autowired
	private DoctorInsertionModule doctorInsertionModule;
	
	@RequestMapping("/")
	public String index() {
		doctorInsertionModule.getDetails();
		return "Greetings from Spring Boot! \n";
	}

}
