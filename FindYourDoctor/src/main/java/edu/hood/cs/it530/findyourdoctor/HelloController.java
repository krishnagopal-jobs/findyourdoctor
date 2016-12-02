package edu.hood.cs.it530.findyourdoctor;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.hood.cs.it530.findyourdoctor.searchphysician.SearchPhysicianDao;

@Component
@Path("/hello")
public class HelloController {

    @Autowired
    private SearchPhysicianDao searchPhysicianDao;

    @GET
    public String index() throws SQLException {
        searchPhysicianDao.getPhysicians(20147, 1, "firstName", "lastName");
        return "Greetings from Spring Boot! \n";
    }

}
