package edu.hood.cs.it530.findyourdoctor.physicians;

import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
//import javax.ws.rs.MatrixParam;
//import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;

import edu.hood.cs.it530.findyourdoctor.common.beans.Physician;
import edu.hood.cs.it530.findyourdoctor.locations.LocationDao;

@Component
public class PhysicianResource {

    Logger logger = LoggerFactory.getLogger(PhysicianResource.class);

    @Context
    private UriInfo uriInfo;

    private PhysicianDao physicianDao;
    
    private LocationDao locationDao;

    @Autowired(required = true)
    public void setLocationDao(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Autowired(required = true)
    public void setSearchPhysicianDao(PhysicianDao physicianDao) {
        this.physicianDao = physicianDao;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PhysiciansResult retrievePhysicians(@MatrixParam("zipCode") int zipCode,
            @MatrixParam("specialityId") int specialityId, @MatrixParam("firstName") String firstName,
            @MatrixParam("lastName") String lastName) throws SQLException {

        logger.debug("zipCode:" + zipCode + "\nspecialityId:" + specialityId + "\nfirstName:" + firstName
                + "\nlastName:" + lastName);

        List<Physician> physicians = physicianDao.retrievePhysicians(zipCode, specialityId, firstName, lastName);

        PhysiciansResult physicianResult = new PhysiciansResult();
        physicianResult.setData(physicians);
        physicianResult.setItemCount(physicians.size());

        return physicianResult;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPhysicians(Physician physician) throws SQLException, URISyntaxException {


        XStream xstream = new XStream(new JsonHierarchicalStreamDriver() {
            public HierarchicalStreamWriter createWriter(Writer writer) {
                return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
            }
        });

        System.out.println(xstream.toXML("physician :" + physician));

        if (physician.getLocation() == null) {
            return Response.status(Status.BAD_REQUEST).entity("Location details are needed").build();
        }
        
        
        locationDao.insertLocation(physician.getLocation());
        
        physicianDao.insertPhysician(physician);
        
        URI location = uriInfo.getAbsolutePathBuilder().path("{id}").resolveTemplate("id", physician.getPhysicianId()).build();

        return Response.created(location).build();
    }

}
