package edu.hood.cs.it530.findyourdoctor.common;

import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

import edu.hood.cs.it530.findyourdoctor.physicians.PhysicianResource;
import edu.hood.cs.it530.findyourdoctor.specialities.SpecialityResource;

@Component
@Path("/findYourDoctor")
public class FindYourDoctorAppResource {

    @Path("/physicians")
    public Class<PhysicianResource> getPhysicians() {
        return PhysicianResource.class;
    }
    
    
    @Path("/specialities")
    public Class<SpecialityResource> getSpecialities() {
        return SpecialityResource.class;
    }
}
