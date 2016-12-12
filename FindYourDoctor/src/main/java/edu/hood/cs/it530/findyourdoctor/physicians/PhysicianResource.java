package edu.hood.cs.it530.findyourdoctor.physicians;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.hood.cs.it530.findyourdoctor.common.URIPath;
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
    @Path(URIPath.PHYSICIAN_ID)
    public Physician retrievePhysician(@QueryParam("physicianId") int physicianId) throws SQLException {

        Physician physician = physicianDao.retrievePhysicianDetails(physicianId);

        return physician;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(URIPath.DELETE_PHYSICIAN_BY_ID)
    public void deletePhysician(@QueryParam("physicianId") int physicianId) throws SQLException {
        physicianDao.deletePhysician(physicianId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PhysiciansResult retrievePhysicians(@QueryParam("zipCode") int zipCode,
            @QueryParam("specialityId") int specialityId, @QueryParam("firstName") String firstName,
            @QueryParam("lastName") String lastName, @QueryParam("city") String city) throws SQLException {

        logger.debug("zipCode:" + zipCode + "\nspecialityId:" + specialityId + "\nfirstName:" + firstName
                + "\nlastName:" + lastName);

        List<Physician> physicians = physicianDao.retrievePhysicians(zipCode, specialityId, firstName, lastName, city);

        PhysiciansResult physicianResult = new PhysiciansResult();
        physicianResult.setData(physicians);
        physicianResult.setItemCount(physicians.size());

        return physicianResult;
    }
    


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPhysicians(Physician physician) throws SQLException, URISyntaxException {

        if (physician.getLocation() == null) {
            return Response.status(Status.BAD_REQUEST).entity("Location details are needed").build();
        }
        
        locationDao.insertLocation(physician.getLocation());
        
        physicianDao.insertPhysician(physician);
        
        URI location = uriInfo.getAbsolutePathBuilder().path("{id}").resolveTemplate("id", physician.getPhysicianId()).build();

        return Response.created(location).build();
    }

}
