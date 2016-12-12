package edu.hood.cs.it530.findyourdoctor.specialities;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import edu.hood.cs.it530.findyourdoctor.common.beans.Speciality;

public class SpecialityResource {

    Logger logger = LoggerFactory.getLogger(SpecialityResource.class);

    private SpecialitiesDao specialitiesDao;

    @Autowired(required = true)
    public void setSpecialitiesDao(SpecialitiesDao physicianDao) {
        this.specialitiesDao = physicianDao;
    }

    @GET
    @Produces({ "application/json" })
    public SpecialityResult retrieveSpecialities() throws SQLException {

        SpecialityResult specialityResult = new SpecialityResult();

        List<Speciality> specialities = specialitiesDao.getSpecialities();
        specialityResult.setSpecialities(specialities);
        specialityResult.setCount(specialities.size());

        return specialityResult;
    }

}
