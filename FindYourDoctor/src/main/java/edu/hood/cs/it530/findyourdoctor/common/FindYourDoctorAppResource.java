package edu.hood.cs.it530.findyourdoctor.common;

import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

import edu.hood.cs.it530.findyourdoctor.physicians.PhysicianResource;
import edu.hood.cs.it530.findyourdoctor.specialities.SpecialityResource;

@Component
@Path(URIPath.BASE)
public class FindYourDoctorAppResource {

    @Path(URIPath.PHYSICIANS)
    public Class<PhysicianResource> getPhysicians() {
        return PhysicianResource.class;
    }
    
    
    @Path(URIPath.SPECIALITIES)
    public Class<SpecialityResource> getSpecialities() {
        return SpecialityResource.class;
    }
}
