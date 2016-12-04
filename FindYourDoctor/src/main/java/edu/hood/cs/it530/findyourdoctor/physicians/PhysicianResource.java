package edu.hood.cs.it530.findyourdoctor.physicians;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
//import javax.ws.rs.MatrixParam;
//import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.hood.cs.it530.findyourdoctor.common.beans.Physician;

@Component
public class PhysicianResource {

    Logger logger = LoggerFactory.getLogger(PhysicianResource.class);

    private PhysiciansDao physicianDao;

    public PhysiciansDao getSearchPhysicianDao() {
        return physicianDao;
    }

    @Autowired(required = true)
    public void setSearchPhysicianDao(PhysiciansDao physicianDao) {
        this.physicianDao = physicianDao;
    }

    @GET
    @Produces({ "application/json" })
    public PhysiciansResult retrievePhysicians(@MatrixParam("zipCode") int zipCode,
            @MatrixParam("specialityId") int specialityId, @MatrixParam("firstName") String firstName,
            @MatrixParam("lastName") String lastName) throws SQLException {

        logger.debug("zipCode:" + zipCode + "\nspecialityId:" + specialityId + "\nfirstName:" + firstName
                + "\nlastName:" + lastName);

        List<Physician> physicians = physicianDao.retrievePhysicians(zipCode, specialityId, firstName, lastName);

        PhysiciansResult physicianResult = new PhysiciansResult();
        physicianResult.setPhysicians(physicians);
        physicianResult.setCount(physicians.size());

        return physicianResult;
    }

}
