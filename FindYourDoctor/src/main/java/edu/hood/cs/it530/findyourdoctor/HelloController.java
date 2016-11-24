package edu.hood.cs.it530.findyourdoctor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.hood.cs.it530.findyourdoctor.doctordetails.DoctorInsertionModule;

@Component
@Path("/hello")
public class HelloController {
	
	@Autowired
	private DoctorInsertionModule doctorInsertionModule;
	
	@GET
	public String index() {
		//doctorInsertionModule.getDetails();
		return "Greetings from Spring Boot! \n";
	}

}
