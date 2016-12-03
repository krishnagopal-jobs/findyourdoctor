package edu.hood.cs.it530.findyourdoctor.searchphysician;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Path("/physicians")
public class PhysicianResource {

    @Autowired
    private SearchPhysicianDao searchPhysicianDao;

    @GET
    @Path("/{physicianId}")
    public String index(@PathParam("physicianId") int physicianId) throws SQLException {
        searchPhysicianDao.getPhysicians(20147, 1, "firstName", "lastName");
        return "Greetings from Spring Boot! \n";
    }

}

